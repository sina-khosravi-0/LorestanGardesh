package com.example.lorestangardesh.db;

import com.example.lorestangardesh.statics.DayMapper;

public class OpeningHour {
    public String day;
    public String openTime;
    public String closeTime;

    public OpeningHour(int day, String openTime, String closeTime) {
        this.day = DayMapper.getInstance(null).codeToStringResMap.get(String.valueOf(day));
        this.openTime = openTime;
        this.closeTime = closeTime;
    }
}
