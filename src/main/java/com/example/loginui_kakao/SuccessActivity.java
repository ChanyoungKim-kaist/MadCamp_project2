package com.example.loginui_kakao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.loginui_kakao.data.LoginData;
import com.example.loginui_kakao.data.LoginResponse;
import com.example.loginui_kakao.network.RetrofitClient;
import com.example.loginui_kakao.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuccessActivity extends AppCompatActivity {

    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_success);

        Intent intent = getIntent();
        String token = intent.getStringExtra("token");

        ImageView restaurant = findViewById(R.id.restaurant);
        ImageView salon = findViewById(R.id.salon);
        ImageView health = findViewById(R.id.health);
        ImageView study = findViewById(R.id.study);
        ImageView cafe = findViewById(R.id.cafe);
        ImageView dormitory = findViewById(R.id.dormitory);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuccessActivity.this, ListActivity.class);
                intent.putExtra("category", 1);
                startActivity(intent);
            }
        });

    }
}