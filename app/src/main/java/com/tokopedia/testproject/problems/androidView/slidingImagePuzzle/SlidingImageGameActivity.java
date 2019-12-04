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

public class SlidingImageGameActivity extends AppCompatActivity {
    public static final String X_IMAGE_URL = "x_image_url";
    public static final int GRID_NO = 4;
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

        // TODO add implementation of the game.
        // There is image adjacent to blank space (either horizontal or vertical).
        // If that image is clicked, it will swap to the blank space
        // if the puzzle is solved (the image in the view is aligned with the original image), then show a "success" dialog

        // TODO add handling for rotation to save the user input.
        // If the device is rotated, it should retain user's input, so user can continue the game.
    }

}
