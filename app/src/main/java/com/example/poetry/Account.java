package com.example.poetry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.example.poetry.databinding.ActivityAccountBinding;
import com.google.android.material.navigation.NavigationBarView;

public class Account extends AppCompatActivity {

    private ActivityAccountBinding binding;
    private SharedPreferences spf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        spf = Account.this.getSharedPreferences("UserDetails" , Context.MODE_PRIVATE);
        binding.txName.setText(spf.getString("UserName" , null));
        binding.txEmail.setText(spf.getString("UserEmail" , null));

        binding.mainBottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.bNavHome:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

//                    case R.id.bNavFavorite:
//                        startActivity(new Intent(getApplicationContext(),Login.class));
//                        overridePendingTransition(0,0);
//                        return true;

                    case R.id.bNavAccount:
                        startActivity(new Intent(getApplicationContext(),Account.class));
                        overridePendingTransition(0,0);
                        return true;

                }

                return false;
            }
        });

        binding.btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences spf = Account.this.getSharedPreferences("UserDetails" , Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = spf.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(Account.this, Signup.class);
                startActivity(intent);
               // requireActivity().finishAffinity();
            }
        });

    }
}