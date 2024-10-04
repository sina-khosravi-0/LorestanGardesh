package com.example.lorestangardesh.ui.hotel;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lorestangardesh.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.Arrays;

public class HotelsListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotels_activity_list);
        HorizontalScrollView scrollView = findViewById(R.id.filter_scroll_view);
        LinearLayout filterLinearLayout = findViewById(R.id.filter_linear_layout);
        ChipGroup sortTypeChipGroup = findViewById(R.id.sort_type_chip_group);
        ChipGroup filterStarChipGroup = findViewById(R.id.filter_star_chip_group);
        ChipGroup filterGuestNumberChipGroup = findViewById(R.id.filter_guest_number_chip_group);
        Chip sortTypeChip = findViewById(R.id.sort_type_chip);
        Chip filterStarChip = findViewById(R.id.filter_star_chip);
        Chip filterBedsNumberChip = findViewById(R.id.filter_guest_number_chip);

        scrollView.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        filterLinearLayout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        filterStarChipGroup.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        sortTypeChipGroup.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        filterGuestNumberChipGroup.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        sortTypeChip.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(this, sortTypeChip);
            popupMenu.inflate(R.menu.sort_tours_menu);
            popupMenu.setOnMenuItemClickListener(item -> {
                sortTypeChip.setText(item.getTitle());
                return true;
            });
            popupMenu.show();
        });
        filterStarChip.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(this, filterStarChip);
            popupMenu.inflate(R.menu.hotel_stars_menu);
            popupMenu.setOnMenuItemClickListener(item -> {
                filterStarChip.setText(item.getTitle());
                return true;
            });
            popupMenu.show();
        });
        filterBedsNumberChip.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(this, filterBedsNumberChip);
            popupMenu.inflate(R.menu.hotel_beds_menu);
            popupMenu.setOnMenuItemClickListener(item -> {
                filterBedsNumberChip.setText(item.getTitle());
                return true;
            });
            popupMenu.show();
        });

        RecyclerView toursRecycler = findViewById(R.id.hotels_recycler);
        HotelReservationsRecyclerAdapter toursAdapter = new HotelReservationsRecyclerAdapter(
                Arrays.asList(
                        new HotelReservationItem(R.drawable.falk2, "نام هتل", getString(R.string._3_star),
                                "۳,۵/۵", "خرم آباد",
                                "لرستان خرم آباد میدان استانداری جنب استانداری",
                                "۲ تخته", "۲ اتاق باقی مانده", "1,140,000"),
                        new HotelReservationItem(R.drawable.falk2, "نام هتل", getString(R.string._3_star),
                                "۳,۵/۵", "خرم آباد",
                                "لرستان خرم آباد میدان استانداری جنب استانداری",
                                "۲ تخته", "۲ اتاق باقی مانده", "1,140,000")
                ));

        toursRecycler.setAdapter(toursAdapter);
        toursRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
}
