package com.example.lorestangardesh.ui.flight;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lorestangardesh.R;

import java.util.Arrays;

public class FlightTicketResultsFragment extends Fragment {
    public static FlightTicketResultsFragment newInstance() {
        return new FlightTicketResultsFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.flight_fragment_ticket_results, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView resultsList = view.findViewById(R.id.flight_ticket_results_recycler);
        FlightResultsRecyclerAdapter busAdapter = new FlightResultsRecyclerAdapter(Arrays.asList(
                new FlightTicketItem(
                        "تعاونی", "خرم آباد", "10:30", "تهران", "12:00","200,000", "Economy"),
                new FlightTicketItem(
                        "تعاونی", "خرم آباد", "10:30", "تهران", "12:00","200,000", "Economy"),
                new FlightTicketItem(
                        "تعاونی", "خرم آباد", "10:30", "تهران", "12:00","200,000", "Economy"),
                new FlightTicketItem(
                        "تعاونی", "خرم آباد", "10:30", "تهران", "12:00","200,000", "Economy")));
        resultsList.setAdapter(busAdapter);
        resultsList.setLayoutManager(new LinearLayoutManager(requireContext()));
    }
}
