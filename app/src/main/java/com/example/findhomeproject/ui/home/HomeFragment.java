package com.example.findhomeproject.ui.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.findhomeproject.R;
import com.example.findhomeproject.adapter.MotelNewsAdapter;
import com.example.findhomeproject.intents.MotelPosting;
import com.example.findhomeproject.modelForMotel.MotelNews;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public ArrayList<MotelNews> arrMotelNews;
    MotelNewsAdapter motelNewsAdapter;
    ListView lvMotelNews;

    DatabaseReference myRef;
    FirebaseDatabase firebaseDatabase;
    ProgressDialog progressDialog;

    Toolbar toolbarHome;
    ImageButton btnPostMotel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View home = inflater.inflate(R.layout.fragment_home, container, false);
        addControls(home);
        addEvents();
        return home;
    }



    private void addEvents() {
        getDataFromFirebase();
        btnPostMotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onGoToMotelPostingActivity();
            }
        });

        lvMotelNews.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                arrMotelNews.remove(i);
                return false;
            }
        });

    }

    private void getDataFromFirebase() {

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                MotelNews motelNews = dataSnapshot.getValue(MotelNews.class);
                arrMotelNews.add(0, motelNews);
                motelNewsAdapter.notifyDataSetChanged();
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

    private void onGoToMotelPostingActivity() {
        Intent intent = new Intent(getActivity(), MotelPosting.class);
        startActivity(intent);
    }

    private void addControls(View home) {

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("motels");
        lvMotelNews = home.findViewById(R.id.lvMotelNews);
        arrMotelNews = new ArrayList<>();
        motelNewsAdapter = new MotelNewsAdapter(getActivity(), R.layout.item_motel_news, arrMotelNews);
        lvMotelNews.setAdapter(motelNewsAdapter);
        progressDialog = new ProgressDialog(getActivity());
        toolbarHome = home.findViewById(R.id.toolbarHome);
        btnPostMotel = home.findViewById(R.id.btnPostMotel);
        progressDialog.setTitle("Đang tìm nhà. Chờ xíu!!");
        progressDialog.show();

    }


}