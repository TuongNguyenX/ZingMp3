package com.example.appnghenhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnghenhac.Activity.DanhsachbaihatActivity;
import com.example.appnghenhac.Model.Album;
import com.example.appnghenhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    Context context;
    ArrayList<Album> albumArrayList;

    public AlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater =     LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_album,null);

        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {

        Album album = albumArrayList.get(position);
        holder.textView_TenAlbum.setText(album.getTenAlbum());
        holder.textView_CaSi.setText(album.getTencasiAlbum());
        Picasso.with(context).load(album.getHinhanhAlbum()).into(holder.imageView_Album);

    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }


    public class AlbumViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView_Album;
        TextView textView_CaSi,textView_TenAlbum;
        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_Album = itemView.findViewById(R.id.imageview_album);
            textView_CaSi = itemView.findViewById(R.id.textviewtencasi);
            textView_TenAlbum = itemView.findViewById(R.id.textviewalbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);

                    intent.putExtra("album",albumArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });



        }
    }
}
