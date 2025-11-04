package com.example.lorestangardesh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lorestangardesh.db.DatabaseHandlerSingleton;
import com.example.lorestangardesh.db.PromptObject;
import com.example.lorestangardesh.statics.DayMapper;
import com.example.lorestangardesh.statics.TypeMapper;
import com.example.lorestangardesh.statics.Intents;
import com.example.lorestangardesh.ui.main.HomeFragment;
import com.example.lorestangardesh.ui.main.SearchFragment;
import com.example.lorestangardesh.ui.main.YouFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.search.SearchBar;
import com.google.android.material.search.SearchView;
import com.google.android.material.transition.MaterialFadeThrough;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String homeFragmentTag = "home_fragment";
    private static final String tourismFragmentTag = "tourism_fragment";
    private static final String youFragmentTag = "you_fragment";
    public static String searchTerm;
    BottomNavigationView bottomNavigationView;
    private SearchBar searchBar;
    private SearchView searchView;
    private AppBarLayout topAppbar;
    private Fragment homeFragment;
    private Fragment tourismFragment;
    private Fragment youFragment;
    private Fragment Fragment1;
    private Fragment Fragment2;
    private Fragment previousFragment;

    private static String queryTerm = "";
    public static String getQueryTerm(){
        if (queryTerm == null) {
            return "";
        }
        return queryTerm;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
//        // todo:Change app language based on user preferences
//        Locale locale = new Locale("en");
//        Locale.setDefault(locale);
//        Resources resources = newBase.getResources();
//        Configuration configuration = resources.getConfiguration();
//        configuration.setLocale(locale);
//        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
//        Context newContext = newBase.createConfigurationContext(configuration);
//        newContext.getResources().getConfiguration().setLayoutDirection(locale);
//        super.attachBaseContext(newContext);
        Locale locale = new Locale("fa");
//        Locale locale = Locale.getDefault();
        Locale.setDefault(locale);
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.create(locale));
        DatabaseHandlerSingleton.getInstance(newBase);
        TypeMapper.getInstance(newBase);
        DayMapper.getInstance(newBase);
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        setExitSharedElementCallback(new MaterialContainerTransformSharedElementCallback());
        getWindow().setSharedElementsUseOverlay(false);

        setContentView(R.layout.activity_main);

//        //todo: make locale change not restart activity everytime.
        Locale locale = new Locale("fa");
        LocaleListCompat localeListCompat = LocaleListCompat.create(locale);
        if (AppCompatDelegate.getApplicationLocales() != localeListCompat) {
            AppCompatDelegate.setApplicationLocales(localeListCompat);
//        Locale locale = Locale.getDefault();
        }

//        RecyclerView searchSuggestionRecycler = findViewById(R.id.search_suggestion_recycler);
//        SearchSuggestionRecyclerAdapter searchSuggestionRecyclerAdapter =
//                new SearchSuggestionRecyclerAdapter(Arrays.asList(
//                        new SearchSuggestionItem(R.drawable.falk, "فلک الافلاک", "آثار باستانی خرم آباد"),
//                        new SearchSuggestionItem(R.drawable.falk, "آبشار بیشه", "طبیعت لرستان"),
//                        new SearchSuggestionItem(R.drawable.falk, "فلک الافلاک", "آثار باستانی خرم آباد")
//                ));
//        searchSuggestionRecyclerAdapter.setOnItemClickListener(position -> {
//            startActivity(new Intent(this, PlaceEventActivity.class));
//        });
//        searchSuggestionRecycler.setAdapter(searchSuggestionRecyclerAdapter);
//        searchSuggestionRecycler.setLayoutManager(new LinearLayoutManager(this));

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        getSupportFragmentManager().getFragments().forEach(transaction::remove);
        transaction.commitNow();

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        NestedScrollView nestedScrollView = findViewById(R.id.nested_scroll_view);
        topAppbar = findViewById(R.id.top_appbar);
        searchBar = findViewById(R.id.search_bar);
        searchView = findViewById(R.id.search_view);


        EditText editText = searchView.getEditText();
        // Listen for the IME "Search" action (user presses keyboard search button)
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || (event != null && event.getAction() == KeyEvent.ACTION_DOWN
                        && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    String query = v.getText() == null ? "" : v.getText().toString().trim();
                    searchView.clearFocusAndHideKeyboard();
                    searchView.hide();

                    bottomNavigationView.setSelectedItemId(R.id.bottom_nav_item_2);
                    searchBar.setText(query);
                    queryTerm = query;
                    LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(new Intent(Intents.SEARCH_INTENT));
                    return true;
                }
                return false;
            }
        });

        homeFragment = HomeFragment.newInstance();
        homeFragment.setEnterTransition(new MaterialFadeThrough());
        homeFragment.setExitTransition(new MaterialFadeThrough());

        tourismFragment = SearchFragment.newInstance();
        tourismFragment.setEnterTransition(new MaterialFadeThrough());
        tourismFragment.setExitTransition(new MaterialFadeThrough());

        youFragment = YouFragment.newInstance();
        youFragment.setEnterTransition(new MaterialFadeThrough());
        youFragment.setExitTransition(new MaterialFadeThrough());


        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_nav_item_1) {
                loadFragment(homeFragment, homeFragmentTag);
            } else if (item.getItemId() == R.id.bottom_nav_item_2) {
                loadFragment(tourismFragment, tourismFragmentTag);
//            } else if (item.getItemId() == R.id.bottom_nav_item_3) {
//            } else if (item.getItemId() == R.id.bottom_nav_item_4) {
//                loadFragment(youFragment, youFragmentTag);
            }
            return true;
        });

        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.bottom_nav_item_1);
        } else {
            bottomNavigationView.setSelectedItemId(savedInstanceState.getInt("selectedId"));
        }

//        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, 100);
//        translateAnimation.setInterpolator(new PathInterpolator(0.05f, 0.7f, 0.1f, 1f));
//        bottomNavigationView.setAnimation(translateAnimation);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("selectedId", bottomNavigationView.getSelectedItemId());
        super.onSaveInstanceState(outState);
    }

    void loadFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment existingFragment = getSupportFragmentManager().findFragmentByTag(tag);

        if (existingFragment == null) {
            transaction.add(R.id.content_place_holder, fragment, tag);
            if (previousFragment != null) {
                transaction.hide(previousFragment);
            }
            previousFragment = fragment;
        } else {
            if (previousFragment != existingFragment && previousFragment != null) {
                transaction.show(existingFragment);
                transaction.hide(previousFragment);
            }
            previousFragment = existingFragment;
        }
        transaction.commit();
    }
}