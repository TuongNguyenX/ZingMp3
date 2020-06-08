package com.example.appnghenhac.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.appnghenhac.Activity.DanhsachbaihatActivity;
import com.example.appnghenhac.Activity.DanhsachtatcachudeActivity;
import com.example.appnghenhac.Activity.DanhsachtheloaitheochudeActivity;
import com.example.appnghenhac.Model.ChuDe;
import com.example.appnghenhac.Model.ChuDeTheLoai;
import com.example.appnghenhac.Model.PlayList;
import com.example.appnghenhac.Model.QuangCao;
import com.example.appnghenhac.Model.TheLoai;
import com.example.appnghenhac.R;
import com.example.appnghenhac.Retrofit2.APIService;
import com.example.appnghenhac.Retrofit2.DataService;
import com.squareup.picasso.Picasso;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChuDeTheLoaiToDayFragment extends Fragment {




    View view;
    HorizontalScrollView horizontalScrollView;
    TextView textViewxemthemchudetheloai;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chu_de_the_loai_to_day, container, false);

        horizontalScrollView = view.findViewById(R.id.horizontalScrollView);
        textViewxemthemchudetheloai = view.findViewById(R.id.textviewxemthemchudetheloai);
        textViewxemthemchudetheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhsachtatcachudeActivity.class);

//                intent.putExtra("");
                startActivity(intent);
            }
        });

        getData();

        return view;
    }

    private void getData() {
        DataService dataService = APIService.getSerive();
        Call<ChuDeTheLoai> callback = dataService.GetChuDeTheLoai();

        callback.enqueue(new Callback<ChuDeTheLoai>() {
            @Override
            public void onResponse(Call<ChuDeTheLoai> call, Response<ChuDeTheLoai> response) {
                ChuDeTheLoai chuDeVaTheLoai = (ChuDeTheLoai) response.body();

                final ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();
                chuDeArrayList.addAll(chuDeVaTheLoai.getChuDe());

                final ArrayList<TheLoai> theLoaiArrayList = new ArrayList<>();
                theLoaiArrayList.addAll(chuDeVaTheLoai.getTheLoai());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(580,250);
                layout.setMargins(10,20,10,30);

                for (int i = 0; i< chuDeArrayList.size();i++){
                    CardView cardView  = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (chuDeArrayList.get(i).getHinhChuDe() != null){
                        Picasso.with(getActivity()).load(chuDeArrayList.get(i).getHinhChuDe()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), DanhsachtheloaitheochudeActivity.class);
                            intent.putExtra("chude",chuDeArrayList.get(finalI));
                            startActivity(intent);
                        }
                    });
                }

                for (int j = 0; j < chuDeArrayList.size();j++){
                    CardView cardView  = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (theLoaiArrayList.get(j).getHinhTheLoai() != null){
                        Picasso.with(getActivity()).load(theLoaiArrayList.get(j).getHinhTheLoai()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final  int finnalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), DanhsachbaihatActivity.class);
                            intent.putExtra("idtheloai",theLoaiArrayList.get(finnalJ));

                            startActivity(intent);
                        }
                    });

                }
                horizontalScrollView.addView(linearLayout);

            }

            @Override
            public void onFailure(Call<ChuDeTheLoai> call, Throwable t) {

            }
        });
    }
}
