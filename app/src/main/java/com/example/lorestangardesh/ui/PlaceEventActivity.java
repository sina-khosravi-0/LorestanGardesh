package com.example.lorestangardesh.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lorestangardesh.R;
import com.example.lorestangardesh.db.BriefLocation;
import com.example.lorestangardesh.db.DataManager;
import com.example.lorestangardesh.db.Location;
import com.example.lorestangardesh.db.OpeningHour;
import com.example.lorestangardesh.ui.carousel.CarouselItem;
import com.example.lorestangardesh.ui.carousel.CarouselRecyclerAdapter;
import com.example.lorestangardesh.ui.review.ReviewItem;
import com.example.lorestangardesh.ui.review.ReviewRecyclerAdapter;
import com.example.lorestangardesh.utils.GeoDistance;
import com.example.lorestangardesh.utils.Util;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.carousel.CarouselLayoutManager;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PlaceEventActivity extends AppCompatActivity {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 100;
    BottomSheetBehavior<View> reviewsBottomSheetBehavior;
    Location location;
    TextView distance;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_place_event);

        // Initialize FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        // Check for location permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            // Request permissions
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        }

        RecyclerView carousel = findViewById(R.id.carousel);
        TextView title = findViewById(R.id.title_text);
        TextView address = findViewById(R.id.address);
        distance = findViewById(R.id.distance);
        TextView description = findViewById(R.id.description);
        RecyclerView reviewRecyclerView = findViewById(R.id.reviews_recycler_view);
        MaterialCardView reviewsCardView = findViewById(R.id.reviews_card_view);
        reviewsBottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.reviews_bottom_sheet));
        MaterialButton showDirectionsBtn = findViewById(R.id.show_directions_btn);
        LinearLayout container = findViewById(R.id.container);
        LinearLayout nearLocationContainer = findViewById(R.id.near_location_container);
        TextView phoneText = findViewById(R.id.phone_text);

        new Thread(() -> {
            location = DataManager.getInstance().getLocationDetails(getIntent().getIntExtra("id", 0));
            CarouselRecyclerAdapter carouselAdapter = new CarouselRecyclerAdapter(
                    location.getPhotos().stream().map(photo -> new CarouselItem(photo, "", "")).collect(Collectors.toList()),
                    this
            );

            showDirectionsBtn.setOnClickListener(v -> {
                String uri = "google.navigation:q=" + location.getLat() + "," + location.getLon();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            });

            runOnUiThread(() -> {
                carousel.setAdapter(carouselAdapter);
                carousel.setLayoutManager(new CarouselLayoutManager());
                title.setText(location.getTitle());
                address.setText(location.getAddress());
                description.setText(location.getDescription());
                phoneText.setText(location.getPhone());

                phoneText.setOnClickListener(v -> Util.copyToClipboard(this, (String) phoneText.getText()));

                LayoutInflater inflater = LayoutInflater.from(this);
                for (OpeningHour item : location.getOpeningHours()) {
                    View row = inflater.inflate(R.layout.items_opening_hour, container, false);

                    TextView day = row.findViewById(R.id.day);
                    TextView opening = row.findViewById(R.id.opening);
                    TextView closing = row.findViewById(R.id.closing);

                    day.setText(item.day);
                    opening.setText(item.openTime);
                    closing.setText(item.closeTime);

                    container.addView(row);
                }

                for (BriefLocation item : location.getNearestLocations()) {
                    View row = inflater.inflate(R.layout.items_near_location_card, nearLocationContainer, false);

                    row.setOnClickListener(v -> {
                        Intent intent = new Intent(this, PlaceEventActivity.class);
                        intent.putExtra("id", Integer.valueOf(item.id));
                        startActivity(intent);
                    });
                    TextView opening = row.findViewById(R.id.title_text);
                    TextView closing = row.findViewById(R.id.distance_text);

                    opening.setText(item.title);
                    closing.setText(String.format("%.2f km", item.distance_km));

                    nearLocationContainer.addView(row);

                }

                getLastLocation();
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
        return reviewsBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED;
    }

    private boolean isReviewsCollapsed() {
        return reviewsBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED;
    }

    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<android.location.Location>() {
                        @Override
                        public void onSuccess(android.location.Location location) {

                            distance.setText(String.format("%.1f km", GeoDistance.calculateDistance(location.getLatitude(), location.getLongitude(), PlaceEventActivity.this.location.getLat(), PlaceEventActivity.this.location.getLon())));
                            System.out.println(location.getLatitude());
                            System.out.println(location.getLongitude());
                        }
                    });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, get location
                getLastLocation();
            } else {
                // Permission denied
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}