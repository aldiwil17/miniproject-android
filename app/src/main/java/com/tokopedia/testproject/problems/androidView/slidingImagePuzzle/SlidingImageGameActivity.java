package com.tokopedia.testproject.problems.androidView.slidingImagePuzzle;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.Toast;

import com.tokopedia.testproject.R;

import java.util.List;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class SlidingImageGameActivity extends AppCompatActivity {
    private static GestureDetectGridView mGridView;
    private static int mColumnWidth, mColumnHeight;

    public static final String up = "up";
    public static final String down = "down";
    public static final String left = "left";
    public static final String right = "right";

    private static String[] tileList;
    public static final String X_IMAGE_URL = "x_image_url";
    public static final int GRID_NO = 4;
    private static final int DIMENSIONS = GRID_NO * GRID_NO;
    private String imageUrl;
    ImageView[][] imageViews = new ImageView[4][4];
    private GridLayout gridLayout;

    public static Intent getIntent(Context context, String imageUrl) {
        Intent intent = new Intent(context, SlidingImageGameActivity.class);
        intent.putExtra(X_IMAGE_URL, imageUrl);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageUrl = getIntent().getStringExtra(X_IMAGE_URL);
        setContentView(R.layout.activity_sliding_image_game);
        gridLayout = findViewById(R.id.gridLayout);

        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < GRID_NO; i++) {
            for (int j = 0; j < GRID_NO; j++) {
                ImageView view = (ImageView) inflater.inflate(R.layout.item_image_sliding_image,
                        gridLayout, false);
                gridLayout.addView(view);
                imageViews[i][j] = view;
            }
        }

        Solution.sliceTo4x4(this, new Solution.onSuccessLoadBitmap() {
            @Override
            public void onSliceSuccess(List<Bitmap> bitmapList) {
                //TODO will randomize placement to grid. Note: the game must be solvable.
                //replace below implementation to your implementation.
                int counter = 0;
                int bitmapSize = bitmapList.size();
                for (int i = 0; i < GRID_NO; i++) {
                    for (int j = 0; j < GRID_NO; j++) {
                        if (counter >= bitmapSize) break;
                        imageViews[i][j].setImageBitmap(bitmapList.get(counter));
                        counter++;
                    }
                    if (counter >= bitmapSize) break;
                }
            }

            @Override
            public void onSliceFailed(Throwable throwable) {
                Toast.makeText(SlidingImageGameActivity.this,
                        throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        }, imageUrl);

        init();

        scramble();

        setDimensions();
        // TODO add implementation of the game.
        // There is image adjacent to blank space (either horizontal or vertical).
        // If that image is clicked, it will swap to the blank space
        // if the puzzle is solved (the image in the view is aligned with the original image), then show a "success" dialog

        // TODO add handling for rotation to save the user input.
        // If the device is rotated, it should retain user's input, so user can continue the game.
    }
    private void init() {
        mGridView = (GestureDetectGridView) findViewById(R.id.gridView);
        mGridView.setNumColumns(GRID_NO);

        tileList = new String[DIMENSIONS];
        for (int i = 0; i < DIMENSIONS; i++) {
            tileList[i] = String.valueOf(i);
        }
    }

    private void scramble() {
        int index;
        String temp;
        Random random = new Random();

        for (int i = tileList.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = tileList[index];
            tileList[index] = tileList[i];
            tileList[i] = temp;
        }
    }

    private void setDimensions() {
        ViewTreeObserver vto = mGridView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int displayWidth = mGridView.getMeasuredWidth();
                int displayHeight = mGridView.getMeasuredHeight();

                int statusbarHeight = getStatusBarHeight(getApplicationContext());
                int requiredHeight = displayHeight - statusbarHeight;

                mColumnWidth = displayWidth / GRID_NO;
                mColumnHeight = requiredHeight / GRID_NO;

                display(getApplicationContext());
            }
        });
    }

    private int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");

        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }

    private static void display(Context context) {
        ArrayList<Button> buttons = new ArrayList<>();
        Button button;

        for (int i = 0; i < tileList.length; i++) {
            button = new Button(context);

            if (tileList[i].equals("0"))
                button.setBackgroundResource(R.drawable.sample11);
            else if (tileList[i].equals("1"))
                button.setBackgroundResource(R.drawable.sample12);
            else if (tileList[i].equals("2"))
                button.setBackgroundResource(R.drawable.sample13);
            else if (tileList[i].equals("3"))
                button.setBackgroundResource(R.drawable.sample14);
            else if (tileList[i].equals("4"))
                button.setBackgroundResource(R.drawable.sample21);
            else if (tileList[i].equals("5"))
                button.setBackgroundResource(R.drawable.sample22);
            else if (tileList[i].equals("6"))
                button.setBackgroundResource(R.drawable.sample23);
            else if (tileList[i].equals("7"))
                button.setBackgroundResource(R.drawable.sample24);
            else if (tileList[i].equals("8"))
                button.setBackgroundResource(R.drawable.sample31);
            else if (tileList[i].equals("9"))
                button.setBackgroundResource(R.drawable.sample32);
            else if (tileList[i].equals("10"))
                button.setBackgroundResource(R.drawable.sample33);
            else if (tileList[i].equals("11"))
                button.setBackgroundResource(R.drawable.sample34);
            else if (tileList[i].equals("12"))
                button.setBackgroundResource(R.drawable.sample41);
            else if (tileList[i].equals("13"))
                button.setBackgroundResource(R.drawable.sample42);
            else if (tileList[i].equals("14"))
                button.setBackgroundResource(R.drawable.sample43);

            buttons.add(button);
        }

        mGridView.setAdapter(new CustomAdapter(buttons, mColumnWidth, mColumnHeight));
    }

    private static void swap(Context context, int currentPosition, int swap) {
        String newPosition = tileList[currentPosition + swap];
        tileList[currentPosition + swap] = tileList[currentPosition];
        tileList[currentPosition] = newPosition;
        display(context);

        if (isSolved()) Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
    }

    public static void moveTiles(Context context, String direction, int position) {

        // Upper-left-corner tile
        if (position == 0) {

            if (direction.equals(right)) swap(context, position, 1);
            else if (direction.equals(down)) swap(context, position, GRID_NO);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Upper-center tiles
        } else if (position > 0 && position < GRID_NO - 1) {
            if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) swap(context, position, GRID_NO);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Upper-right-corner tile
        } else if (position == GRID_NO - 1) {
            if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) swap(context, position, GRID_NO);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Left-side tiles
        } else if (position > GRID_NO - 1 && position < DIMENSIONS - GRID_NO &&
                position % GRID_NO == 0) {
            if (direction.equals(up)) swap(context, position, -GRID_NO);
            else if (direction.equals(right)) swap(context, position, 1);
            else if (direction.equals(down)) swap(context, position, GRID_NO);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Right-side AND bottom-right-corner tiles
        } else if (position == GRID_NO * 2 - 1 || position == GRID_NO * 3 - 1) {
            if (direction.equals(up)) swap(context, position, -GRID_NO);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) {

                // Tolerates only the right-side tiles to swap downwards as opposed to the bottom-
                // right-corner tile.
                if (position <= DIMENSIONS - GRID_NO - 1) swap(context, position,
                        GRID_NO);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-left corner tile
        } else if (position == DIMENSIONS - GRID_NO) {
            if (direction.equals(up)) swap(context, position, -GRID_NO);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-center tiles
        } else if (position < DIMENSIONS - 1 && position > DIMENSIONS - GRID_NO) {
            if (direction.equals(up)) swap(context, position, -GRID_NO);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Center tiles
        } else {
            if (direction.equals(up)) swap(context, position, -GRID_NO);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(right)) swap(context, position, 1);
            else swap(context, position, GRID_NO);
        }
    }

    private static boolean isSolved() {
        boolean solved = false;

        for (int i = 0; i < tileList.length; i++) {
            if (tileList[i].equals(String.valueOf(i))) {
                solved = true;
            } else {
                solved = false;
                break;
            }
        }

        return solved;
    }
}
