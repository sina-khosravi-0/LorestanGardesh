package com.example.lorestangardesh.ui.assistant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lorestangardesh.databinding.AssistantFragmentChatBubbleBinding;

import java.util.ArrayList;

public class AssistantChatRecyclerAdapter extends RecyclerView.Adapter<AssistantChatRecyclerAdapter.ViewHolder> {
    private ArrayList<AssistantChatItem> items;

    public AssistantChatRecyclerAdapter(ArrayList<AssistantChatItem> items) {
        this.items = items;
    }

    public void addItem(AssistantChatItem item) {
        items.add(item);
    }

    @NonNull
    @Override
    public AssistantChatRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AssistantChatRecyclerAdapter.ViewHolder(AssistantFragmentChatBubbleBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AssistantChatRecyclerAdapter.ViewHolder holder, int position) {
        if (items.get(position).incoming) {
            holder.incomingBubble.setText(items.get(position).text);
            holder.incomingBubble.setVisibility(View.VISIBLE);
            holder.outgoingBubble.setVisibility(View.GONE);
        } else {
            holder.outgoingBubble.setText(items.get(position).text);
            holder.outgoingBubble.setVisibility(View.VISIBLE);
            holder.incomingBubble.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView incomingBubble;
        private TextView outgoingBubble;

        public ViewHolder(AssistantFragmentChatBubbleBinding binding) {
            super(binding.getRoot());
            this.incomingBubble = binding.incomingBubble;
            this.outgoingBubble = binding.outgoingBubble;

        }
    }
}
