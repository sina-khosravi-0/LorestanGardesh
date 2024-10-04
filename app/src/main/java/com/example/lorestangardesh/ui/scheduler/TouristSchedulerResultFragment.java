package com.example.lorestangardesh.ui.scheduler;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lorestangardesh.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class TouristSchedulerResultFragment extends Fragment {
    private View dialogView;

    public static TouristSchedulerResultFragment newInstance() {
        TouristSchedulerResultFragment fragment = new TouristSchedulerResultFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tour_fragment_tourism_schedule_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ExtendedFloatingActionButton saveButton = requireActivity().findViewById(R.id.generate_schedule_fab);
        saveButton.setOnClickListener(v -> {
            Toast.makeText(requireContext(), getString(R.string.saved), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        requireActivity().findViewById(R.id.generate_schedule_fab).setOnClickListener(null);
    }
}