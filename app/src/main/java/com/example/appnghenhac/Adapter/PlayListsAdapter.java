package com.example.appnghenhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnghenhac.Activity.DanhsachbaihatActivity;
import com.example.appnghenhac.Model.PlayList;
import com.example.appnghenhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlayListsAdapter extends RecyclerView.Adapter<PlayListsAdapter.ViewHolder> {


    Context context;
    ArrayList<PlayList> playListArrayList;

    public PlayListsAdapter(Context context, ArrayList<PlayList> playListArrayList) {
        this.context = context;
        this.playListArrayList = playListArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
       View view =  layoutInflater.inflate(R.layout.dong_playlist,null);
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlayList playList = playListArrayList.get(position);

        holder.txttenplaylist.setText(playList.getTen());
        Picasso.with(context).load(playList.getHinh()).into(holder.imgbackground);
        Picasso.with(context).load(playList.getIcon()).into(holder.imgplaylist);

    }

    @Override
    public int getItemCount() {
        return playListArrayList.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder {

        TextView txttenplaylist;
        ImageView imgbackground,imgplaylist;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttenplaylist = itemView.findViewById(R.id.textviewtenplaylist);
            imgbackground = itemView.findViewById(R.id.imageviewbackgroundplaylist);
            imgplaylist = itemView.findViewById(R.id.imageviewplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("itemplaylist", playListArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
