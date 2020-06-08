package com.example.appnghenhac.Retrofit2;

public class APIService {

    private static String base_url = "https://zingmp3s.000webhostapp.com/Server/";
    public static DataService getSerive(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
