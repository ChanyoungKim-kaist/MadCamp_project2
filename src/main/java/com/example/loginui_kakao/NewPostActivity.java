package com.example.loginui_kakao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.loginui_kakao.data.PostData;
import com.example.loginui_kakao.data.PostResponse;
import com.example.loginui_kakao.network.RetrofitClient;
import com.example.loginui_kakao.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPostActivity extends AppCompatActivity {

    private ServiceApi service;
    private EditText title;
    private EditText content;
    private ImageView check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        ImageView back = findViewById(R.id.back_pressed3);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        if (getIntent().getExtras() != null ) {
            int type = getIntent().getExtras().getInt("category");
            String token = getIntent().getExtras().getString("token");

            title = (EditText) findViewById(R.id.make_title);
            content = (EditText) findViewById(R.id.make_contents);
            check = (ImageView) findViewById(R.id.check);

            back.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    finish();
                }
            });

            check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String userTitle = title.getText().toString();
                    String userContent = content.getText().toString();
                    if (userTitle.isEmpty()){
                        Toast.makeText(NewPostActivity.this, "제목을 입력하세요.", Toast.LENGTH_SHORT).show();
                    }
                    else if (userContent.isEmpty())
                        Toast.makeText(NewPostActivity.this, "내용을 입력하세요.", Toast.LENGTH_SHORT).show();
                    else
                        service.userPost(new PostData(userTitle, userContent, type), token).enqueue(new Callback<PostResponse>() {
                            @Override
                            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                                PostResponse result = response.body();
                                //Log.e("result", result.toString());
                                if (result.getOk()) {
                                    Toast.makeText(NewPostActivity.this, "게시 성공!📝️", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                else
                                    Toast.makeText(NewPostActivity.this, result.getError(), Toast.LENGTH_SHORT).show();
                                //showProgress(false);
                            }

                            @Override
                            public void onFailure(Call<PostResponse> call, Throwable t) {
                                Toast.makeText(NewPostActivity.this, "통신 에러 발생", Toast.LENGTH_SHORT).show();
                                Log.e("통신 에러 발생", t.getMessage());
                                t.printStackTrace();
                                //showProgress(false);
                            }
                        });
                }
            });
        }
    }

}