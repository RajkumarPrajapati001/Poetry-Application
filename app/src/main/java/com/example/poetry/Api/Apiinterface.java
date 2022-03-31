package com.example.poetry.Api;

import com.example.poetry.Models.PoetryModel;
import com.example.poetry.Response.Deletereponse;
import com.example.poetry.Response.Getpoetryresponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Apiinterface {

    @GET("getread.php")
    Call<List<PoetryModel>> getpoetry();

    @FormUrlEncoded
    @POST("delete.php")
    Call<List<PoetryModel>> deletepoetry(@Field("poetry_id") String poetry_id);

}
