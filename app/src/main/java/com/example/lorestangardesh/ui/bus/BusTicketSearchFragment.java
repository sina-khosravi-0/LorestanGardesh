package com.example.lorestangardesh.ui.bus;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lorestangardesh.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BusTicketSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BusTicketSearchFragment extends Fragment {
    public static BusTicketSearchFragment newInstance() {
        BusTicketSearchFragment fragment = new BusTicketSearchFragment();
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
        return inflater.inflate(R.layout.bus_fragment_ticket_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


    }
}