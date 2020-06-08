package com.example.appnghenhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnghenhac.Activity.DanhsachbaihatActivity;
import com.example.appnghenhac.Model.PlayList;
import com.example.appnghenhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachplaylistAdapter extends RecyclerView.Adapter<DanhsachplaylistAdapter.ViewHolder> {

    Context context;
    ArrayList<PlayList> playListArrayList;

    public DanhsachplaylistAdapter(Context context, ArrayList<PlayList> playListArrayList) {
        this.context = context;
        this.playListArrayList = playListArrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_danh_sach_cac_play_list, null);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PlayList playList = playListArrayList.get(position);
        Picasso.with(context).load(playList.getHinh()).into(holder.imageView_hinhnen);
        holder.textView_playlist.setText(playList.getTen());
    }

    @Override
    public int getItemCount() {
        return playListArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView_playlist;
        ImageView imageView_hinhnen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_playlist = itemView.findViewById(R.id.textviewtendanhsachcacplaylist);
            imageView_hinhnen = itemView.findViewById(R.id.imageviewdanhsachcacplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("itemplaylist",playListArrayList.get(getPosition()));
                    context.startActivity(intent);


                }
            });
        }
    }
}
