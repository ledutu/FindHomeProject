package com.example.findhomeproject.ui.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.findhomeproject.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    Spinner spYouLive;
    List<String> tinh;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View account = inflater.inflate(R.layout.fragment_account, container, false);

        spYouLive = (Spinner) account.findViewById(R.id.spYouLive);

        spYouLive.setOnItemSelectedListener(this);

        tinh = new ArrayList<String>();
        tinh = Arrays.asList(getResources().getStringArray(R.array.tinh_thanh));

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,tinh);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spYouLive.setAdapter(dataAdapter);
        return account;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),"Bạn đã chọn:" +item,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
