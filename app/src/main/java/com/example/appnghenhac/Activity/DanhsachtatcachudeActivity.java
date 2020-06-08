package com.example.appnghenhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.appnghenhac.Adapter.DanhsachtatcachudeAdapter;
import com.example.appnghenhac.Model.ChuDe;
import com.example.appnghenhac.R;
import com.example.appnghenhac.Retrofit2.APIService;
import com.example.appnghenhac.Retrofit2.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtatcachudeActivity extends AppCompatActivity {

    DanhsachtatcachudeAdapter danhsachtatcachudeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtatcachude);
        toolbar();
        recyclerview();
        getData();

    }

    private void getData() {
        DataService dataService = APIService.getSerive();
        Call<List<ChuDe>> callback = dataService.Getdanhchuchude();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> mangchude = (ArrayList<ChuDe>) response.body();
                danhsachtatcachudeAdapter = new DanhsachtatcachudeAdapter(DanhsachtatcachudeActivity.this,mangchude);
                RecyclerView recyclerView = findViewById(R.id.recycler_Danhsachcacchude);
                recyclerView.setLayoutManager(new GridLayoutManager(DanhsachtatcachudeActivity.this,1));
                recyclerView.setAdapter(danhsachtatcachudeAdapter);
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void recyclerview() {


    }

    private void toolbar() {
        Toolbar toolbar = findViewById(R.id.toolbardanhsaccacchude);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ChuDe");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
