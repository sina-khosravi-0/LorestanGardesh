package com.example.lorestangardesh.db;

import static com.example.lorestangardesh.db.DataManager.ASSISTANT_CONVERSATION_LIST;
import static com.example.lorestangardesh.statics.Constants.API_ADDRESS;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.collection.LruCache;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class DatabaseHandlerSingleton {
    private static DatabaseHandlerSingleton instance;
    private final ImageLoader imageLoader;
    private final Context context;
    private RequestQueue requestQueue;

    private DatabaseHandlerSingleton(@NonNull Context context) {
        // getApplicationContext() is key, it keeps you from leaking the
        // Activity or BroadcastReceiver if someone passes one in.
        this.context = context.getApplicationContext();
        requestQueue = getRequestQueue();

        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<>(20);

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    public static synchronized DatabaseHandlerSingleton getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHandlerSingleton(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public JSONArray getAllLocations() {
        String url = API_ADDRESS + "location/all/";
        RequestFuture<JSONArray> future = RequestFuture.newFuture();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, future, future);
        instance.addToRequestQueue(request);
        try {
            return future.get(20, TimeUnit.SECONDS);
//            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(MainActivity.UPDATE_USER_DATA_INTENT));
        } catch (InterruptedException | ExecutionException | TimeoutException ex) {
            Log.d("DatabaseHandler", ex.getMessage(), ex);
            return new JSONArray();
        }
    }

    public JSONObject getLocationDetails(int id) {
        String url = API_ADDRESS + "location/get/" + id + "/";
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, future, future);
        instance.addToRequestQueue(request);
        try {
            return future.get(20, TimeUnit.SECONDS);
//            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(MainActivity.UPDATE_USER_DATA_INTENT));
        } catch (InterruptedException | ExecutionException | TimeoutException ex) {
            Log.d("DatabaseHandler", ex.getMessage(), ex);
            return new JSONObject();
        }
    }

    public JSONArray getSearchResults(String searchTerm, String locationType) {
        String url = API_ADDRESS + String.format("location/search?q=%s", searchTerm);
        RequestFuture<JSONArray> future = RequestFuture.newFuture();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, future, future);
        instance.addToRequestQueue(request);
        try {
            return future.get(20, TimeUnit.SECONDS);
//            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(MainActivity.UPDATE_USER_DATA_INTENT));
        } catch (InterruptedException | ExecutionException | TimeoutException ex) {
            Log.d("DatabaseHandler", ex.getMessage(), ex);
            return new JSONArray();
        }
    }

    public JSONArray getNearLocations(int km, double lat, double lon) {
        String url = API_ADDRESS + String.format("location/nearme?km=%s&lat=%s&lon=%s", km, lat, lon);
        RequestFuture<JSONArray> future = RequestFuture.newFuture();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, future, future);
        instance.addToRequestQueue(request);
        try {
            return future.get(20, TimeUnit.SECONDS);
//            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(MainActivity.UPDATE_USER_DATA_INTENT));
        } catch (InterruptedException | ExecutionException | TimeoutException ex) {
            Log.d("DatabaseHandler", ex.getMessage(), ex);
            return new JSONArray();
        }
    }

    public JSONObject sendPrompt() {
        String url = API_ADDRESS + "assistant/prompt/";
        // Prepare the JSON array
        JSONArray jsonArray = new JSONArray();
        try {
            for (PromptObject prompt : ASSISTANT_CONVERSATION_LIST) {
                JSONObject obj = new JSONObject();
                obj.put("role", prompt.role);
                obj.put("content", prompt.content);
                jsonArray.put(obj);
            }
        } catch (JSONException e) {
        }

        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null, future, future) {
            @Override
            public byte[] getBody() {
                return jsonArray.toString().getBytes(StandardCharsets.UTF_8);
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                Date date = new Date();
                headers.put("X-Current-Datetime", date.toString());
                return headers;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        instance.addToRequestQueue(request);
        try {
            return future.get(50, TimeUnit.SECONDS);
//            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(MainActivity.UPDATE_USER_DATA_INTENT));
        } catch (InterruptedException | ExecutionException | TimeoutException ex) {
            Log.d("DatabaseHandler", ex.getMessage(), ex);
            return new JSONObject();
        }
    }

}
