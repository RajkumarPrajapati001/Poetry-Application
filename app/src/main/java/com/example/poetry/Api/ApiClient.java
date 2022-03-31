package com.example.poetry.Api;

import android.app.KeyguardManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit  RETROFIT = null;

    public static Apiinterface apiInterface() {
        if (RETROFIT == null) {

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            RETROFIT = new Retrofit.Builder()
                    .baseUrl("http://172.31.240.1/DataAdd/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return RETROFIT.create(Apiinterface.class);
    }
//
//
//    public  static  Retrofit getclient() {
//
//        if(RETROFIT == null) {
//
//            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
//
//            Gson gson = new GsonBuilder().create();
//
//            RETROFIT = new Retrofit.Builder()
//                    .baseUrl("http://172.25.224.1/DataAdd/")
//                    .client(okHttpClient)
//                    .addConverterFactory(GsonConverterFactory.create(gson)).build();
//        }
//        return  RETROFIT.create(Apiinterface.class);
//    }


}

