package com.example.appnghenhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.appnghenhac.Adapter.DanhsachplaylistAdapter;
import com.example.appnghenhac.Model.PlayList;
import com.example.appnghenhac.R;
import com.example.appnghenhac.Retrofit2.APIService;
import com.example.appnghenhac.Retrofit2.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachcacplaylistActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<PlayList> playListArrayList;
    DanhsachplaylistAdapter danhsachplaylistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachcacplaylist);

        toolbar = findViewById(R.id.toolbardanhsaccacplaylist);
        recyclerView = findViewById(R.id.recycler_Danhsachcacplaylist);
        settoolBar();
        getData();

    }

    private void getData() {
        final DataService dataService = APIService.getSerive();
        Call<List<PlayList>> listCall = dataService.GetPlayList();

        listCall.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                playListArrayList = (ArrayList<PlayList>) response.body();

                danhsachplaylistAdapter = new DanhsachplaylistAdapter(DanhsachcacplaylistActivity.this,playListArrayList);
                recyclerView.setLayoutManager(new GridLayoutManager(DanhsachcacplaylistActivity.this,2));
                recyclerView.setAdapter(danhsachplaylistAdapter);
            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }

    private void settoolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Playllits");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
