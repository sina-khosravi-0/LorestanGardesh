package com.example.lorestangardesh.ui.smallcard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.PathInterpolator;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lorestangardesh.R;
import com.example.lorestangardesh.databinding.FragmentSmallCardItemBinding;

import java.util.ArrayList;
import java.util.List;

public class SmallCardViewRecyclerAdapter extends RecyclerView.Adapter<SmallCardViewRecyclerAdapter.ViewHolder> {
    List<SmallCardItem> items;
    private Context context;
    public SmallCardViewRecyclerAdapter(List<SmallCardItem> items) {
        this.items = new ArrayList<>(items);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext().getApplicationContext();
        return new ViewHolder(FragmentSmallCardItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.text.setText(items.get(position).text);
        setAnimation(holder.itemView, position);
    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.card_enter_anim);
        animation.setDuration(500);
        animation.setInterpolator(new PathInterpolator(0.05f, 0.7f, 0.1f, 1f));
        viewToAnimate.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView text;

        public ViewHolder(FragmentSmallCardItemBinding binding) {
            super(binding.getRoot());
            text = binding.text;
        }
    }
}
