package com.example.lorestangardesh.ui.tours.single;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.lorestangardesh.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.transition.MaterialFadeThrough;

public class TourActivity extends AppCompatActivity {

    private Fragment previousFragment;
    private Fragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.tour_activity_tour);
        ExtendedFloatingActionButton eFab = findViewById(R.id.signup_fab);
        mainFragment = TourMainFragment.newInstance();
        Fragment reserveFragment = ReserveTourFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_place_holder,
                mainFragment, mainFragment.getClass().getName()).commit();
        previousFragment = mainFragment;

        mainFragment.setEnterTransition(new MaterialFadeThrough());
        reserveFragment.setEnterTransition(new MaterialFadeThrough());
        mainFragment.setExitTransition(new MaterialFadeThrough());
        reserveFragment.setExitTransition(new MaterialFadeThrough());

        eFab.setOnClickListener(v -> {
            loadFragment(reserveFragment, reserveFragment.getClass().getName());
            eFab.hide();
        });
        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                eFab.show();
            }
        });
    }

    void loadFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment existingFragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (existingFragment == null) {
            transaction.add(R.id.content_place_holder, fragment, tag);
            if (previousFragment != null) {
                transaction.hide(previousFragment);
            }
        } else {
            if (previousFragment != existingFragment && previousFragment != null) {
                transaction.show(existingFragment);
                transaction.hide(previousFragment);
            }
        }
        transaction.addToBackStack(null);
        transaction.commit();
    }

}