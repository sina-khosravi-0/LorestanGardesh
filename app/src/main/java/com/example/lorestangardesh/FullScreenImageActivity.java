package com.example.lorestangardesh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.lorestangardesh.ui.carousel.CarouselItem;
import com.example.lorestangardesh.ui.carousel.CarouselRecyclerAdapter;
import com.google.android.material.carousel.CarouselLayoutManager;
import com.google.android.material.carousel.CarouselSnapHelper;
import com.google.android.material.carousel.FullScreenCarouselStrategy;
import com.google.android.material.transition.platform.MaterialContainerTransform;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;

import java.util.Arrays;

public class FullScreenImageActivity extends AppCompatActivity implements CarouselRecyclerAdapter.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable Activity Transitions. Optionally enable Activity transitions in your
        // theme with <item name=”android:windowActivityTransitions”>true</item>.
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        // Attach a callback used to receive the shared elements from Activity A to be
        // used by the container transform transition.
        setEnterSharedElementCallback(new MaterialContainerTransformSharedElementCallback());
        // Set this Activity’s enter and return transition to a MaterialContainerTransform
        MaterialContainerTransform containerTransform = new MaterialContainerTransform();
        containerTransform.addTarget(R.id.gallery);
        containerTransform.setDuration(300L);
//        getWindow().setSharedElementEnterTransition(containerTransform);

        MaterialContainerTransform containerTransform2 = new MaterialContainerTransform();
        containerTransform2.addTarget(R.id.gallery);
        containerTransform2.setDuration(300L);
//        getWindow().setSharedElementReturnTransition(containerTransform2);

        setContentView(R.layout.activity_fullscreen_image);

        RecyclerView carouselRecyclerView = findViewById(R.id.gallery);
        ViewCompat.setTransitionName(carouselRecyclerView, "gallery");

        CarouselRecyclerAdapter carouselRecyclerAdapter = new CarouselRecyclerAdapter(
                Arrays.asList(new CarouselItem(R.drawable.falk,
                                "فلک الافلاک", "جاذبه ی گردشگری خرم آباد"),
                        new CarouselItem(R.drawable.falk2,
                                "فلک الافلاک", "جاذبه ی گردشگری خرم آباد"),
                        new CarouselItem(R.drawable.falk,
                                "فلک الافلاک", "جاذبه ی گردشگری خرم آباد"),
                        new CarouselItem(R.drawable.falk2,
                                "فلک الافلاک", "جاذبه ی گردشگری خرم آباد"),
                        new CarouselItem(R.drawable.falk,
                                "فلک الافلاک", "جاذبه ی گردشگری خرم آباد"),
                        new CarouselItem(R.drawable.falk2,
                                "فلک الافلاک", "جاذبه ی گردشگری خرم آباد")),
                this);

        CarouselLayoutManager carouselManager = new CarouselLayoutManager(new FullScreenCarouselStrategy());
        carouselManager.setCarouselAlignment(CarouselLayoutManager.ALIGNMENT_START);
        SnapHelper snapHelper = new CarouselSnapHelper();
        snapHelper.attachToRecyclerView(carouselRecyclerView);

        runOnUiThread(() -> {
            carouselRecyclerView.setAdapter(carouselRecyclerAdapter);
            carouselRecyclerView.setLayoutManager(carouselManager);
            carouselRecyclerView.getLayoutManager().scrollToPosition(getIntent().getIntExtra("carouselPosition", 0));
            carouselRecyclerView.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void onClick(ImageView imageView) {

    }

    @Override
    public void onClick(int position) {

    }
}