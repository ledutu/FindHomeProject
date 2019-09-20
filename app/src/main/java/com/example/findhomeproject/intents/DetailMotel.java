package com.example.findhomeproject.intents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.findhomeproject.R;
import com.example.findhomeproject.modelForMotel.MotelNews;

public class DetailMotel extends AppCompatActivity {

    ImageView imgImageSlider;
    TextView txtDetailName, txtDetailAddress, txtDetailPhone, txtDetailArea,
            txtDetailTime, txtDetailInformation, txtDetailContent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_motel);
        addControls();
        addEvents();

    }

    private void addControls() {
        imgImageSlider = findViewById(R.id.imgImageSlider);
        txtDetailName = findViewById(R.id.txtDetailName);
        txtDetailAddress = findViewById(R.id.txtDetailAddress);
        txtDetailPhone = findViewById(R.id.txtDetailPhone);
        txtDetailArea = findViewById(R.id.txtDetailArea);
        txtDetailTime = findViewById(R.id.txtDetailTime);
        txtDetailInformation = findViewById(R.id.txtDetailInformation);
        txtDetailContent = findViewById(R.id.txtDetailContent);
        Intent intent = getIntent();
        MotelNews motelNews = (MotelNews) intent.getSerializableExtra("MotelDetail");
        imgImageSlider.setImageResource(motelNews.getMotelImage());
        txtDetailName.setText(motelNews.getMotelName());
        txtDetailAddress.setText(motelNews.getMotelAddress());
        txtDetailPhone.setText(motelNews.getPhoneNumber());
        txtDetailArea.setText(motelNews.getMotelArea());
        txtDetailTime.setText(motelNews.getTimePosting());
        txtDetailContent.setText(motelNews.getMotelDetail());
    }


    private void addEvents() {

    }
}
