package com.example.lorestangardesh.ui.main;

import android.animation.LayoutTransition;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lorestangardesh.MainActivity;
import com.example.lorestangardesh.R;
import com.example.lorestangardesh.db.DataManager;
import com.example.lorestangardesh.db.Location;
import com.example.lorestangardesh.statics.Intents;
import com.example.lorestangardesh.ui.PlaceEventActivity;
import com.example.lorestangardesh.ui.searchresult.SearchResultItem;
import com.example.lorestangardesh.ui.searchresult.SearchResultRecyclerAdapter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import org.json.JSONException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SearchFragment extends Fragment {
    private RecyclerView searchResultRecyclerView;
    private List<Location> locations;
    private final BroadcastReceiver searchBr = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            new Thread(() -> {
                if (MainActivity.getQueryTerm().isEmpty()) {
                    try {
                        DataManager.getInstance().fetchAllLocations();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    locations = DataManager.getInstance().getAllLocations();

                } else {
                    locations = DataManager.getInstance().getSearchResults(MainActivity.getQueryTerm());
                }

                SearchFragment.this.requireActivity().runOnUiThread(() -> {
                    if (locations.isEmpty()) {
                        Toast.makeText(requireContext(), R.string.nothing_found, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    SearchResultRecyclerAdapter searchResultAdapter = new SearchResultRecyclerAdapter(locations.stream().map(location -> {
                        String photo = "";
                        if (location.getPhotos() !=null && !location.getPhotos().isEmpty()) {
                            photo = location.getPhotos().get(0);
                        }
                        return new SearchResultItem(photo, location.getTitle(),
                                String.format("%1.100s", location.getDescription()), "قابل بازدید");
                    }).collect(Collectors.toList()));
                    searchResultRecyclerView.setAdapter(searchResultAdapter);
                    searchResultAdapter.setOnItemClickListener(position -> {
                        Intent intention = new Intent(getActivity(), PlaceEventActivity.class);
                        intention.putExtra("id", Integer.valueOf(locations.get(position).getId()));
                        startActivity(intention);
                    });
                });
            }).start();

        }
    };

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.mainpage_fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        HorizontalScrollView scrollView = view.findViewById(R.id.filter_scroll_view);
        LinearLayout filterLinearLayout = view.findViewById(R.id.filter_linear_layout);
        ChipGroup filterChipGroup = view.findViewById(R.id.filter_chip_group);
        ChipGroup sortTypeChipGroup = view.findViewById(R.id.sort_type_chip_group);
        Chip sortTypeChip = view.findViewById(R.id.sort_type_chip);
        searchResultRecyclerView = view.findViewById(R.id.tour_items_recycler);

        sortTypeChip.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(requireContext(), sortTypeChip);
            popupMenu.inflate(R.menu.sort_place_type_menu);
            popupMenu.setOnMenuItemClickListener(item -> {
                sortTypeChip.setText(item.getTitle());
                return true;
            });
            popupMenu.show();
        });
        scrollView.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        filterLinearLayout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        filterChipGroup.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        sortTypeChipGroup.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        List<Location> allLocations = DataManager.getInstance().getAllLocations();

        if (MainActivity.getQueryTerm().isEmpty()) {
            SearchResultRecyclerAdapter searchResultAdapter = new SearchResultRecyclerAdapter(allLocations.stream().map(location -> {
                String photo = "";
                if (location.getPhotos() !=null && !location.getPhotos().isEmpty()) {
                    photo = location.getPhotos().get(0);
                }
                return new SearchResultItem(photo, location.getTitle(),
                        String.format("%1.100s", location.getDescription()), "");
            }).collect(Collectors.toList()));
            searchResultRecyclerView.setAdapter(searchResultAdapter);
            searchResultRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
            searchResultAdapter.setOnItemClickListener(position -> {
                Intent intent = new Intent(getActivity(), PlaceEventActivity.class);
                intent.putExtra("id", Integer.valueOf(allLocations.get(position).getId()));
                startActivity(intent);
            });
        } else {
            new Thread(() -> {
                List<Location> locations = DataManager.getInstance().getSearchResults(MainActivity.getQueryTerm());

                SearchFragment.this.requireActivity().runOnUiThread(() -> {
                    if (locations.isEmpty()) {
                        Toast.makeText(requireContext(), R.string.nothing_found, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    SearchResultRecyclerAdapter searchResultAdapter = new SearchResultRecyclerAdapter(locations.stream().map(location -> {
                        String photo = "";
                        if (location.getPhotos() !=null && !location.getPhotos().isEmpty()) {
                            photo = location.getPhotos().get(0);
                        }
                        return new SearchResultItem(photo, location.getTitle(),
                                String.format("%1.100s", location.getDescription()), "قابل بازدید");
                    }).collect(Collectors.toList()));
                    searchResultRecyclerView.setAdapter(searchResultAdapter);
                    searchResultRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                    searchResultAdapter.setOnItemClickListener(position -> {
                        Intent intent = new Intent(getActivity(), PlaceEventActivity.class);
                        intent.putExtra("id", Integer.valueOf(locations.get(position).getId()));
                        startActivity(intent);
                    });
                });

            }).start();
        }


//        for (int i = 0; i < filterChipGroup.getChildCount(); i++) {
//            Chip chip = (Chip) filterChipGroup.getChildAt(i);
//            chip.setOnCheckedChangeListener(((buttonView, isChecked) -> {
//                int index = filterChipGroup.indexOfChild(chip);
//                filterChipGroup.removeView(buttonView);
//                filterChipGroup.addView(buttonView, index);
//            }));
//        }
//        MaterialCardView tourSchedulerCard = view.findViewById(R.id.tour_scheduler_card);
//        MaterialCardView suggestCard = view.findViewById(R.id.suggest_card);
//        tourSchedulerCard.setOnClickListener(v -> {
//            startActivity(new Intent(getContext(), TourismScheduleActivity.class));
//        });
//        suggestCard.setOnClickListener(v -> {
//            startActivity(new Intent(getContext(), PlaceEventFragment.class));
//        });
    }

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(requireActivity());
        lbm.registerReceiver(searchBr, new IntentFilter(Intents.SEARCH_INTENT));
    }
}