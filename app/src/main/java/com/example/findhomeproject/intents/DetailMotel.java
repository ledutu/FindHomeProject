package com.example.findhomeproject.intents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findhomeproject.R;
import com.example.findhomeproject.modelForMotel.MotelNews;
import com.squareup.picasso.Picasso;

public class DetailMotel extends AppCompatActivity {

    ImageView imgImageSlider;
    TextView txtDetailName, txtDetailAddress, txtDetailPhone, txtDetailArea,
            txtDetailTime, txtDetailInformation, txtDetailContent;

    ImageButton btnBackToHome, btnAddToFavourite;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_motel);
        getSupportActionBar().hide();
        addControls();
        addEvents();

    }

    private void addControls() {
        //mapping Id
        imgImageSlider = findViewById(R.id.imgImageSlider);
        txtDetailName = findViewById(R.id.txtDetailName);
        txtDetailAddress = findViewById(R.id.txtDetailAddress);
        txtDetailPhone = findViewById(R.id.txtDetailPhone);
        txtDetailArea = findViewById(R.id.txtDetailArea);
        txtDetailTime = findViewById(R.id.txtDetailTime);
        txtDetailInformation = findViewById(R.id.txtDetailInformation);
        txtDetailContent = findViewById(R.id.txtDetailContent);
        btnBackToHome = findViewById(R.id.btnBackToHome);
        btnAddToFavourite = findViewById(R.id.btnAddToFavourite);

        //init
        Intent intent = getIntent();
        MotelNews motelNews = (MotelNews) intent.getSerializableExtra("MotelDetail");
        Picasso.get().load(motelNews.getMotelImage()).into(imgImageSlider);
        txtDetailName.setText(motelNews.getMotelName());
        txtDetailAddress.setText(motelNews.getMotelAddress());
        txtDetailPhone.setText(motelNews.getPhoneNumber());
        txtDetailArea.setText(motelNews.getMotelArea());
        txtDetailTime.setText(motelNews.getTimePosting());
        txtDetailContent.setText(motelNews.getMotelDetail());


    }


    private void addEvents() {
        btnBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnAddToFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailMotel.this, "Added", Toast.LENGTH_LONG).show();
            }
        });
    }
}
