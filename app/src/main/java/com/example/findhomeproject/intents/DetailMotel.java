package com.example.findhomeproject.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.findhomeproject.R;
import com.example.findhomeproject.modelForMotel.MotelNews;

public class DetailMotel extends AppCompatActivity {

    ImageView imgImage;
    TextView txtContent, txtTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_motel);
        addControls();
        addEvents();

    }

    private void addControls() {
        Intent intent = getIntent();
    }


    private void addEvents() {

    }
}
