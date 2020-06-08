package com.example.appnghenhac.Retrofit2;

import com.example.appnghenhac.Model.Album;
import com.example.appnghenhac.Model.BaiHat;
import com.example.appnghenhac.Model.ChuDe;
import com.example.appnghenhac.Model.ChuDeTheLoai;
import com.example.appnghenhac.Model.PlayList;
import com.example.appnghenhac.Model.QuangCao;
import com.example.appnghenhac.Model.TheLoai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {

    @GET("Songbanner.php")
    Call<List<QuangCao>> GetDataBanner();

    @GET("PlayListForCurrentDay.php")
    Call<List<PlayList>> GetPlayListCurrentDay();


    @GET("ChuDevaTheLoai.php")
    Call<ChuDeTheLoai> GetChuDeTheLoai();

    @GET("Albumhot.php")
    Call<List<Album>> GetAlbum();


    @GET("BaiHatDuocYeuThich.php")
    Call<List<BaiHat>> GetBaiHatHot();

    @FormUrlEncoded
    @POST("DanhSachBaiHat.php")
    Call<List<BaiHat>> Getdanhsachbaihattheoquancao(@Field("idquangcao") String idquangcao  );

    @FormUrlEncoded
    @POST("DanhSachBaiHat.php")
    Call<List<BaiHat>> GetdanhsachPlayList(@Field("idplaylist") String idplaylist);

    @GET("Danhsachcacplaylist.php")
    Call<List<PlayList>> GetPlayList();

    @GET("Tatcachude.php")
    Call<List<ChuDe>> Getdanhchuchude();
    @FormUrlEncoded
    @POST("DanhSachBaiHat.php")
    Call<List<BaiHat>> GetDanhsachbaihattheochude(@Field("idtheloai") String idtheloai);



    @FormUrlEncoded
    @POST("Theloaitheochude.php")
    Call<List<TheLoai>> GetTheloaitheochude(@Field("idchude") String idchude);

    @GET("Tatcaalbum.php")
    Call<List<Album>> GetAllAlbum();

    @FormUrlEncoded
    @POST("DanhSachBaiHat.php")
    Call<List<BaiHat>> GetDanhsachbaihattheoalbum(@Field("idalbum") String idalbum);


    @FormUrlEncoded
    @POST("Updatabaihatduocyeuthich.php")
    Call<String> UpdataLuotThich(@Field("luotthich") String luotthich,@Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("SearchSong.php")
    Call<List<BaiHat>> GetSearch(@Field("tukhoa") String tukhoa);

}
