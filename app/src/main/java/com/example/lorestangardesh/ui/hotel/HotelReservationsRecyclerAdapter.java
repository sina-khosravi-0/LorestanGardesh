package com.example.lorestangardesh.ui.hotel;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lorestangardesh.databinding.HotelsFragmentItemBinding;

import java.util.List;

public class HotelReservationsRecyclerAdapter extends RecyclerView.Adapter<HotelReservationsRecyclerAdapter.ViewHolder> {
    private List<HotelReservationItem> items;
    public HotelReservationsRecyclerAdapter(List<HotelReservationItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public HotelReservationsRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HotelReservationsRecyclerAdapter.ViewHolder(HotelsFragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HotelReservationsRecyclerAdapter.ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).load(items.get(position).imageId).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
        holder.hotelName.setText(items.get(position).hotelName);
        holder.star.setText(items.get(position).star);
        holder.rating.setText(items.get(position).rating);
        holder.city.setText(items.get(position).city);
        holder.location.setText(items.get(position).location);
        holder.beds.setText(items.get(position).beds);
        holder.remainingWarning.setText(items.get(position).remainingWarning);
        holder.price.setText(items.get(position).price);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView hotelName;
        private final TextView star;
        private final TextView rating;
        private final TextView city;
        private final TextView location;
        private final TextView beds;
        private final TextView remainingWarning;
        private final TextView price;

        public ViewHolder(HotelsFragmentItemBinding binding) {
            super(binding.getRoot());
            imageView = binding.image;
            hotelName = binding.hotelName;
            star = binding.star;
            rating = binding.rating;
            city = binding.city;
            location = binding.location;
            beds = binding.beds;
            remainingWarning = binding.remainingWarning;
            price = binding.price;
        }
    }
}
