package com.example.appnghenhac.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appnghenhac.Adapter.SongAdapter;
import com.example.appnghenhac.Model.BaiHat;
import com.example.appnghenhac.R;
import com.example.appnghenhac.Retrofit2.APIService;
import com.example.appnghenhac.Retrofit2.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SongsFragment extends Fragment {
    SongAdapter songAdapter;
    RecyclerView recyclerView_Song;
    LinearLayoutManager linearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_songs, container, false);
        recyclerView_Song = view.findViewById(R.id.recycler_Song);
        getData();
        return view;
    }

    private void getData() {
        DataService dataService = APIService.getSerive();
        Call<List<BaiHat>> callback = dataService.GetBaiHatHot();
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> baiHatArrayList = (ArrayList<BaiHat>) response.body();
                songAdapter =new SongAdapter(getActivity(),baiHatArrayList);
                linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView_Song.setLayoutManager(linearLayoutManager);
                recyclerView_Song.setAdapter(songAdapter);
                songAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });

    }
}
