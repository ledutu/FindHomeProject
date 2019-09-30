package com.example.findhomeproject.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.findhomeproject.R;
import com.example.findhomeproject.adapter.AdapterMotelSaving;
import com.example.findhomeproject.modelForMotel.MotelNews;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    ArrayList<MotelNews> arrMotels;
    ListView lvSearch;
    AdapterMotelSaving adapterMotelSaving;
    ImageView btnSearch;

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
        String searchText = txtSearch.getText().toString();
        if(searchText.isEmpty())
        {
            Toast.makeText(getActivity(), "Bạn chưa nhập địa điểm", Toast.LENGTH_SHORT).show();
        }
        else
        {

        }
    }

    private void addControls(View root) {
        arrMotels = new ArrayList<>();
        lvSearch= root.findViewById(R.id.lvSearch);
        adapterMotelSaving= new AdapterMotelSaving(getActivity(), R.layout.item_motel_saving, arrMotels);
        lvSearch.setAdapter(adapterMotelSaving);
        txtSearch = root.findViewById(R.id.txtSearch);
        btnSearch = root.findViewById(R.id.btnSearch);
    }


}