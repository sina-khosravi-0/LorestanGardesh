package com.example.lorestangardesh.ui.tours;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lorestangardesh.R;
import com.example.lorestangardesh.ui.carousel.CarouselRecyclerAdapter;
import com.example.lorestangardesh.ui.tours.single.TourActivity;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.Arrays;

public class ToursListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tours_activity_list);
        HorizontalScrollView scrollView = findViewById(R.id.filter_scroll_view);
        LinearLayout filterLinearLayout = findViewById(R.id.filter_linear_layout);
        ChipGroup filterChipGroup = findViewById(R.id.filter_chip_group);
        ChipGroup sortTypeChipGroup = findViewById(R.id.sort_type_chip_group);
        HorizontalScrollView transTypeScrollView = findViewById(R.id.filter_transport_type_scroll_view);
        ChipGroup filterTransTypeChipGroup = findViewById(R.id.filter_transport_type_chip_group);
        Chip sortTypeChip = findViewById(R.id.sort_type_chip);

        scrollView.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        filterLinearLayout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        filterChipGroup.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        sortTypeChipGroup.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        transTypeScrollView.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        filterTransTypeChipGroup.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        sortTypeChip.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(this, sortTypeChip);
            popupMenu.inflate(R.menu.sort_tours_menu);
            popupMenu.setOnMenuItemClickListener(item -> {
                sortTypeChip.setText(item.getTitle());
                return true;
            });
            popupMenu.show();
        });

        RecyclerView toursRecycler = findViewById(R.id.tour_items_recycler);
        ToursRecyclerAdapter toursAdapter = new ToursRecyclerAdapter(Arrays.asList(
                new ToursListItem(R.drawable.falk2, "نام تور", "آژانس مسافرتی",
                        "مکان تور", "طبیعت گردی", "اتوبوس", "10:30, 1403/01/05", "3 روز", "150,000")
        ));
        toursAdapter.setOnClickListener(position -> {
            System.out.println("hi");
            startActivity(new Intent(this, TourActivity.class));
        });
        toursRecycler.setAdapter(toursAdapter);
        toursRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
}
