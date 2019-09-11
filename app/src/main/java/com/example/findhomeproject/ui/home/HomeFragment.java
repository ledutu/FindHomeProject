package com.example.findhomeproject.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.findhomeproject.R;

public class HomeFragment extends Fragment {

    EditText txtHomeA, txtHomeB;
    TextView txtHomeResult;
    Button btnHomeAdd;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        txtHomeA = root.findViewById(R.id.txtHomeA);
        txtHomeB = root.findViewById(R.id.txtHomeB);
        txtHomeResult = root.findViewById(R.id.txtHomeResult);
        btnHomeAdd = root.findViewById(R.id.btnHomeAdd);


        btnHomeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doAdd();
            }
        });

        return root;
    }

    private void doAdd() {
        int a = Integer.parseInt(txtHomeA.getText().toString());
        int b = Integer.parseInt(txtHomeB.getText().toString());
        int c = a+b;

        txtHomeResult.setText("Result: "+c);
    }

}