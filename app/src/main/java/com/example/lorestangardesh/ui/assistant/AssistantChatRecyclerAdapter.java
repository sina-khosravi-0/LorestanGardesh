package com.example.lorestangardesh.ui.assistant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lorestangardesh.databinding.AssistantFragmentBotBubbleBinding;
import com.example.lorestangardesh.databinding.AssistantFragmentUserBubbleBinding;
import com.example.lorestangardesh.ui.mediumcard.MediumCardViewRecyclerAdapter;

import java.util.ArrayList;

public class AssistantChatRecyclerAdapter extends RecyclerView.Adapter {
    private ArrayList<AssistantChatItem> items;
    private AssistantChatRecyclerAdapter.OnBubbleCreatedListener onItemClickListener = (v, position) -> {
    };
    public AssistantChatRecyclerAdapter(ArrayList<AssistantChatItem> items) {
        this.items = items;
    }

    public void addItem(AssistantChatItem item) {
        items.add(item);
    }
    public interface OnBubbleCreatedListener {
        void onClick(View view, int position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new AssistantChatRecyclerAdapter.BotViewHolder(AssistantFragmentBotBubbleBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        } else {
            return new AssistantChatRecyclerAdapter.UserViewHolder(AssistantFragmentUserBubbleBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (items.get(position).incoming) {
            ((BotViewHolder) holder).botBubble.setText(items.get(position).text);
            onItemClickListener.onClick(holder.itemView, position);
        } else {
            ((UserViewHolder) holder).userBubble.setText(items.get(position).text);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position).incoming) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public void setOnBotBubbleCreatedListener(OnBubbleCreatedListener onBubbleCreatedListener) {
        this.onItemClickListener = onBubbleCreatedListener;
    }
    public static class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView userBubble;

        public UserViewHolder(AssistantFragmentUserBubbleBinding binding) {
            super(binding.getRoot());
            this.userBubble = binding.userBubble;

        }
    }

    public static class BotViewHolder extends RecyclerView.ViewHolder {
        private TextView botBubble;

        public BotViewHolder(AssistantFragmentBotBubbleBinding binding) {
            super(binding.getRoot());
            this.botBubble = binding.botBubble;

        }
    }

}
