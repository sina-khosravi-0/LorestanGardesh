package com.example.lorestangardesh.ui.carousel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lorestangardesh.R;
import com.example.lorestangardesh.databinding.ItemsFragmentCarouselItemBinding;
import com.example.lorestangardesh.values.Constants;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class CarouselRecyclerAdapter extends RecyclerView.Adapter<CarouselRecyclerAdapter.ViewHolder> {
    private List<CarouselItem> items;
    private OnItemClickListener onItemClickListener = v -> {
    };
    private Context context;
    private TabLayout tabLayout = null;
    private RecyclerView recyclerView;

    public CarouselRecyclerAdapter(List<CarouselItem> items) {

        this.items = items;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.context = recyclerView.getContext().getApplicationContext();
        this.recyclerView = recyclerView;
//        if (tabLayout != null) {
//            items.forEach(item -> {
//                TabLayout.Tab tab = tabLayout.newTab();
//                Drawable icon = AppCompatResources.getDrawable(context, R.drawable.carousel_tab_selected);
//                assert icon != null;
//                icon.setColorFilter(new ColorMatrixColorFilter(Constants.NEGATIVE_COLOR_MATRIX));
//                tab.setIcon(icon);
//                tabLayout.addTab(tab);
//            });
//        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsFragmentCarouselItemBinding binding = ItemsFragmentCarouselItemBinding.inflate(LayoutInflater.from(
                parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.image.setImageBitmap(items.get(position).image);
        Glide.with(recyclerView.getContext()).load(items.get(position).imageId).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.image);
        holder.title.setText(items.get(position).title);
        holder.description.setText(items.get(position).description);
        holder.image.setTransitionName("image_" + position);
        holder.itemView.setOnClickListener(view -> {
            onItemClickListener.onClick(holder.image);
        });
//        setAnimation(holder.itemView, position);
        if (items.get(position).title == null && items.get(position).description == null) {
            holder.textContainer.setVisibility(View.GONE);
        }
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

    /**
     * Should be set before attaching to RecyclerView
     */
    public void setTabLayout(TabLayout tabLayout) {
        this.tabLayout = tabLayout;
    }

    public interface OnItemClickListener {
        void onClick(ImageView imageView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final TextView title;
        private final TextView description;
        private final LinearLayout textContainer;


        public ViewHolder(ItemsFragmentCarouselItemBinding binding) {
            super(binding.getRoot());
            image = binding.carouselImageView;
            image.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            title = binding.carouselTitle;
            description = binding.carouselDescription;
            textContainer = binding.carouselItemTextContainer;
        }
    }
}
