package com.example.lorestangardesh.ui.tour;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lorestangardesh.R;
import com.google.android.material.button.MaterialButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReserveTourFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReserveTourFragment extends Fragment {
    public static ReserveTourFragment newInstance() {
        ReserveTourFragment fragment = new ReserveTourFragment();
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
        return inflater.inflate(R.layout.tour_fragment_reserve_tour, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((ViewGroup) view).getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        ((ViewGroup) view.findViewById(R.id.passenger_items)).getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        MaterialButton addPassengerButton = view.findViewById(R.id.add_passenger);
        getChildFragmentManager().beginTransaction().add(R.id.passenger_items, PassengerFragment.newInstance()).commitNow();

        addPassengerButton.setOnClickListener(v -> {
            getChildFragmentManager().beginTransaction().add(R.id.passenger_items, PassengerFragment.newInstance()).commit();
        });
    }
}