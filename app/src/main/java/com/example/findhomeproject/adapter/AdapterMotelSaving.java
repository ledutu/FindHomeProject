package com.example.findhomeproject.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.findhomeproject.R;
import com.example.findhomeproject.modelForMotel.MotelNews;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterMotelSaving extends ArrayAdapter<MotelNews> {

    Activity context;
    int resource;
    List<MotelNews> objects;

    TextView txtMotelCostSaving, txtMotelSavingName, txtMotelAddressSaving,
            txtMotelAreaSaving, txtMotelTimeSaving;
    ImageView imgImageSaving;

    public AdapterMotelSaving(@NonNull Activity context, int resource, @NonNull List<MotelNews> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(R.layout.item_motel_saving, null);

        txtMotelCostSaving = row.findViewById(R.id.txtMotelCostSaving);
        txtMotelSavingName = row.findViewById(R.id.txtMotelSavingName);
        txtMotelAddressSaving = row.findViewById(R.id.txtMotelAddressSaving);
        txtMotelAreaSaving = row.findViewById(R.id.txtMotelAreaSaving);
        txtMotelTimeSaving = row.findViewById(R.id.txtMotelTimeSaving);
        imgImageSaving = row.findViewById(R.id.imgImageSaving);

        MotelNews motelNews = this.objects.get(position);
        Picasso.get().load(motelNews.getMotelImage()).into(imgImageSaving);
        txtMotelCostSaving.setText(motelNews.getMotelCost());
        txtMotelSavingName.setText(motelNews.getMotelName());
        txtMotelAddressSaving.setText(motelNews.getMotelAddress());
        txtMotelTimeSaving.setText(motelNews.getTimePosting());

        return row;
    }

}
