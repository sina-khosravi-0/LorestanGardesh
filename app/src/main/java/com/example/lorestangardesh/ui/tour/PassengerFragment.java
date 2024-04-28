package com.example.lorestangardesh.ui.tour;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.lorestangardesh.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import ir.erfandm.persiandatepicker.datepicker.MaterialDatePicker;

public class PassengerFragment extends Fragment {
    public static PassengerFragment newInstance() {
        return new PassengerFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_passenger, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MaterialButton expandCardButton = view.findViewById(R.id.expand_card_button);
        LinearLayout passengerInfo = view.findViewById(R.id.extended_info_layout);
        TextInputLayout birthdateInputLayout = view.findViewById(R.id.birthdate_input_layout);
//
                expandCardButton.setOnClickListener(v -> {
            if (passengerInfo.getVisibility() == View.VISIBLE) {
                passengerInfo.setVisibility(View.GONE);
            } else {
                passengerInfo.setVisibility(View.VISIBLE);
            }
        });
        requireActivity().runOnUiThread(() -> {
            birthdateInputLayout.setEndIconOnClickListener(v -> {
                FragmentManager fm = requireActivity().getSupportFragmentManager();
                MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker().setTitleText("انتخاب تاریخ").build();
                datePicker.addOnNegativeButtonClickListener(v1 -> {
                    System.out.println("negative");
                });
                datePicker.addOnPositiveButtonClickListener(v1 -> {
                    System.out.println("positive");
                });
                datePicker.show(fm, "fragment_alert");
            });
        });
    }
}
