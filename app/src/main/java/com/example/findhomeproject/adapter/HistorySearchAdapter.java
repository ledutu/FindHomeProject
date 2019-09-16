package com.example.findhomeproject.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.findhomeproject.R;

import java.util.List;

public class HistorySearchAdapter extends ArrayAdapter<String> {

    Activity context;
    int resource;
    List<String> objects;

    public HistorySearchAdapter(@NonNull Activity context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        View search = layoutInflater.inflate(R.layout.item_history_search, null);
        TextView txtHistorySearch = search.findViewById(R.id.txtHistorySearch);
        String s = this.objects.get(position);
        txtHistorySearch.setText(s);
        return search;
    }
}
