package com.example.poetry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.poetry.databinding.ActivitySignupBinding;

public class Signup extends AppCompatActivity {

   private ActivitySignupBinding binding;
   private String UserName, Email, PhoneNumber, Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding.Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.name.getText().toString().isEmpty()) {
                    Toast.makeText(Signup.this, "Please Enter Name ", Toast.LENGTH_SHORT).show();
                } else if (binding.name.getText().toString().length() < 4) {
                    Toast.makeText(Signup.this, "Name must be Minimum 3 characters", Toast.LENGTH_SHORT).show();
                } else if (binding.email.getText().toString().isEmpty()) {
                    Toast.makeText(Signup.this, "Please Enter Email In Valid Format ", Toast.LENGTH_SHORT).show();
                } else if (!(binding.email.getText().toString().contains("@"))) {
                    Toast.makeText(Signup.this, "Please Enter Email In Valid Format ", Toast.LENGTH_SHORT).show();
                } else if (!(binding.email.getText().toString().endsWith(".com"))) {
                    Toast.makeText(Signup.this, "Please Enter Email In Valid Format ", Toast.LENGTH_SHORT).show();
                } else if (binding.phoneNumber.getText().toString().isEmpty()) {
                    Toast.makeText(Signup.this, "Please Enter Number", Toast.LENGTH_SHORT).show();
                } else if (binding.phoneNumber.getText().toString().length() < 10) {
                    Toast.makeText(Signup.this, "Number Must be Minimun 10 Numbers", Toast.LENGTH_SHORT).show();
                } else if (binding.password.getText().toString().isEmpty()) {
                    Toast.makeText(Signup.this, "Please Enter Password ", Toast.LENGTH_SHORT).show();
                } else if (binding.password.getText().toString().length() < 8) {
                    Toast.makeText(Signup.this, "Password Must Be Minimum 8 Characters ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Signup.this, "Profile Saved...", Toast.LENGTH_SHORT).show();

                    UserName = binding.name.getText().toString();
                    Email = binding.email.getText().toString();
                    PhoneNumber = binding.phoneNumber.getText().toString();
                    Pass = binding.password.getText().toString();

                    StoreUserDetails(UserName, Email, PhoneNumber, Pass);
                    Intent intent = new Intent(Signup.this, MainActivity.class);
                    startActivity(intent);
                   finish();
                }
            }
        });

//        return binding.getRoot();
    }

    private SharedPreferences StoreUserDetails(String UserName, String UserEmail, String UserPhoneNumber, String UserPassword) {
        SharedPreferences spf = Signup.this.getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spf.edit();
        editor.putString("UserName", UserName);
        editor.putString("UserEmail", UserEmail);
        editor.putString("UserPhoneNumber", UserPhoneNumber);
        editor.putString("UserPassword", UserPassword);
        editor.apply();
        return spf;
    }
}