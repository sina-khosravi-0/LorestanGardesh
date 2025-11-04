package com.example.lorestangardesh.statics;

import com.example.lorestangardesh.R;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

public class TypeMapper {
    private static volatile TypeMapper instance;
    private final Context context;
    public final Map<String, String> codeToStringResMap;

    private TypeMapper(Context context) {
        this.context = context.getApplicationContext(); // Use application context to avoid memory leaks
        this.codeToStringResMap = new HashMap<>();
        initializeCodeMap();
    }

    public static TypeMapper getInstance(Context context) {
        if (instance == null) {
            synchronized (TypeMapper.class) {
                if (instance == null) {
                    instance = new TypeMapper(context);
                }
            }
        }
        return instance;
    }

    private void initializeCodeMap() {
        // Map JSON codes to string resource IDs
        codeToStringResMap.put("N", context.getString(R.string.nature));
        codeToStringResMap.put("HH", context.getString(R.string.historical));
        codeToStringResMap.put("R", context.getString(R.string.restaurant));
        codeToStringResMap.put("H", context.getString(R.string.hotel));
        codeToStringResMap.put("C", context.getString(R.string.cafe));
        codeToStringResMap.put("S", context.getString(R.string.shop));
        codeToStringResMap.put("PA", context.getString(R.string.park));
    }
}