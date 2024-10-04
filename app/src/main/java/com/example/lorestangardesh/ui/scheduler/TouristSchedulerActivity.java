package com.example.lorestangardesh.ui.scheduler;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import com.example.lorestangardesh.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class TouristSchedulerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.tools_activity_tourist_scheduler);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ExtendedFloatingActionButton calculateButton = findViewById(R.id.generate_schedule_fab);
        calculateButton.setExtended(false);
        calculateButton.extend();

        View.OnClickListener onCalculateButtonClicked = v -> {
//            FragmentManager fm = getSupportFragmentManager();
//            ShowScheduleFragment installedPackageDialogue = ShowScheduleFragment.newInstance();
//            installedPackageDialogue.show(fm, "fragment_alert");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, TouristSchedulerResultFragment.newInstance(), null)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit();

            calculateButton.shrink();
            calculateButton.setIconResource(R.drawable.ic_save);
            calculateButton.setOnClickListener(v1 -> {});
        };

        calculateButton.setOnClickListener(onCalculateButtonClicked);
        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                calculateButton.extend();
                calculateButton.setIconResource(R.drawable.ic_gears);
                calculateButton.setOnClickListener(onCalculateButtonClicked);
            }
        });
    }
}