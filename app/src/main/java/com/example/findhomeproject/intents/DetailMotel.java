package com.example.findhomeproject.intents;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findhomeproject.R;
import com.example.findhomeproject.modelForMotel.MotelNews;
import com.example.findhomeproject.ui.account.AccountFragment;
import com.example.findhomeproject.ui.favourite.FavouriteFragment;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;

public class DetailMotel extends AppCompatActivity {

    ImageView imgImageSlider;
    TextView txtDetailName, txtDetailAddress, txtDetailPhone, txtDetailArea,
            txtDetailTime, txtDetailInformation, txtDetailContent, txtDetailMotelCosting;

    ImageButton btnBackToHome, btnAddToFavourite;

    MotelNews motelNews;

    ScrollView svDetailMotel;

    DatabaseReference myRef;
    FirebaseDatabase firebaseDatabase;

    int check;

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
        svDetailMotel = findViewById(R.id.svDetailMotel);
        txtDetailMotelCosting = findViewById(R.id.txtDetailMotelCosting);
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("motels");

        //init
        Intent intentFromMotelNews = getIntent();
        motelNews = (MotelNews) intentFromMotelNews.getSerializableExtra("MotelDetail");
        Picasso.get().load(motelNews.getMotelImage()).into(imgImageSlider);
        txtDetailName.setText(motelNews.getMotelName());
        txtDetailAddress.setText(motelNews.getMotelAddress());
        txtDetailPhone.setText(motelNews.getPhoneNumber());
        txtDetailArea.setText(motelNews.getMotelArea());
        txtDetailTime.setText(motelNews.getTimePosting());
        txtDetailContent.setText(motelNews.getMotelDetail());
        txtDetailMotelCosting.setText(motelNews.getMotelCost());
        check = motelNews.getCheck();

        Intent intentFromMotelSaving = getIntent();
        motelNews = (MotelNews) intentFromMotelSaving.getSerializableExtra("MotelDetail");
        Picasso.get().load(motelNews.getMotelImage()).into(imgImageSlider);
        txtDetailName.setText(motelNews.getMotelName());
        txtDetailAddress.setText(motelNews.getMotelAddress());
        txtDetailPhone.setText(motelNews.getPhoneNumber());
        txtDetailArea.setText(motelNews.getMotelArea());
        txtDetailTime.setText(motelNews.getTimePosting());
        txtDetailContent.setText(motelNews.getMotelDetail());
        txtDetailMotelCosting.setText(motelNews.getMotelCost());
        check = motelNews.getCheck();
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
                addToFavourite();
            }
        });

    }

    private void addToFavourite() {
        Log.i("check", String.valueOf(check));
        if(check == 1)
        {
            myRef.child(String.valueOf(motelNews.getId())).child("check").setValue(0);
            Toast.makeText(DetailMotel.this, "Deleted from favourite", Toast.LENGTH_SHORT).show();
        }
        else
        {
            myRef.child(String.valueOf(motelNews.getId())).child("check").setValue(1);
            Toast.makeText(DetailMotel.this, "Added from favourite", Toast.LENGTH_SHORT).show();
        }
    }
}
