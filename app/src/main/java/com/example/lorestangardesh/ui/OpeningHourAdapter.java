package com.example.lorestangardesh.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lorestangardesh.R;
import com.example.lorestangardesh.db.OpeningHour;

import java.util.List;

public class OpeningHourAdapter extends BaseAdapter {

    private Context context;
    private List<OpeningHour> items;

    public OpeningHourAdapter(Context context, List<OpeningHour> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        System.out.println(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.items_opening_hour, parent, false);

            holder = new ViewHolder();
            holder.day = convertView.findViewById(R.id.day);
            holder.opening = convertView.findViewById(R.id.opening);
            holder.closing = convertView.findViewById(R.id.closing);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        OpeningHour item = items.get(position);

        holder.day.setText(item.day);
        holder.opening.setText(item.openTime);
        holder.closing.setText(item.closeTime);

        return convertView;
    }

    static class ViewHolder {
        TextView day, opening, closing;
    }
}
