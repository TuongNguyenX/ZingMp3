package com.example.appnghenhac.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appnghenhac.Adapter.PlayListAdapter;
import com.example.appnghenhac.Adapter.PlayListsAdapter;
import com.example.appnghenhac.Model.PlayList;
import com.example.appnghenhac.R;
import com.example.appnghenhac.Retrofit2.APIService;
import com.example.appnghenhac.Retrofit2.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayListsFragment extends Fragment {

    RecyclerView recyclerView;
    PlayListsAdapter playListAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_play_lists, container, false);
        recyclerView = view.findViewById(R.id.recycler_Playlistss);

        getData();


        return view;
    }

    private void getData() {
        DataService dataService = APIService.getSerive();
        Call<List<PlayList>> callback = dataService.GetPlayListCurrentDay();

        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                final ArrayList<PlayList> mangPlayList = (ArrayList<PlayList>) response.body();
                playListAdapter = new PlayListsAdapter(getActivity(),mangPlayList);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(playListAdapter);
                playListAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }
}
