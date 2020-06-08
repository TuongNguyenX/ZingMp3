package com.example.appnghenhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appnghenhac.Adapter.DanhsachtatcaalbumAdapter;
import com.example.appnghenhac.Model.Album;
import com.example.appnghenhac.R;
import com.example.appnghenhac.Retrofit2.APIService;
import com.example.appnghenhac.Retrofit2.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtatcaalbumActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView_Album;
    DanhsachtatcaalbumAdapter danhsachtatcaalbumAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtatcaalbum);
        init();
        getData();

    }

    private void getData() {
        DataService dataService = APIService.getSerive();
        Call<List<Album>> callback = dataService.GetAllAlbum();

        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumArrayList= (ArrayList<Album>) response.body();
                danhsachtatcaalbumAdapter = new DanhsachtatcaalbumAdapter(DanhsachtatcaalbumActivity.this,albumArrayList);
                recyclerView_Album.setLayoutManager(new GridLayoutManager(DanhsachtatcaalbumActivity.this,2));
                recyclerView_Album.setAdapter(danhsachtatcaalbumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void init() {
        toolbar = findViewById(R.id.toolbardanhsachtatcaalbum);
        recyclerView_Album = findViewById(R.id.recycler_Danhsachtatcaalbum);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tat cac Album");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
