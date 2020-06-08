package com.example.appnghenhac.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appnghenhac.Model.PlayList;
import com.example.appnghenhac.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlayListAdapter extends ArrayAdapter {
    public PlayListAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }
    public class ViewHolder{
        TextView txttenplaylist;
        ImageView imgbackground,imgplaylist;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.dong_playlist,null);
            viewHolder= new ViewHolder();
            viewHolder.imgbackground = convertView.findViewById(R.id.imageviewbackgroundplaylist);
            viewHolder.imgplaylist = convertView.findViewById(R.id.imageviewplaylist);
            viewHolder.txttenplaylist = convertView.findViewById(R.id.textviewtenplaylist);

            convertView.setTag(viewHolder);




        }else {
            viewHolder  = (ViewHolder)convertView.getTag();
        }
        PlayList playList  = (PlayList) getItem(position);
        Picasso.with(getContext()).load(playList.getHinh()).into(viewHolder.imgbackground);
        Picasso.with(getContext()).load(playList.getIcon()).into(viewHolder.imgplaylist);
        viewHolder.txttenplaylist.setText(playList.getTen());



        return convertView;
    }
}
