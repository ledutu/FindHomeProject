package com.example.findhomeproject.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
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
        arrMotelNews.add(new MotelNews("Phòng 1PN và 2PN tại Bành Văn Trân, Tân Bình",
                "Phòng Cao Cấp Full Nội Thất 1PN và 2PN " +
                        "NGAY GẦN NGÃ TƯ BẢY HIỀN " +
                        "(149 Bành Văn Trân, P.7, Q.Tân Bình)" +
                        "Phòng mới xây đang hoàn thiện nội thất ( Phòng Mới 100%), tháng 9 dọn vào ở. " +
                        "Cho những ai thích căn phòng sang trọng ở khu vực trầm lắng yên bình.", arrImage[0]));
        arrMotelNews.add(new MotelNews("Hoàng hoa thám còn hải phòng to co ban công",
                "Con hai phòng to co ban công " +
                        "Giơ giấc tư do " +
                        "Loi đi riêng " +
                        "Khu an ninh sạch sẽ " +
                        "Sat chợ hoang hoa thám " +
                        "Co the ở do 3-5n " +
                        "Uu tiên nhân viên van phòng.hộ gia đình " +
                        "Ai co nhu câu liên hê kim",
                arrImage[2]));
        arrMotelNews.add(new MotelNews("Phòng 1PN và 2PN tại Bành Văn Trân, Tân Bình",
                "Phòng Cao Cấp Full Nội Thất 1PN và 2PN " +
                        "NGAY GẦN NGÃ TƯ BẢY HIỀN " +
                        "(149 Bành Văn Trân, P.7, Q.Tân Bình)" +
                        "Phòng mới xây đang hoàn thiện nội thất ( Phòng Mới 100%), tháng 9 dọn vào ở. " +
                        "Cho những ai thích căn phòng sang trọng ở khu vực trầm lắng yên bình.",
                arrImage[3]));
        arrMotelNews.add(new MotelNews("Phòng Đủ tiện nghi QUẬN 7",
                "Cần cho thuê phòng đẹp, đủ tiện nghi cơ bản xách vali vào là ở " +
                        "1.Nhà rất đẹp và ngay mặt dường hẻm xe hơi " +
                        "-WC riêng " +
                        "2. Phòng trang bị đầy đủ tiện nghi, nước nóng năng lượng mặt trời, giường, tủ, " +
                        "máy lạnh, diện tích từ 20m2- 25m2 tuỳ lớn nhỏ",
                arrImage[4]));
        motelNewsAdapter.notifyDataSetChanged();
    }


}