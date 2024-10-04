package com.example.lorestangardesh.ui.tours.single;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lorestangardesh.R;
import com.example.lorestangardesh.ui.carousel.CarouselItem;
import com.example.lorestangardesh.ui.carousel.CarouselRecyclerAdapter;
import com.google.android.material.carousel.CarouselLayoutManager;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TourMainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TourMainFragment extends Fragment {
    public static TourMainFragment newInstance() {
        return new TourMainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tour_fragment_tour_main, container, false);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewCompat.setOnApplyWindowInsetsListener(requireActivity().findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        RecyclerView carousel = requireActivity().findViewById(R.id.carousel);
        CarouselRecyclerAdapter carouselRecyclerAdapter = new CarouselRecyclerAdapter(
                Arrays.asList(
                        new CarouselItem((R.drawable.falk), null, null),
                        new CarouselItem((R.drawable.falk2), null, null),
                        new CarouselItem((R.drawable.falk), null, null)
                )
        );
        carousel.setLayoutManager(new CarouselLayoutManager());
        carousel.setAdapter(carouselRecyclerAdapter);
    }
}