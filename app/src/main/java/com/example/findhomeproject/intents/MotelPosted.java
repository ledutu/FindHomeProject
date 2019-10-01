package com.example.findhomeproject.intents;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.findhomeproject.R;
import com.example.findhomeproject.adapter.AdapterMotelSaving;
import com.example.findhomeproject.modelForMotel.MotelNews;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MotelPosted extends AppCompatActivity {

    ListView lvShowMotelPosted;
    ArrayList<MotelNews> arrMotel;
    AdapterMotelSaving adapterMotelSaving;

    DatabaseReference myRef;
    FirebaseUser firebaseUser;
    String user;

    TextView txtNoMotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motel_posted);
        getSupportActionBar().hide();
        addControls();
        addEvents();
    }

    private void addEvents() {
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                MotelNews motelNews = dataSnapshot.getValue(MotelNews.class);
                if(motelNews.getUser().equals(user))
                {
                    arrMotel.add(motelNews);
                    adapterMotelSaving.notifyDataSetChanged();
                }
                if(arrMotel.size() == 0)
                {
                    lvShowMotelPosted.setVisibility(View.INVISIBLE);
                    txtNoMotel.setVisibility(View.VISIBLE);
                }
                else
                {
                    lvShowMotelPosted.setVisibility(View.VISIBLE);
                    txtNoMotel.setVisibility(View.INVISIBLE);
                }
            }


            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void addControls() {
        myRef = FirebaseDatabase.getInstance().getReference("motels");
        lvShowMotelPosted = findViewById(R.id.lvShowMotelPosted);
        arrMotel = new ArrayList<>();
        adapterMotelSaving = new AdapterMotelSaving(MotelPosted.this, R.layout.item_motel_saving, arrMotel);
        lvShowMotelPosted.setAdapter(adapterMotelSaving);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        user = firebaseUser.getEmail().toLowerCase();
        txtNoMotel = findViewById(R.id.txtNoMotel);
    }
}
