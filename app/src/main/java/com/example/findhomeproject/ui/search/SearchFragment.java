package com.example.findhomeproject.ui.search;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.findhomeproject.R;
import com.example.findhomeproject.adapter.AdapterMotelSaving;
import com.example.findhomeproject.intents.Login;
import com.example.findhomeproject.intents.SplashScreen;
import com.example.findhomeproject.modelForMotel.MotelNews;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    ArrayList<MotelNews> arrMotels;
    ListView lvSearch;
    AdapterMotelSaving adapterMotelSaving;
    ImageView btnSearch;
    DatabaseReference myRef;

    EditText txtSearch;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        addControls(root);
        addEvents();

        return root;
    }

    private void addEvents() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });
    }

    private void search() {
        arrMotels.clear();
        adapterMotelSaving.notifyDataSetChanged();
        final String searchText = txtSearch.getText().toString().toLowerCase();
        if(searchText.isEmpty())
        {
            Toast.makeText(getActivity(), "Bạn chưa nhập địa điểm", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Query firebaseQuery = myRef.orderByChild("keyMotelAddress").startAt(searchText).endAt(searchText + "\uf8ff");
            queryFirebase(firebaseQuery);
        }
    }

    private void queryFirebase(Query firebaseQuery) {

        firebaseQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                MotelNews motelNews = dataSnapshot.getValue(MotelNews.class);
                Log.i("motel", String.valueOf(dataSnapshot.getValue()));
                if(motelNews != null)
                {
                    arrMotels.add(motelNews);
                    adapterMotelSaving.notifyDataSetChanged();

                }
                else
                {
                    Toast.makeText(getActivity(), "Không tìm thấy", Toast.LENGTH_SHORT).show();
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
        adapterMotelSaving.notifyDataSetChanged();
    }

    private void addControls(View root) {
        arrMotels = new ArrayList<>();
        lvSearch= root.findViewById(R.id.lvSearch);
        adapterMotelSaving= new AdapterMotelSaving(getActivity(), R.layout.item_motel_saving, arrMotels);
        lvSearch.setAdapter(adapterMotelSaving);
        txtSearch = root.findViewById(R.id.txtSearch);
        btnSearch = root.findViewById(R.id.btnSearch);
        myRef = FirebaseDatabase.getInstance().getReference("motels");
    }


}