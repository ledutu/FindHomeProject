package com.example.findhomeproject.ui.favourite;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.findhomeproject.R;
import com.example.findhomeproject.adapter.AdapterMotelSaving;
import com.example.findhomeproject.modelForMotel.MotelNews;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class FavouriteFragment extends Fragment {

    DatabaseReference myRef;
    FirebaseDatabase firebaseDatabase;
    ProgressDialog progressDialog;

    ArrayList<MotelNews> arrMotelNews;
    ListView lvMotelNews;
    AdapterMotelSaving adapterMotelSaving;

    TextView txtNoMotel;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View favourite = inflater.inflate(R.layout.fragment_favourite, container, false);

        addControls(favourite);
        addEvents();


        return favourite;
    }

    private void addEvents() {
        getDataFromFirebase();
    }

    private void getDataFromFirebase() {

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                MotelNews motelNews = dataSnapshot.getValue(MotelNews.class);
                if (motelNews.getCheck() == 1) {
                    arrMotelNews.add(motelNews);
                    adapterMotelSaving.notifyDataSetChanged();
                    Log.i("key", dataSnapshot.getKey());
                }
                if(arrMotelNews.size() != 0)
                {
                    lvMotelNews.setVisibility(View.VISIBLE);
                    txtNoMotel.setVisibility(View.INVISIBLE);

                }
                else
                {
                    lvMotelNews.setVisibility(View.INVISIBLE);
                    txtNoMotel.setVisibility(View.VISIBLE);
                }

                progressDialog.dismiss();
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


    private void addControls(View favourite) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("motels");
        arrMotelNews = new ArrayList<>();
        lvMotelNews = favourite.findViewById(R.id.lvMotelNews);
        adapterMotelSaving = new AdapterMotelSaving(getActivity(), R.layout.item_motel_saving, arrMotelNews);
        lvMotelNews.setAdapter(adapterMotelSaving);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Đang load danh sách yêu thích, Chờ xíu!!!");
        progressDialog.show();
        txtNoMotel = favourite.findViewById(R.id.txtNoMotel);

    }
}