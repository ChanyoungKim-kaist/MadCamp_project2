package com.example.loginui_kakao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.loginui_kakao.network.RetrofitClient;
import com.example.loginui_kakao.network.ServiceApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Categories categoriesList;
    private RecyclerAdapter adapter;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_category);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        if (getIntent().getExtras() != null) {
            int type = getIntent().getExtras().getInt("category");
            fetchInformation(type);
        }
    }

    public void fetchInformation(int type){
        service = RetrofitClient.getClient().create(ServiceApi.class);

        Call<Categories> call = service.category(type);
        call.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                Toast.makeText(ListActivity.this, "클", Toast.LENGTH_SHORT).show();
                categoriesList = response.body();
                adapter = new RecyclerAdapter(categoriesList, ListActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                Log.e("로그인 에러 발생", t.getMessage());
                Toast.makeText(ListActivity.this, "실패", Toast.LENGTH_SHORT).show();
            }
        });

    }

}