package com.example.lorestangardesh.ui.main;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lorestangardesh.R;
import com.example.lorestangardesh.ui.PlaceEventActivity;
import com.example.lorestangardesh.ui.searchresult.SearchResultItem;
import com.example.lorestangardesh.ui.searchresult.SearchResultRecyclerAdapter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.Arrays;

public class SearchFragment extends Fragment {

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
        RecyclerView searchResultRecyclerView = view.findViewById(R.id.tour_items_recycler);

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

        SearchResultRecyclerAdapter searchResultAdapter = new SearchResultRecyclerAdapter(Arrays.asList(
                new SearchResultItem(R.drawable.falk, "فلک الافلاک",
                        "آثار باستانی خرم آباد", "قابل بازدید"),
                new SearchResultItem(R.drawable.falk2, "آبشار بیشه",
                        "طبیعت لرستان", "قابل بازدید"),
                new SearchResultItem(R.drawable.falk, "فلک الافلاک",
                        "آثار باستانی خرم آباد", "قابل بازدید"),
                new SearchResultItem(R.drawable.falk2, "آبشار بیشه",
                        "طبیعت لرستان", "قابل بازدید"),
                new SearchResultItem(R.drawable.falk, "فلک الافلاک",
                        "آثار باستانی خرم آباد", "قابل بازدید"),
                new SearchResultItem(R.drawable.falk2, "آبشار بیشه",
                        "طبیعت لرستان", "قابل بازدید")));
        searchResultRecyclerView.setAdapter(searchResultAdapter);
        searchResultRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        searchResultAdapter.setOnItemClickListener(position -> {
            startActivity(new Intent(requireActivity(), PlaceEventActivity.class));
        });

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
}