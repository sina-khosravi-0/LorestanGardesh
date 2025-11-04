package com.example.lorestangardesh.ui.main;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lorestangardesh.MainActivity;
import com.example.lorestangardesh.R;
import com.example.lorestangardesh.db.DataManager;
import com.example.lorestangardesh.db.Location;
import com.example.lorestangardesh.statics.Intents;
import com.example.lorestangardesh.ui.PlaceEventActivity;
import com.example.lorestangardesh.ui.searchresult.SearchResultItem;
import com.example.lorestangardesh.ui.searchresult.SearchResultRecyclerAdapter;
import com.example.lorestangardesh.utils.GeoDistance;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONException;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class RealtimeSuggestionActivity extends AppCompatActivity {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 100;
    SearchResultRecyclerAdapter searchResultAdapter;
    RecyclerView realtimeRecycler;
    private FusedLocationProviderClient fusedLocationClient;
    private List<Location> locations;
    private int km = 2;
    private double lat;
    private double lon;
    private final BroadcastReceiver reloadBr = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            new Thread(() -> {

                locations = DataManager.getInstance().getNearLocations(km, lat, lon);
                RealtimeSuggestionActivity.this.runOnUiThread(() -> {
                    if (locations.isEmpty()) {
                        Toast.makeText(RealtimeSuggestionActivity.this, R.string.nothing_found, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (realtimeRecycler.getAdapter() != null) {
                        searchResultAdapter.setItems(locations.stream().map(location -> {
                            String photo = "";
                            if (!location.getPhotos().isEmpty()) {
                                photo = location.getPhotos().get(0);
                            }
                            return new SearchResultItem(photo, location.getTitle(),
                                    String.format("%1.100s", location.getDescription()),
                                    String.format("%.1f km", GeoDistance.calculateDistance(lat, lon, location.getLat(), location.getLon())));
                        }).collect(Collectors.toList()));
                        searchResultAdapter.notifyDataSetChanged();
                    } else {
                        searchResultAdapter = new SearchResultRecyclerAdapter(locations.stream().map(location -> {
                            String photo = "";
                            if (!location.getPhotos().isEmpty()) {
                                photo = location.getPhotos().get(0);
                            }
                            return new SearchResultItem(photo, location.getTitle(),
                                    String.format("%1.100s", location.getDescription()),
                                    String.format("%.1f km", GeoDistance.calculateDistance(lat, lon, location.getLat(), location.getLon())));
                        }).collect(Collectors.toList()));

                        realtimeRecycler.setAdapter(searchResultAdapter);
                        searchResultAdapter.setOnItemClickListener(position -> {
                            Intent intention = new Intent(RealtimeSuggestionActivity.this, PlaceEventActivity.class);
                            intention.putExtra("id", Integer.valueOf(locations.get(position).getId()));
                            startActivity(intention);
                        });
                    }
                });
            }).start();

        }
    };
    private boolean scheduled = false;
    private TimerTask reloadLocationTask;
    private Timer reloadLocationTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        lbm.registerReceiver(reloadBr, new IntentFilter(Intents.RELOAD_INTENT));


        setContentView(R.layout.activity_realtime_suggestion);

        // Initialize FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        // Check for location permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            // Request permissions
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        }

        RadioGroup radiusRadio = findViewById(R.id.radius_radio);
        realtimeRecycler = findViewById(R.id.realtime_items_recycler);
        realtimeRecycler.setLayoutManager(new LinearLayoutManager(RealtimeSuggestionActivity.this));

        radiusRadio.setOnCheckedChangeListener(((group, checkedId) -> {
            RadioButton radioButton = group.findViewById(checkedId);
            switch (group.indexOfChild(radioButton)) {
                case 0:
                    km = 2;
                    break;
                case 1:
                    km = 5;
                    break;
                case 2:
                    km = 10;
                    break;
            }
        }));
        if (scheduled) {
            return;
        }
        reloadLocationTask = new TimerTask() {

            @Override
            public void run() {
                getLastLocation();
            }
        };
        reloadLocationTimer = new Timer();
        reloadLocationTimer.schedule(reloadLocationTask, 0, 5000);
        scheduled = true;
    }

    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<android.location.Location>() {
                @Override
                public void onSuccess(android.location.Location location) {
                    lat = location.getLatitude();
                    lon = location.getLongitude();
                    LocalBroadcastManager.getInstance(RealtimeSuggestionActivity.this).sendBroadcast(new Intent(Intents.RELOAD_INTENT));
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, get location
                getLastLocation();
            } else {
                // Permission denied
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
        reloadLocationTimer.cancel();
        scheduled = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!scheduled) {
            reloadLocationTask = new TimerTask() {

                @Override
                public void run() {
                    getLastLocation();
                }
            };
            reloadLocationTimer = new Timer();
            reloadLocationTimer.schedule(reloadLocationTask, 0, 5000);
            scheduled = true;
        }
    }
}