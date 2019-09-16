package com.example.findhomeproject.ui.search;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.findhomeproject.R;
import com.example.findhomeproject.adapter.HistorySearchAdapter;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    ArrayList<String> arrHistorySearch;
    ListView lvHistorySearch;
    HistorySearchAdapter historySearchAdapter;

    EditText txtSearch;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        arrHistorySearch = new ArrayList<>();
        lvHistorySearch = root.findViewById(R.id.lvHistorySearch);
        historySearchAdapter = new HistorySearchAdapter(getActivity(), R.layout.item_motel_news, arrHistorySearch);
        lvHistorySearch.setAdapter(historySearchAdapter);
        txtSearch = root.findViewById(R.id.txtSearch);

        txtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    String s = textView.getText().toString();
                    arrHistorySearch.add(s);
                    historySearchAdapter.notifyDataSetChanged();
                    textView.setText("");
                    InputMethodManager imm = (InputMethodManager) textView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(textView.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });




        return root;
    }


}