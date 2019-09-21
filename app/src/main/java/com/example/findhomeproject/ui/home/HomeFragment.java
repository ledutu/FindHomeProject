package com.example.findhomeproject.ui.home;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.findhomeproject.R;
import com.example.findhomeproject.adapter.MotelNewsAdapter;
import com.example.findhomeproject.modelForMotel.MotelNews;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ArrayList<MotelNews> arrMotelNews;
    MotelNewsAdapter motelNewsAdapter;
    ListView lvMotelNews;
//    Toolbar tbHome;

    DatabaseReference myRef;
    FirebaseDatabase firebaseDatabase;
    StorageReference storageReference;
    ProgressDialog progressDialog;

    public final static int Image_Request_Code = 10;
    private Uri filePathUri;
    private String Storage_Path = "MotelImage/";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View home = inflater.inflate(R.layout.fragment_home, container, false);
        addControls(home);
        addEvents();

        return home;
    }



    private void addEvents() {
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                MotelNews motelNews = dataSnapshot.getValue(MotelNews.class);
                arrMotelNews.add(motelNews);
                motelNewsAdapter.notifyDataSetChanged();
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

    private void addControls(View home) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("motels");
        lvMotelNews = home.findViewById(R.id.lvMotelNews);
        arrMotelNews = new ArrayList<>();
        motelNewsAdapter = new MotelNewsAdapter(getActivity(), R.layout.item_motel_news, arrMotelNews);
        lvMotelNews.setAdapter(motelNewsAdapter);
        progressDialog = new ProgressDialog(getActivity());
       /* tbHome = home.findViewById(R.id.tbHome);
        tbHome.setTitle("Nha tro 360");*/

    }


}