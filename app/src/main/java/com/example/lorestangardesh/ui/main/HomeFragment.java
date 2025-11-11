package com.example.lorestangardesh.ui.main;

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
import com.example.lorestangardesh.db.DataManager;
import com.example.lorestangardesh.db.DatabaseHandlerSingleton;
import com.example.lorestangardesh.db.GalleryItem;
import com.example.lorestangardesh.db.Location;
import com.example.lorestangardesh.db.PromptObject;
import com.example.lorestangardesh.statics.TypeMapper;
import com.example.lorestangardesh.ui.PlaceEventActivity;
import com.example.lorestangardesh.R;
import com.example.lorestangardesh.ui.assistant.AssistantChatActivity;
import com.example.lorestangardesh.ui.assistant.AssistantChatRecyclerAdapter;
import com.example.lorestangardesh.ui.carousel.CarouselItem;
import com.example.lorestangardesh.ui.carousel.CarouselRecyclerAdapter;
import com.example.lorestangardesh.ui.mediumcard.MediumCardItem;
import com.example.lorestangardesh.ui.mediumcard.MediumCardViewRecyclerAdapter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.carousel.CarouselLayoutManager;
import com.google.android.material.carousel.CarouselSnapHelper;
import com.google.android.material.carousel.HeroCarouselStrategy;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import io.noties.markwon.Markwon;
import io.noties.markwon.ext.tables.TablePlugin;

public class HomeFragment extends Fragment {


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
        return inflater.inflate(R.layout.mainpage_fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MaterialButton realtimeButton = view.findViewById(R.id.realtime_button);
        MaterialButton assistantButton = view.findViewById(R.id.assistant_button);
        realtimeButton.setOnClickListener(v -> {
            startActivity(new Intent(requireActivity(), RealtimeSuggestionActivity.class));
        });

        new Thread(() -> {
//            Glide.get(requireContext()).clearDiskCache();
            try {
                new Thread(() -> {
                    RecyclerView carouselRecyclerView = view.findViewById(R.id.carousel_recycler_view);
                    try {
                        DataManager.getInstance().fetchAllLocations();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    List<GalleryItem> allPhotos = DataManager.getInstance().getAllPhotos();
                    CarouselRecyclerAdapter carouselRecyclerAdapter = new CarouselRecyclerAdapter(
                            allPhotos.stream().map(galleryItem -> new CarouselItem(galleryItem.getPath(), galleryItem.getTitle(),
                                    TypeMapper.getInstance(null).codeToStringResMap.get(galleryItem.getLocationType()))).collect(Collectors.toList())
                            , requireActivity());

                    CarouselLayoutManager carouselManager = new CarouselLayoutManager(new HeroCarouselStrategy());
                    carouselManager.setCarouselAlignment(CarouselLayoutManager.ALIGNMENT_START);
                    SnapHelper snapHelper = new CarouselSnapHelper();
                    snapHelper.attachToRecyclerView(carouselRecyclerView);

                    requireActivity().runOnUiThread(() -> {
                        carouselRecyclerView.setAdapter(carouselRecyclerAdapter);
                        carouselRecyclerView.setLayoutManager(carouselManager);
                        carouselRecyclerView.setVisibility(View.VISIBLE);
                    });

                    TextView title = view.findViewById(R.id.near_you_title);
                    List<Location> allLocations = DataManager.getInstance().getAllLocations();
                    RecyclerView nearYouCardRecycler = view.findViewById(R.id.near_you_card_view_recycler);
                    MediumCardViewRecyclerAdapter mediumCardViewRecyclerAdapter1 = new MediumCardViewRecyclerAdapter(
                            allLocations.stream().map(location -> new MediumCardItem(location.getPhotos().get(0), location.getTitle(),
                                    String.format("%1.100s", location.getDescription()))).collect(Collectors.toList())
                    );
                    LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(requireContext());
                    linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);

                    mediumCardViewRecyclerAdapter1.setOnItemClickListener((v, position) -> {
                        Intent intent = new Intent(getActivity(), PlaceEventActivity.class);
                        intent.putExtra("id", Integer.valueOf(allLocations.get(position).getId()));
                        startActivity(intent);
                    });

                    requireActivity().runOnUiThread(() -> {
                        nearYouCardRecycler.setAdapter(mediumCardViewRecyclerAdapter1);
                        nearYouCardRecycler.setLayoutManager(linearLayoutManager1);
                        nearYouCardRecycler.setVisibility(View.VISIBLE);
                        title.setVisibility(View.VISIBLE);
                    });
                }).start();

                assistantButton.setOnClickListener(v -> {
                    startActivity(new Intent(requireActivity(), AssistantChatActivity.class));
                });

//                new Thread(() -> {
//                    TextView title = view.findViewById(R.id.tours_title);
//                    RecyclerView toursCardRecycler = view.findViewById(R.id.tours_card_view_recycler);
//                    LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(context);
//                    linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
//                    MediumCardViewRecyclerAdapter mediumCardViewRecyclerAdapter2 = new MediumCardViewRecyclerAdapter(Arrays
//                            .asList(new MediumCardItem((R.drawable.falk),
//                                            "تور گردشگری", "تور گردشگری نوروزی"),
//                                    new MediumCardItem((R.drawable.falk2),
//                                            "تور", "تور گردشگری خارجی")));
//                    mediumCardViewRecyclerAdapter2.setOnItemClickListener(v -> {
//                        startActivity(new Intent(getContext(), TourActivity.class));
//
//                    });
//                    activity.runOnUiThread(() -> {
//                        toursCardRecycler.setAdapter(mediumCardViewRecyclerAdapter2);
//                        toursCardRecycler.setLayoutManager(linearLayoutManager2);
//                        toursCardRecycler.setVisibility(View.VISIBLE);
//                        title.setVisibility(View.VISIBLE);
//                    });
//                }).start();
//
//                new Thread(() -> {
//                    TextView title = view.findViewById(R.id.travel_tickets_title);
//                    RecyclerView travelTicketCardRecycler = view.findViewById(R.id.travel_tickets_card_view_recycler);
//                    MediumCardViewRecyclerAdapter mediumCardViewRecyclerAdapter3 = new MediumCardViewRecyclerAdapter(Arrays
//                            .asList(new MediumCardItem(( R.drawable.falk),
//                                    "بلیط اتوبوس", "اتوبوس بین شهری"), new MediumCardItem(( R.drawable.falk2),
//                                    "بلیط هواپیما", "هواپیمای داخلی")));
//                    LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(context);
//                    linearLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
//
//
//                    activity.runOnUiThread(() -> {
//                        travelTicketCardRecycler.setAdapter(mediumCardViewRecyclerAdapter3);
//                        travelTicketCardRecycler.setLayoutManager(linearLayoutManager3);
//                        travelTicketCardRecycler.setVisibility(View.VISIBLE);
//                        title.setVisibility(View.VISIBLE);
//                    });
//                }).start();

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