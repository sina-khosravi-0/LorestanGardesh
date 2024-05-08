package com.example.lorestangardesh.ui;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lorestangardesh.R;
import com.example.lorestangardesh.ui.carousel.CarouselItem;
import com.example.lorestangardesh.ui.carousel.CarouselRecyclerAdapter;
import com.example.lorestangardesh.ui.review.ReviewItem;
import com.example.lorestangardesh.ui.review.ReviewRecyclerAdapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.carousel.CarouselLayoutManager;

import java.util.Arrays;

public class PlaceEventFragment extends AppCompatActivity {
    BottomSheetBehavior<View> reviewsBottomSheetBehavior;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_place_event);

        RecyclerView carousel = findViewById(R.id.carousel);
        RecyclerView reviewRecyclerView = findViewById(R.id.reviews_recycler_view);
        MaterialCardView reviewsCardView = findViewById(R.id.reviews_card_view);
        reviewsBottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.reviews_bottom_sheet));
        MaterialButton showDirectionsBtn = findViewById(R.id.show_directions_btn);
        showDirectionsBtn.setOnClickListener(v -> {
            String uri = "google.navigation:q=" + "33.484160" + "," + "48.353181";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            startActivity(intent);
        });

        new Thread(() -> {
            CarouselRecyclerAdapter carouselAdapter = new CarouselRecyclerAdapter(Arrays.asList(
                    new CarouselItem(( R.drawable.falk),
                            null, null),
                    new CarouselItem(( R.drawable.falk2),
                            null, null),
                    new CarouselItem(( R.drawable.falk),
                            null, null),
                    new CarouselItem(( R.drawable.falk2),
                            null, null)));
            runOnUiThread(() -> {
                carousel.setAdapter(carouselAdapter);
                carousel.setLayoutManager(new CarouselLayoutManager());
            });
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
            ReviewRecyclerAdapter reviewRecyclerAdapter = new ReviewRecyclerAdapter(Arrays.asList(
                    new ReviewItem(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.falk2),
                            "user", getString(R.string.test_text), false),
                    new ReviewItem(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.falk2),
                            "user", getString(R.string.test_text), true),
                    new ReviewItem(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.falk2),
                            "user", getString(R.string.test_text), false),
                    new ReviewItem(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.falk2),
                            "user", getString(R.string.test_text), false),
                    new ReviewItem(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.falk2),
                            "user", getString(R.string.test_text), false),
                    new ReviewItem(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.falk2),
                            "user", getString(R.string.test_text), false)));
            runOnUiThread(() -> {
                reviewRecyclerView.setAdapter(reviewRecyclerAdapter);
                reviewRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
            });
        }).start();

        OnBackPressedCallback callback = new OnBackPressedCallback(isReviewsExpanded()) {
            @Override
            public void handleOnBackPressed() {
                if (reviewsBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    reviewsBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    setEnabled(false);
                }
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
        reviewsCardView.setOnClickListener(v -> {
            callback.setEnabled(true);
            reviewsBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        });
        reviewsBottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                if (isReviewsCollapsed()) {
                    callback.setEnabled(false);
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });
    }

    private boolean isReviewsExpanded() {
        System.out.println(reviewsBottomSheetBehavior.getState());
        return reviewsBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED;
    }

    private boolean isReviewsCollapsed() {
        System.out.println(reviewsBottomSheetBehavior.getState());
        return reviewsBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED;
    }

//    @Override
//    public void onBackPressed() {
//        if (reviewsBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
//            reviewsBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//            return;
//        }
//        super.onBackPressed();
//    }
}