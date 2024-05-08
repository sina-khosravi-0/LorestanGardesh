package com.example.lorestangardesh.ui.tour;

//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.myapplication.databinding.TourItemsFragmentPassengerItemBinding;
//import com.google.android.material.button.MaterialButton;
//import com.google.android.material.textfield.TextInputLayout;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;

public class PassengerRecyclerAdapter /*extends RecyclerView.Adapter<PassengerRecyclerAdapter.ViewHolder>*/ {
//    private List<PassengerItem> items;
//
//    public PassengerRecyclerAdapter(List<PassengerItem> items) {
//        this.items = new ArrayList<>(items);
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new ViewHolder(TourItemsFragmentPassengerItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        if (items.isEmpty()) {
//            holder.index.setText(String.format("%d", position + 1));
//            return;
//        }
//        holder.index.setText(String.format("%d", position + 1));
//        holder.displayName.setText(items.get(position).name);
//        holder.displayLastname.setText(items.get(position).lastname);
//        Objects.requireNonNull(holder.nameInputLayout.getEditText()).setText(items.get(position).name);
//        Objects.requireNonNull(holder.lastnameInputLayout.getEditText()).setText(items.get(position).lastname);
//        Objects.requireNonNull(holder.birthdateInputLayout.getEditText()).setText(items.get(position).birthdate);
//        Objects.requireNonNull(holder.codeInputLayout.getEditText()).setText(items.get(position).code);
//        holder.expandButton.setOnClickListener(v -> {
//            if(holder.extendedInfoLayout.getVisibility() == View.GONE) {
//                holder.extendedInfoLayout.setVisibility(View.VISIBLE);
//            } else {
//                holder.extendedInfoLayout.setVisibility(View.GONE);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return Math.max(items.size(), 1);
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        private final TextView index;
//        private final TextView displayName;
//        private final TextView displayLastname;
//        private final MaterialButton expandButton;
//        private final LinearLayout extendedInfoLayout;
//        private final TextInputLayout nameInputLayout;
//        private final TextInputLayout lastnameInputLayout;
//        private final TextInputLayout birthdateInputLayout;
//        private final TextInputLayout codeInputLayout;
//
//        public ViewHolder(TourItemsFragmentPassengerItemBinding binding) {
//            super(binding.getRoot());
//            index = binding.itemIndexTextView;
//            displayName = binding.displayNameTextview;
//            displayLastname = binding.displayLastnameTextview;
//            expandButton = binding.expandCardButton;
//            extendedInfoLayout = binding.extendedInfoLayout;
//            nameInputLayout = binding.nameInputLayout;
//            lastnameInputLayout = binding.lastnameInputLayout;
//            birthdateInputLayout = binding.birthdateInputLayout;
//            codeInputLayout = binding.codeInputLayout;
//        }
//    }
}
