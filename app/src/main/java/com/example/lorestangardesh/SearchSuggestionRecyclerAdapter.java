package com.example.lorestangardesh;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lorestangardesh.databinding.ItemsMainSearchSuggestionBinding;
import com.example.lorestangardesh.ui.OnItemClickListener;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class SearchSuggestionRecyclerAdapter extends RecyclerView.Adapter<SearchSuggestionRecyclerAdapter.ViewHolder> {
    private List<SearchSuggestionItem> items;
    private OnItemClickListener onItemClickListener;

    public SearchSuggestionRecyclerAdapter(List<SearchSuggestionItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemsMainSearchSuggestionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).load(items.get(position).imageId).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
        holder.title.setText(items.get(position).title);
        holder.text.setText(items.get(position).text);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ShapeableImageView imageView;
        private TextView title;
        private TextView text;

        public ViewHolder(ItemsMainSearchSuggestionBinding binding) {
            super(binding.getRoot());
            imageView = binding.image;
            title = binding.title;
            text = binding.text;
            itemView.setOnClickListener(v -> {
                if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                    onItemClickListener.onClick(getAdapterPosition());
                }
            });
        }
    }
}
