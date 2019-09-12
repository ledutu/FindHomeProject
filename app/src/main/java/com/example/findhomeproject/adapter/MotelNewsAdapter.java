package com.example.findhomeproject.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.findhomeproject.R;
import com.example.findhomeproject.modelForMotel.MotelNews;

import java.util.List;

public class MotelNewsAdapter extends ArrayAdapter<MotelNews> {
    Activity context;
    int resource;
    List<MotelNews> objects;


    public MotelNewsAdapter(@NonNull Activity context, int resource, @NonNull List<MotelNews> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);
        TextView txtTitle = row.findViewById(R.id.txtTitle);
        TextView txtContent = row.findViewById(R.id.txtContent);
        ImageView imgImage = row.findViewById(R.id.imgImage);
        LinearLayout lyMotelNews = row.findViewById(R.id.lyMotelNews);

        MotelNews motelNews = this.objects.get(position);
        imgImage.setImageResource(motelNews.getMotelImage());
        txtTitle.setText(motelNews.getMotelName());
        txtContent.setText(motelNews.getMotelAddress());
        return row;
    }
}
