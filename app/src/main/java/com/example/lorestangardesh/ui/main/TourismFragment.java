package com.example.lorestangardesh.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lorestangardesh.ui.PlaceEventFragment;
import com.example.lorestangardesh.R;
import com.example.lorestangardesh.ui.TourismScheduleActivity;
import com.google.android.material.card.MaterialCardView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TourismFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TourismFragment extends Fragment {

    public static TourismFragment newInstance() {
        return new TourismFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tourism, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MaterialCardView tourSchedulerCard = view.findViewById(R.id.tour_scheduler_card);
        MaterialCardView suggestCard = view.findViewById(R.id.suggest_card);
        tourSchedulerCard.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), TourismScheduleActivity.class));
        });
        suggestCard.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), PlaceEventFragment.class));
        });
    }
}