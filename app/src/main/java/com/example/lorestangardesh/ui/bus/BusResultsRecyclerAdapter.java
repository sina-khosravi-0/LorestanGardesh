package com.example.lorestangardesh.ui.bus;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lorestangardesh.databinding.BusFragmentTicketSearchResultBinding;

import java.util.List;

public class BusResultsRecyclerAdapter extends RecyclerView.Adapter<BusResultsRecyclerAdapter.ViewHolder> {
    private List<BusResultsItem> items;
    public BusResultsRecyclerAdapter(List<BusResultsItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public BusResultsRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BusResultsRecyclerAdapter.ViewHolder(
                BusFragmentTicketSearchResultBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false));
    }

    @Override
    public void onBindViewHolder(@NonNull BusResultsRecyclerAdapter.ViewHolder holder, int position) {
        holder.agency.setText(items.get(position).agency);
        holder.sourceLocation.setText(items.get(position).sourceLocation);
        holder.departureTime.setText(items.get(position).departureTime);
        holder.arrivalLocation.setText(items.get(position).arrivalLocation);
        holder.price.setText(items.get(position).price);
        holder.vip.setText(items.get(position).vip);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView agency;
        private TextView sourceLocation;
        private TextView departureTime;
        private TextView arrivalLocation;
        private TextView price;
        private TextView vip;

        public ViewHolder(BusFragmentTicketSearchResultBinding binding) {
            super(binding.getRoot());
            agency = binding.agency;
            sourceLocation = binding.sourceLocation;
            departureTime = binding.departureTime;
            arrivalLocation = binding.destinationLocation;
            price = binding.price;
            vip = binding.vip;
        }
    }
}
