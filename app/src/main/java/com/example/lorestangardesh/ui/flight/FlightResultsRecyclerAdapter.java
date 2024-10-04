package com.example.lorestangardesh.ui.flight;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lorestangardesh.databinding.FlightFragmentTicketSearchResultBinding;

import java.util.List;

public class FlightResultsRecyclerAdapter extends RecyclerView.Adapter<FlightResultsRecyclerAdapter.ViewHolder> {
    private List<FlightTicketItem> items;
    public FlightResultsRecyclerAdapter(List<FlightTicketItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public FlightResultsRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FlightResultsRecyclerAdapter.ViewHolder(FlightFragmentTicketSearchResultBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FlightResultsRecyclerAdapter.ViewHolder holder, int position) {
        holder.agency.setText(items.get(position).agency);
        holder.sourceLocation.setText(items.get(position).sourceLocation);
        holder.departureTime.setText(items.get(position).departureTime);
        holder.arrivalLocation.setText(items.get(position).arrivalLocation);
        holder.arrivalTime.setText(items.get(position).arrivalTime);
        holder.price.setText(items.get(position).price);
        holder.ticketType.setText(items.get(position).ticketType);
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
        private TextView arrivalTime;
        private TextView price;
        private TextView ticketType;

        public ViewHolder(FlightFragmentTicketSearchResultBinding binding) {
            super(binding.getRoot());
            agency = binding.agency;
            sourceLocation = binding.sourceLocation;
            departureTime = binding.departureTime;
            arrivalLocation = binding.destinationLocation;
            arrivalTime = binding.arrivalTime;
            price = binding.price;
            ticketType = binding.ticketType;
        }
    }
}
