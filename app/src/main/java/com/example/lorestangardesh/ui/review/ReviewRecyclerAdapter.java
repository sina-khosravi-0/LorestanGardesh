package com.example.lorestangardesh.ui.review;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lorestangardesh.databinding.FragmentReviewItemBinding;

import java.util.ArrayList;
import java.util.List;

public class ReviewRecyclerAdapter extends RecyclerView.Adapter<ReviewRecyclerAdapter.ViewHolder> {
    private List<ReviewItem> items;

    public ReviewRecyclerAdapter(List<ReviewItem> items) {
        this.items = new ArrayList<>(items);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("oncreate");
        return new ViewHolder(FragmentReviewItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.profilePicture.setImageBitmap(items.get(position).profilePicture);
        holder.usernameTextView.setText(items.get(position).username);
        holder.reviewText.setText(items.get(position).text);
        holder.likeButton.setSelected(items.get(position).isLiked);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView profilePicture;
        private final TextView usernameTextView;
        private final ImageButton likeButton;
        private final TextView reviewText;

        public ViewHolder(@NonNull FragmentReviewItemBinding binding) {
            super(binding.getRoot());
            profilePicture = binding.pfpImageView;
            usernameTextView = binding.usernameTextView;
            likeButton = binding.likeImageButton;
            reviewText = binding.textTextView;
        }
    }
}
