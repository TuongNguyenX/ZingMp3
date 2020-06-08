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
import com.example.appnghenhac.Fragment.SearchFragment;
import com.example.appnghenhac.Model.BaiHat;
import com.example.appnghenhac.R;
import com.example.appnghenhac.Retrofit2.APIService;
import com.example.appnghenhac.Retrofit2.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchBaiHatAdapter extends RecyclerView.Adapter<SearchBaiHatAdapter.ViewHolder>{

    Context context;
    ArrayList<BaiHat> baiHatArrayList;

    public SearchBaiHatAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_searchbaihat,null);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        BaiHat baiHat = baiHatArrayList.get(position);
        Picasso.with(context).load(baiHat.getHinhbaihat()).into(holder.imageViewhinh);
        holder.textViewtenbaihat.setText(baiHat.getTenbaihat());
        holder.textViewcasi.setText(baiHat.getCasi());

    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewhinh,imageView_luothich;
        TextView textViewtenbaihat;
        TextView textViewcasi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewhinh = itemView.findViewById(R.id.imgseachbaihat);
            textViewcasi = itemView.findViewById(R.id.txtsearchtenbaihat);
            textViewtenbaihat = itemView.findViewById(R.id.txtcasisearch);
            imageView_luothich = itemView.findViewById(R.id.imageviewluotthichsearch);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("cakhuc",baiHatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            imageView_luothich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageView_luothich.setImageResource(R.drawable.iconloved);
                    DataService dataService = APIService.getSerive();
                    Call<String> callback = dataService.UpdataLuotThich("1",baiHatArrayList.get(getPosition()).getIdBaiHat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if (ketqua.equals("Success")){
                                Toast.makeText(context, "Bạn Đã Thích Bài: "+baiHatArrayList.get(getPosition()).getTenbaihat(), Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imageView_luothich.setEnabled(false);
                }
            });
        }
    }

}
