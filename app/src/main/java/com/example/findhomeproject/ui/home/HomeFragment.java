package com.example.findhomeproject.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.findhomeproject.R;
import com.example.findhomeproject.adapter.MotelNewsAdapter;
import com.example.findhomeproject.modelForMotel.MotelNews;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ArrayList<MotelNews> arrMotelNews;
    MotelNewsAdapter motelNewsAdapter;
    ListView lvMotelNews;
    private int[] arrImage = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4,
            R.drawable.image5, R.drawable.image6};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View home = inflater.inflate(R.layout.fragment_home, container, false);
        lvMotelNews = home.findViewById(R.id.lvMotelNews);
        arrMotelNews = new ArrayList<>();
        motelNewsAdapter = new MotelNewsAdapter(getActivity(), R.layout.item_motel_news, arrMotelNews);
        lvMotelNews.setAdapter(motelNewsAdapter);
        addData();
        return home;
    }

    private void addData() {
        arrMotelNews.add(new MotelNews(
                233333,
                arrImage[1],
                "Phòng trọ đặc biệt Lê Văn Lương",
                "441/21 Lê Văn Lương Quận 7",
                "0388037094 - A Tung",
                "15 m2",
                "1 ngày trước",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
                ));

        arrMotelNews.add(new MotelNews(
                300000,
                arrImage[0],
                "Why do we use it?",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "0388037094 - C Tien",
                "100 m2",
                "10 ngày trước",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
        ));
        motelNewsAdapter.notifyDataSetChanged();
    }


}