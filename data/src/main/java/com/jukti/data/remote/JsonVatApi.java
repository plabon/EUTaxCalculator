package com.jukti.data.remote;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Pl@b0n on 01,October,2018
 */
public interface JsonVatApi {

    String BASE_URL = "https://jsonvat.com";

    @GET("/")
    Call<ResponseBody> getCurrencyData();

}
