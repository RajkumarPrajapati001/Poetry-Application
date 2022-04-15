package com.example.poetry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Switch;
import android.widget.Toast;

import com.example.poetry.Adapters.PoetryAdapters;
import com.example.poetry.Api.ApiClient;
import com.example.poetry.Api.Apiinterface;
import com.example.poetry.Models.PoetryModel;
import com.example.poetry.Response.Getpoetryresponse;
import com.example.poetry.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    RecyclerView recyclerView;
    PoetryAdapters poetryAdapters;
    Apiinterface apiinterface;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setSupportActionBar(toolbar);
        intialization();
        getdata();


        binding.mainBottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.bNavHome:
                      return true;

//                    case R.id.bNavFavorite:
//                       startActivity(new Intent(getApplicationContext(),Login.class));
//                       overridePendingTransition(0,0);
//                        return true;

                    case R.id.bNavAccount:
                        startActivity(new Intent(getApplicationContext(),Account.class));
                        overridePendingTransition(0,0);
                        return true;

                }

                return false;
            }
        });


    }

    private void intialization() {
        recyclerView = findViewById(R.id.poetryrecycle);
//        toolbar = findViewById(R.id.poetrytoolbar);

    }

    private void setadapter(List<PoetryModel> poetryModels) {
        poetryAdapters = new PoetryAdapters(this, poetryModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(poetryAdapters);
    }

    private void getdata() {
        ApiClient.apiInterface().getpoetry().enqueue(new Callback<List<PoetryModel>>() {
            @Override
            public void onResponse(Call<List<PoetryModel>> call, Response<List<PoetryModel>> response) {
                List<PoetryModel> poetryModelList = new ArrayList<>();
                poetryModelList = response.body();
                setadapter(poetryModelList);

            }

            @Override
            public void onFailure(Call<List<PoetryModel>> call, Throwable t) {
            Log.e("fail",t.getLocalizedMessage());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.add:
                Toast.makeText(this, "Add Clicked", Toast.LENGTH_SHORT).show();
            default:
                return true;
        }
    }
}