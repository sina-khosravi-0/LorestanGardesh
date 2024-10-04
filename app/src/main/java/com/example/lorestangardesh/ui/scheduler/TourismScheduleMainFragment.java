package com.example.lorestangardesh.ui.scheduler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.lorestangardesh.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class TourismScheduleMainFragment extends Fragment {
    public static TourismScheduleMainFragment newInstance() {
        return new TourismScheduleMainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tour_fragment_tourism_schedule_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        MaterialButton showSavedItems = view.findViewById(R.id.show_saved_items_button);
        ExtendedFloatingActionButton calculateButton = requireActivity().findViewById(R.id.generate_schedule_fab);
        showSavedItems.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, TouristSchedulerResultFragment.newInstance(), null)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit();

            calculateButton.hide();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ExtendedFloatingActionButton calculateButton = requireActivity().findViewById(R.id.generate_schedule_fab);
        calculateButton.show();
    }
}
