package com.example.findhomeproject.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.example.findhomeproject.intents.DetailMotel;
import com.example.findhomeproject.modelForMotel.MotelNews;
import com.squareup.picasso.Picasso;

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
        TextView txtAddress = row.findViewById(R.id.txtAddress);
        TextView txtArea = row.findViewById(R.id.txtArea);
        ImageView imgImage = row.findViewById(R.id.imgImage);
        TextView txtMotelCost = row.findViewById(R.id.txtMotelCost);
        LinearLayout lyMotelNews = row.findViewById(R.id.lyMotelNews);

        final MotelNews motelNews = this.objects.get(position);
        txtMotelCost.setText(motelNews.getMotelCost());
        Picasso.get().load(motelNews.getMotelImage()).into(imgImage);
        txtAddress.setText(motelNews.getMotelAddress());
        txtArea.setText(motelNews.getMotelArea());
        lyMotelNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToDetailMotel(motelNews);
            }
        });
        return row;
    }

    private void goToDetailMotel(MotelNews motelNews) {
        Intent intent = new Intent(getContext(), DetailMotel.class);
        motelNews = new MotelNews(
                motelNews.getId(),
                motelNews.getMotelCost(),
                motelNews.getMotelImage(),
                motelNews.getMotelName(),
                motelNews.getMotelAddress(),
                motelNews.getPhoneNumber(),
                motelNews.getMotelArea(),
                motelNews.getTimePosting(),
                motelNews.getMotelDetail()
        );
        intent.putExtra("MotelDetail", motelNews);
        getContext().startActivity(intent);
    }
}
