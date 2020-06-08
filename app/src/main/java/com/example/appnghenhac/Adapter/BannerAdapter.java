package com.example.appnghenhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.appnghenhac.Activity.DanhsachbaihatActivity;
import com.example.appnghenhac.Model.QuangCao;
import com.example.appnghenhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {


    Context context;
    ArrayList<QuangCao> arrayListBanner;

    public BannerAdapter(Context context, ArrayList<QuangCao> arrayListBanner) {
        this.context = context;
        this.arrayListBanner = arrayListBanner;
    }


    @Override
    public int getCount() {
        return arrayListBanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_banner,null);
        final ImageView imageViewbackgroudbanner = view.findViewById(R.id.imageviewbackgroundbanner);
        ImageView imageViewsongbaihat = view.findViewById(R.id.imageview_banner);
        TextView textViewTitleBanner = view.findViewById(R.id.textviewtitlebanner);
        TextView textViewNoidung = view.findViewById(R.id.textviewnoidung);

        Picasso.with(context).load(arrayListBanner.get(position).getHinhanh()).into(imageViewbackgroudbanner);
        Picasso.with(context).load(arrayListBanner.get(position).getHinhBaiHat()).into(imageViewsongbaihat);
        textViewTitleBanner.setText(arrayListBanner.get(position).getTenBaiHat());
        textViewNoidung.setText(arrayListBanner.get(position).getNoidung());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                intent.putExtra("banner",arrayListBanner.get(position));
                context.startActivity(intent);

            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
