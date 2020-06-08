package com.example.appnghenhac.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.appnghenhac.Activity.DanhsachtatcaalbumActivity;
import com.example.appnghenhac.Adapter.AlbumAdapter;
import com.example.appnghenhac.Model.Album;
import com.example.appnghenhac.R;
import com.example.appnghenhac.Retrofit2.APIService;
import com.example.appnghenhac.Retrofit2.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AlbumFragment extends Fragment {
    RecyclerView.LayoutManager layoutManager;
    AlbumAdapter albumAdapter;
    RecyclerView recyclerView_Album;
    TextView textView_Albumxemthem;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_album, container, false);
        recyclerView_Album = view.findViewById(R.id.recycler_Album);
        textView_Albumxemthem = view.findViewById(R.id.textviewxemthemalbum);
        textView_Albumxemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhsachtatcaalbumActivity.class);
                startActivity(intent);
            }
        });
        getData();
        return view;
    }

    private void getData() {

        DataService dataService = APIService.getSerive();
        Call<List<Album>> callback  = dataService.GetAlbum();

        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumArrayList = (ArrayList<Album>) response.body();
                albumAdapter = new AlbumAdapter(getActivity(),albumArrayList);

                layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);

                recyclerView_Album.setLayoutManager(layoutManager);
                recyclerView_Album.setAdapter(albumAdapter);
                recyclerView_Album.setHasFixedSize(true);
                albumAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }
}
