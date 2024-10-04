package com.example.lorestangardesh.ui.bus;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lorestangardesh.R;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BusTicketResultsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BusTicketResultsFragment extends Fragment {
    public static BusTicketResultsFragment newInstance() {
        BusTicketResultsFragment fragment = new BusTicketResultsFragment();
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
        return inflater.inflate(R.layout.bus_fragment_ticket_results, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView resultsList = view.findViewById(R.id.bus_ticket_results_recycler);
        BusResultsRecyclerAdapter busAdapter = new BusResultsRecyclerAdapter(Arrays.asList(
                new BusResultsItem(
                "تعاونی", "خرم آباد", "10:30", "تهران", "200,000", "VIP"),
                new BusResultsItem(
                "تعاونی", "خرم آباد", "10:30", "تهران", "200,000", "VIP"),
                new BusResultsItem(
                "تعاونی", "خرم آباد", "10:30", "تهران", "200,000", "VIP"),
                new BusResultsItem(
                "تعاونی", "خرم آباد", "10:30", "تهران", "200,000", "VIP")));
        resultsList.setAdapter(busAdapter);
        resultsList.setLayoutManager(new LinearLayoutManager(requireContext()));
    }
}