package com.example.findhomeproject.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.findhomeproject.R;

import java.util.ArrayList;
import java.util.List;

/*public class AdapterImage extends BaseAdapter {

    private Context ctx;
    private int pos;
    private LayoutInflater inflater;
    private ImageView imgImageChosen;
    ArrayList<Uri> mArrayUri;

    public AdapterImage(Context ctx, ArrayList<Uri> mArrayUri) {

        this.ctx = ctx;
        this.mArrayUri = mArrayUri;
    }

    @Override
    public int getCount() {
        return mArrayUri.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayUri.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        pos = position;
        inflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = inflater.inflate(R.layout.item_image, parent, false);

        imgImageChosen = (ImageView) row.findViewById(R.id.imgImageChosen);

        imgImageChosen.setImageURI(mArrayUri.get(position));

        final FrameLayout flContainer = row.findViewById(R.id.flContainer);

        TextView btnCancelImage = row.findViewById(R.id.btnCancelImage);

        btnCancelImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flContainer.removeAllViews();
            }
        });

        return row;
    }
}*/

public class AdapterImage extends ArrayAdapter<String>{

    Activity context;
    int resource;
    List<String> objects;

    public AdapterImage(@NonNull Activity context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(R.layout.item_image, null);

        TextView btnCancelImage = row.findViewById(R.id.btnCancelImage);
        final FrameLayout flContainer = row.findViewById(R.id.flContainer);
        ImageView imgImageChosen = row.findViewById(R.id.imgImageChosen);

        String s = this.objects.get(position);

        return row;
    }
}
