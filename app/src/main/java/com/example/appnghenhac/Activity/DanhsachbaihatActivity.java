package com.example.appnghenhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appnghenhac.Adapter.DanhsachbaihatAdapter;
import com.example.appnghenhac.Model.Album;
import com.example.appnghenhac.Model.BaiHat;
import com.example.appnghenhac.Model.PlayList;
import com.example.appnghenhac.Model.QuangCao;
import com.example.appnghenhac.Model.TheLoai;
import com.example.appnghenhac.R;
import com.example.appnghenhac.Retrofit2.APIService;
import com.example.appnghenhac.Retrofit2.DataService;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.QueryName;

public class DanhsachbaihatActivity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    RecyclerView recyclerView;
    Toolbar toolbar;
    FloatingActionButton floatingActionButton;
    QuangCao quangCao;
    PlayList playList;
    TheLoai theLoai;
    Album album;
    ImageView imageView_danhsachcakhuc;

    ArrayList<BaiHat> mangbaihat;
    DanhsachbaihatAdapter danhsachbaihatAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        recyclerView = findViewById(R.id.recycler_Danhbaihat);
        toolbar = findViewById(R.id.toobardanhsach);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setEnabled(false);
        imageView_danhsachcakhuc = findViewById(R.id.imageviewdanhsachcakhuc);


        toolbarHome();
        getDataIntent();

        if (quangCao != null && !quangCao.getTenBaiHat().equals("")){
            setValueInView(quangCao.getTenBaiHat(),quangCao.getHinhBaiHat());
            GetDataQuangCao(quangCao.getIdQuangcao());
        }
        if (playList != null && !playList.getTen().equals("")){
            setValueInView(playList.getTen(),playList.getHinh());
            getDatPlayList(playList.getIdPlayList());
        }
        if (theLoai != null && !theLoai.getTenTheLoai().equals("")){
            setValueInView(theLoai.getTenTheLoai(),theLoai.getHinhTheLoai());
            getDatTheLoai(theLoai.getIdTheLoai());
        }
        if (album != null && !album.getTenAlbum().equals("")){
            setValueInView(album.getTenAlbum(),album.getHinhanhAlbum());
            getDataAlbum(album.getIdAlbum());
        }






    }

    private void getDataAlbum(String idAlbum) {
        DataService dataService = APIService.getSerive();
        Call<List<BaiHat>> callback = dataService.GetDanhsachbaihattheoalbum(idAlbum);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> baiHatArrayList = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this,baiHatArrayList);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerView.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void getDatTheLoai(String idTheLoai) {
        DataService dataService = APIService.getSerive();
        Call<List<BaiHat>> callback = dataService.GetDanhsachbaihattheochude(idTheLoai);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this,mangbaihat);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerView.setAdapter(danhsachbaihatAdapter);
                eventClick();

            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void getDatPlayList(String idPlayList) {
        DataService dataService = APIService.getSerive();
        Call<List<BaiHat>> callback = dataService.GetdanhsachPlayList(idPlayList);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this,mangbaihat);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerView.setAdapter(danhsachbaihatAdapter);

                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetDataQuangCao(String idQuangcao) {
        DataService dataService = APIService.getSerive();
        Call<List<BaiHat>> callback = dataService.Getdanhsachbaihattheoquancao(idQuangcao);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
//                Log.d("EEE",mangbaihat.get(0).getTenbaihat());
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this,mangbaihat);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerView.setAdapter(danhsachbaihatAdapter);
                eventClick();

            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void setValueInView(String ten , String hinh  ) {
            collapsingToolbarLayout.setTitle(ten);
        try {
            URL url = new URL(hinh);
            Bitmap bitmap  = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable  bitmapDrawable  = new BitmapDrawable(getResources(),bitmap);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
                collapsingToolbarLayout.setBackground(bitmapDrawable);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(hinh).into(imageView_danhsachcakhuc);
    }

    private void toolbarHome() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
    }

    private void getDataIntent() {
        Intent intent= getIntent();
        if (intent != null){
            if (intent.hasExtra("banner")){
                quangCao = (QuangCao) intent.getSerializableExtra("banner");
                Toast.makeText(this, quangCao.getTenBaiHat(), Toast.LENGTH_SHORT).show();
            }
            if (intent.hasExtra("itemplaylist")){
                playList = (PlayList)intent.getSerializableExtra("itemplaylist");

            }
            if (intent.hasExtra("idtheloai")){
                theLoai = (TheLoai) intent.getSerializableExtra("idtheloai");

            }
            if (intent.hasExtra("album")){
                album = (Album) intent.getSerializableExtra("album");
                Toast.makeText(this, album.getTenAlbum(), Toast.LENGTH_SHORT).show();

            }
        }

    }
    private void eventClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhsachbaihatActivity.this,PlayMusicActivity.class);
                intent.putExtra("cacbaihat",mangbaihat);
                startActivity(intent);

            }
        });
    }
}
