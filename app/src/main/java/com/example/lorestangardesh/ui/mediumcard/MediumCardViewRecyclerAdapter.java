package com.example.lorestangardesh.ui.mediumcard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lorestangardesh.R;
import com.example.lorestangardesh.databinding.ItemsFragmentMediumCardItemBinding;
import com.example.lorestangardesh.values.Constants;

import java.util.ArrayList;
import java.util.List;

public class MediumCardViewRecyclerAdapter extends RecyclerView.Adapter<MediumCardViewRecyclerAdapter.ViewHolder> {
    private List<MediumCardItem> items;
    private Context context;
    private OnItemClickListener onItemClickListener = (v, position) -> {
    };


    public MediumCardViewRecyclerAdapter(List<MediumCardItem> items) {
        this.items = new ArrayList<>(items);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext().getApplicationContext();
        return new ViewHolder(ItemsFragmentMediumCardItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.image.setImageBitmap(items.get(position).image);
        Glide.with(holder.itemView.getContext()).load(items.get(position).photo).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.image);
        holder.title.setText(items.get(position).title);
        holder.description.setText(items.get(position).description);
        holder.itemView.setOnClickListener(view -> {
            onItemClickListener.onClick(holder.itemView, position);
        });
//        setAnimation(holder.itemView, position);
    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.card_enter_anim);
        animation.setDuration(Constants.REVEAL_ANIMATION_DURATION);
        animation.setInterpolator(new PathInterpolator(0.05f, 0.7f, 0.1f, 1f));
        viewToAnimate.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final TextView title;
        private final TextView description;

        public ViewHolder(ItemsFragmentMediumCardItemBinding itemBinding) {
            super(itemBinding.getRoot());
            setIsRecyclable(false);
            image = itemBinding.cardImage;
            image.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            title = itemBinding.cardTitle;
            description = itemBinding.cardDescription;
        }

        public ImageView getImage() {
            return image;
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getDescription() {
            return description;
        }
    }
}
