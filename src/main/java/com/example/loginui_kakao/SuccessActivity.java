package com.example.loginui_kakao;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginui_kakao.data.LoginData;
import com.example.loginui_kakao.data.LoginResponse;
import com.example.loginui_kakao.network.RetrofitClient;
import com.example.loginui_kakao.network.ServiceApi;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuccessActivity extends AppCompatActivity {

    private ServiceApi service;
    private List<String> items = Arrays.asList("restaurant", "salon", "study");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.category);

        Intent intent = getIntent();
        String token = intent.getStringExtra("token");

        TextView restaurant = findViewById(R.id.restaurant);
        TextView salon = findViewById(R.id.salon);
        TextView health = findViewById(R.id.health);
        TextView study = findViewById(R.id.study);
        TextView cafe = findViewById(R.id.cafe);
        TextView dormitory = findViewById(R.id.dormitory);
        SearchView search = findViewById(R.id.search_category);
        ImageView logout = findViewById(R.id.logout);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SuccessActivity.this); // ?? this
                builder.setTitle("로그아웃 하시겠습니까?").setMessage("로그아웃을 원하시면 확인 버튼을 눌러주세요");

                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SuccessActivity.this, "로그아웃 성공!", Toast.LENGTH_SHORT).show();
                        UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                            @Override
                            public void onCompleteLogout() { //로그아웃 성공시
                                finish(); // 현재 액티비티 종
                            }
                        });
                    }
                });

                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SuccessActivity.this, "취소를 선택하셨습니다", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuccessActivity.this, ListActivity.class);
                intent.putExtra("category", 5);
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });

        salon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuccessActivity.this, ListActivity.class);
                intent.putExtra("category", 3);
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });

        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuccessActivity.this, ListActivity.class);
                intent.putExtra("category", 1);
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });

        study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuccessActivity.this, ListActivity.class);
                intent.putExtra("category", 6);
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });

        cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuccessActivity.this, ListActivity.class);
                intent.putExtra("category", 8);
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });

        dormitory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuccessActivity.this, ListActivity.class);
                intent.putExtra("category", 7);
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });

    }
}