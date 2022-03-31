 package com.example.poetry.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.poetry.Api.ApiClient;
import com.example.poetry.Api.Apiinterface;
import com.example.poetry.Models.PoetryModel;
import com.example.poetry.R;
import com.example.poetry.Response.Deletereponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PoetryAdapters extends RecyclerView.Adapter<PoetryAdapters.ViewHolder>{

    Context context;
    List<PoetryModel> poetryModels;
    Apiinterface apiinterface;

    public PoetryAdapters(Context context, List<PoetryModel> poetryModels) {
        this.context = context;
        this.poetryModels = poetryModels;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.poetry_list,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.poetryname.setText(poetryModels.get(position).getPoet_name());
        holder.poerty.setText(poetryModels.get(position).getPoetry_data());
        holder.date_time.setText(poetryModels.get(position).getDate_time());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            deletepoetry(poetryModels.get(position).getId()+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return poetryModels.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView poetryname,poerty,date_time;
        AppCompatButton update,delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            poetryname = itemView.findViewById(R.id.txtpoetryName);
            poerty = itemView.findViewById(R.id.txtpoetryData);
            date_time = itemView.findViewById(R.id.txtpoetryDateandTime);
            update = itemView.findViewById(R.id.btnpoetryUPDATE);
            delete = itemView.findViewById(R.id.btnpoetryDELETE);
        }
    }

    private  void deletepoetry(String id) {
        ApiClient.apiInterface().deletepoetry(id).enqueue(new Callback<List<PoetryModel>>() {
            @Override
            public void onResponse(Call<List<PoetryModel>> call, Response<List<PoetryModel>> response) {
                List<PoetryModel> poetryModelList = new ArrayList<>();
                poetryModelList = response.body();
//                poetryModels.remove(pose);
//                notifyDataSetChanged();

//                try {
//                    if(response!==null){
//                        Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
//                    }
//                }catch (Exception e){
//                    Log.e("exp",e.getLocalizedMessage());
//                }
            }

            @Override
            public void onFailure(Call<List<PoetryModel>> call, Throwable t) {
//                Log.e("fail", t.getLocalizedMessage());
            }
        });
    }

}
