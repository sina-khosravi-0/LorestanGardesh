package com.example.lorestangardesh.ui.searchresult;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lorestangardesh.databinding.SearchFragmentSearchResultBinding;

import java.util.List;

public class SearchResultRecyclerAdapter extends RecyclerView.Adapter<SearchResultRecyclerAdapter.ViewHolder> {
    private List<SearchResultItem> items;
    public SearchResultRecyclerAdapter(List<SearchResultItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(SearchFragmentSearchResultBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).load(items.get(position).imageId).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
        holder.titleTextView.setText(items.get(position).title);
        holder.supportTextView.setText(items.get(position).supportText);
        holder.openStateTextView.setText(items.get(position).openState);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView titleTextView;
        private TextView supportTextView;
        private TextView openStateTextView;

        public ViewHolder(SearchFragmentSearchResultBinding binding) {
            super(binding.getRoot());
            imageView = binding.image;
            titleTextView = binding.title;
            supportTextView = binding.supportText;
            openStateTextView = binding.openState;
        }
    }
}
