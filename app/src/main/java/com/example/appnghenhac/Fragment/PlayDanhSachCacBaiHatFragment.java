package com.example.appnghenhac.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appnghenhac.Activity.PlayMusicActivity;
import com.example.appnghenhac.Adapter.PlayNhacAdapter;
import com.example.appnghenhac.R;


public class PlayDanhSachCacBaiHatFragment extends Fragment {



    PlayNhacAdapter playNhacAdapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_play_danh_sach_cac_bai_hat, container, false);

        recyclerView= view.findViewById(R.id.recycler_Playbaihat);
        if (PlayMusicActivity.mangbaihat.size() >0){
            playNhacAdapter = new PlayNhacAdapter(getActivity(), PlayMusicActivity.mangbaihat);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(playNhacAdapter);
        }

        return view;
    }
}
