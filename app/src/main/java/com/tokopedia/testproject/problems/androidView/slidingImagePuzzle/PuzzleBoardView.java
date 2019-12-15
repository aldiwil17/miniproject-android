package com.tokopedia.testproject.problems.androidView.slidingImagePuzzle;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class PuzzleBoardView extends View {
    public static final int NUM_SHUFFLE_STEPS = 40;
    private Activity activity;
    private Solution puzzleBoard;
    private ArrayList<Solution> animation;
    private Random random = new Random();
    Comparator<Solution> comparator = new PuzzleBoardComparator();
    PriorityQueue<Solution> queue = new PriorityQueue<>(9999, comparator);

    public PuzzleBoardView(Context context) {
        super(context);
        activity = (Activity) context;
        animation = null;
    }

    public void initialize(Bitmap imageBitmap) {
        int width = getWidth();
        puzzleBoard = new Solution(imageBitmap, width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (puzzleBoard != null) {
            if (animation != null && animation.size() > 0) {
                puzzleBoard = animation.remove(0);
                puzzleBoard.draw(canvas);
                if (animation.size() == 0) {
                    animation = null;
                    Toast toast = Toast.makeText(activity, "Solved! ", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    this.postInvalidateDelayed(500);
                }
            } else {
                puzzleBoard.draw(canvas);
            }
        }
    }

    public void shuffle() {
        if (animation == null && puzzleBoard != null) {
            // Do something. Then:
            ArrayList<Solution> boards;
            for (int i = 0; i <= NUM_SHUFFLE_STEPS; i++) {
                boards = puzzleBoard.neighbours();
                puzzleBoard = boards.get(random.nextInt(boards.size()));
            }
            invalidate();
            queue.clear();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (animation == null && puzzleBoard != null) {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (puzzleBoard.click(event.getX(), event.getY())) {
                        invalidate();
                        if (puzzleBoard.resolved()) {
                            Toast toast = Toast.makeText(activity, "success!", Toast.LENGTH_LONG);
                            toast.show();
                        }
                        return true;
                    }
            }
        }
        return super.onTouchEvent(event);
    }


    public void solve() {
        puzzleBoard.steps = 0;
        puzzleBoard.previousBoard = null;
        queue.add(puzzleBoard);
        Solution prev = null;
        ArrayList<Solution> solution = new ArrayList<>();
        while (!queue.isEmpty()) {
            Solution lowest = queue.poll();
            if (lowest.priority() - lowest.steps != 0) {
                for (Solution toAdd : lowest.neighbours()) {
                    if (!toAdd.equals(prev)) {
                        queue.add(toAdd);
                    }
                }
                prev = lowest;
            }
            else {
                solution.add(lowest);
                while (lowest != null) {
                    if (lowest.getPreviousBoard() == null) {
                        break;
                    }
                    solution.add(lowest.getPreviousBoard());
                    lowest = lowest.getPreviousBoard();
                }
                Collections.reverse(solution);
                animation = solution;
                invalidate();
                break;
            }
        }
    }
}

class PuzzleBoardComparator implements Comparator<Solution> {
    @Override
    public int compare(Solution first, Solution second) {
        if (first.priority() == second.priority()) {
            return 0;
        }
        else if (first.priority() < second.priority()) {
            return -1;
        }
        else {
            return 1;
        }
    }
}
