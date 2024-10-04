package com.example.lorestangardesh.ui.tours;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lorestangardesh.databinding.ToursFragmentListItemBinding;
import com.example.lorestangardesh.ui.OnItemClickListener;

import java.util.List;

public class ToursRecyclerAdapter extends RecyclerView.Adapter<ToursRecyclerAdapter.ViewHolder> {
    private List<ToursListItem> items;
    private OnItemClickListener onClickListener;

    public ToursRecyclerAdapter(List<ToursListItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ToursRecyclerAdapter.ViewHolder(ToursFragmentListItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).load(items.get(position).imageId).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
        holder.tourName.setText(items.get(position).tourName);
        holder.travelAgency.setText(items.get(position).travelAgency);
        holder.tourLocation.setText(items.get(position).tourLocation);
        holder.tourType.setText(items.get(position).tourType);
        holder.transportType.setText(items.get(position).transportType);
        holder.departureDateTime.setText(items.get(position).departureDateTime);
        holder.duration.setText(items.get(position).duration);
        holder.price.setText(items.get(position).price);
        System.out.println(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnClickListener(OnItemClickListener listener) {
        this.onClickListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView tourName;
        private TextView travelAgency;
        private TextView tourLocation;
        private TextView tourType;
        private TextView transportType;
        private TextView departureDateTime;
        private TextView duration;
        private TextView price;

        public ViewHolder(@NonNull ToursFragmentListItemBinding binding) {
            super(binding.getRoot());
            imageView = binding.image;
            tourName = binding.tourName;
            travelAgency = binding.travelAgency;
            tourLocation = binding.tourLocation;
            tourType = binding.tourType;
            transportType = binding.transportType;
            departureDateTime = binding.departureDateTime;
            duration = binding.duration;
            price = binding.price;
            itemView.setOnClickListener(v -> {
                if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                    onClickListener.onClick(getAdapterPosition());
                }
            });
        }
    }
}
