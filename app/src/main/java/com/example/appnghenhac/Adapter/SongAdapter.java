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
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnghenhac.Activity.PlayMusicActivity;
import com.example.appnghenhac.Model.BaiHat;
import com.example.appnghenhac.R;
import com.example.appnghenhac.Retrofit2.APIService;
import com.example.appnghenhac.Retrofit2.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    Context context;
    ArrayList<BaiHat> arrayListBaohat;

    public SongAdapter(Context context, ArrayList<BaiHat> arrayListBaohat) {
        this.context = context;
        this.arrayListBaohat = arrayListBaohat;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater =     LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_baihat,null);

        return new ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        BaiHat baiHat = arrayListBaohat.get(position);
        holder.txtname.setText(baiHat.getTenbaihat());
        holder.txtcasi.setText(baiHat.getCasi());
        Picasso.with(context).load(baiHat.getHinhbaihat()).into(holder.imageView_hinh);


    }

    @Override
    public int getItemCount() {
        return arrayListBaohat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtname,txtcasi;
        ImageView imageView_hinh,imageView_luotthich;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtcasi = itemView.findViewById(R.id.textviewcasibaihathot);
            txtname = itemView.findViewById(R.id.textviewbaihathot);
            imageView_hinh = itemView.findViewById(R.id.imageviewbaihathot);
            imageView_luotthich = itemView.findViewById(R.id.imageviewluotthich);
            imageView_luotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    imageView_luotthich.setImageResource(R.drawable.iconloved);
                    DataService dataService = APIService.getSerive();
                    Call<String> callback = dataService.UpdataLuotThich("1",arrayListBaohat.get(getPosition()).getIdBaiHat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if (ketqua.equals("Success")){
                                Toast.makeText(context, "Bạn Đã Thích Bài: "+arrayListBaohat.get(getPosition()).getTenbaihat(), Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imageView_luotthich.setEnabled(false);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);

                    intent.putExtra("cakhuc",arrayListBaohat.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}
