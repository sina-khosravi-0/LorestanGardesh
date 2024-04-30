package com.example.lorestangardesh.ui.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lorestangardesh.ui.PlaceEventFragment;
import com.example.lorestangardesh.R;
import com.example.lorestangardesh.ui.tour.TourActivity;
import com.example.lorestangardesh.ui.carousel.CarouselItem;
import com.example.lorestangardesh.ui.carousel.CarouselRecyclerAdapter;
import com.example.lorestangardesh.ui.mediumcard.MediumCardItem;
import com.example.lorestangardesh.ui.mediumcard.MediumCardViewRecyclerAdapter;
import com.google.android.material.carousel.CarouselLayoutManager;
import com.google.android.material.carousel.CarouselSnapHelper;
import com.google.android.material.carousel.HeroCarouselStrategy;

import java.util.Arrays;

public class HomeFragment extends Fragment {

    private Activity activity;
    private Context context;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.activity = requireActivity();
        this.context = activity.getApplicationContext();
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new Thread(() -> {
            Glide.get(context).clearDiskCache();
            try {
                new Thread(() -> {
                    RecyclerView carouselRecyclerView = view.findViewById(R.id.carousel_recycler_view);
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
                                            "فلک الافلاک", "جاذبه ی گردشگری خرم آباد")));

                    CarouselLayoutManager carouselManager = new CarouselLayoutManager(new HeroCarouselStrategy());
                    carouselManager.setCarouselAlignment(CarouselLayoutManager.ALIGNMENT_START);
                    SnapHelper snapHelper = new CarouselSnapHelper();
                    snapHelper.attachToRecyclerView(carouselRecyclerView);

                    activity.runOnUiThread(() -> {
                        carouselRecyclerView.setAdapter(carouselRecyclerAdapter);
                        carouselRecyclerView.setLayoutManager(carouselManager);
                        carouselRecyclerView.setVisibility(View.VISIBLE);
                    });
                }).start();

                new Thread(() -> {
                    TextView title = view.findViewById(R.id.near_you_title);
                    RecyclerView nearYouCardRecycler = view.findViewById(R.id.near_you_card_view_recycler);
                    MediumCardViewRecyclerAdapter mediumCardViewRecyclerAdapter1 = new MediumCardViewRecyclerAdapter(Arrays
                            .asList(new MediumCardItem(( R.drawable.falk),
                                            "فلک الافلاک", "جاذبه ی گردشگری خرم آباد"),
                                    new MediumCardItem(( R.drawable.falk2),
                                            "آبشار بیشه", "جاذبه ی گردشگری خرم آباد"),
                                    new MediumCardItem(( R.drawable.falk),
                                            "آبشار بیشه", "جاذبه ی گردشگری خرم آباد"),
                                    new MediumCardItem(( R.drawable.falk2),
                                            "آبشار بیشه", "جاذبه ی گردشگری خرم آباد"),
                                    new MediumCardItem(( R.drawable.falk),
                                            "آبشار بیشه", "جاذبه ی گردشگری خرم آباد"),
                                    new MediumCardItem(( R.drawable.falk2),
                                            "آبشار بیشه", "جاذبه ی گردشگری خرم آباد")));
                    LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context);
                    linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);

                    mediumCardViewRecyclerAdapter1.setOnItemClickListener(v -> {
                        startActivity(new Intent(getContext(), PlaceEventFragment.class));
                    });

                    activity.runOnUiThread(() -> {
                        nearYouCardRecycler.setAdapter(mediumCardViewRecyclerAdapter1);
                        nearYouCardRecycler.setLayoutManager(linearLayoutManager1);
                        nearYouCardRecycler.setVisibility(View.VISIBLE);
                        title.setVisibility(View.VISIBLE);
                    });
                }).start();

                new Thread(() -> {
                    TextView title = view.findViewById(R.id.tours_title);
                    RecyclerView toursCardRecycler = view.findViewById(R.id.tours_card_view_recycler);
                    LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(context);
                    linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
                    MediumCardViewRecyclerAdapter mediumCardViewRecyclerAdapter2 = new MediumCardViewRecyclerAdapter(Arrays
                            .asList(new MediumCardItem((R.drawable.falk),
                                            "تور گردشگری", "تور گردشگری نوروزی"),
                                    new MediumCardItem((R.drawable.falk2),
                                            "تور", "تور گردشگری خارجی")));
                    mediumCardViewRecyclerAdapter2.setOnItemClickListener(v -> {
                        startActivity(new Intent(getContext(), TourActivity.class));

                    });
                    activity.runOnUiThread(() -> {
                        toursCardRecycler.setAdapter(mediumCardViewRecyclerAdapter2);
                        toursCardRecycler.setLayoutManager(linearLayoutManager2);
                        toursCardRecycler.setVisibility(View.VISIBLE);
                        title.setVisibility(View.VISIBLE);
                    });
                }).start();

                new Thread(() -> {
                    TextView title = view.findViewById(R.id.travel_tickets_title);
                    RecyclerView travelTicketCardRecycler = view.findViewById(R.id.travel_tickets_card_view_recycler);
                    MediumCardViewRecyclerAdapter mediumCardViewRecyclerAdapter3 = new MediumCardViewRecyclerAdapter(Arrays
                            .asList(new MediumCardItem(( R.drawable.falk),
                                    "بلیط اتوبوس", "اتوبوس بین شهری"), new MediumCardItem(( R.drawable.falk2),
                                    "بلیط هواپیما", "هواپیمای داخلی")));
                    LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(context);
                    linearLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);


                    activity.runOnUiThread(() -> {
                        travelTicketCardRecycler.setAdapter(mediumCardViewRecyclerAdapter3);
                        travelTicketCardRecycler.setLayoutManager(linearLayoutManager3);
                        travelTicketCardRecycler.setVisibility(View.VISIBLE);
                        title.setVisibility(View.VISIBLE);
                    });
                }).start();

            } catch (IllegalStateException ignored) {

            }
        }).start();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}