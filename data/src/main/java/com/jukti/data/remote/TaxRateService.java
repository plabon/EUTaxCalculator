package com.jukti.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Pl@b0n on 01,October,2018
 */
public class TaxRateService {

    private static TaxRateService instance;
    public static TaxRateService getInstance(){
        if(instance==null){
            instance=new TaxRateService();
        }
        return instance;
    }

    public JsonVatApi getGithubApiService() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JsonVatApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(JsonVatApi.class);
    }
}
