package com.example.lorestangardesh.statics;

import android.content.Context;

import com.example.lorestangardesh.R;

import java.util.HashMap;
import java.util.Map;

public class DayMapper {
    private static volatile DayMapper instance;
    private final Context context;
    public final Map<String, String> codeToStringResMap;

    private DayMapper(Context context) {
        this.context = context.getApplicationContext(); // Use application context to avoid memory leaks
        this.codeToStringResMap = new HashMap<>();
        initializeCodeMap();
    }

    public static DayMapper getInstance(Context context) {
        if (instance == null) {
            synchronized (TypeMapper.class) {
                if (instance == null) {
                    instance = new DayMapper(context);
                }
            }
        }
        return instance;
    }

    private void initializeCodeMap() {
        // Map JSON codes to string resource IDs
        codeToStringResMap.put("1", context.getString(R.string.monday));
        codeToStringResMap.put("2", context.getString(R.string.tuesday));
        codeToStringResMap.put("3", context.getString(R.string.wednesday));
        codeToStringResMap.put("4", context.getString(R.string.thursday));
        codeToStringResMap.put("5", context.getString(R.string.friday));
        codeToStringResMap.put("6", context.getString(R.string.saturday));
        codeToStringResMap.put("7", context.getString(R.string.sunday));
    }
}