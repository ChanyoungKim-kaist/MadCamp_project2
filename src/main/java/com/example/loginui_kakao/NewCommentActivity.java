package com.example.loginui_kakao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.loginui_kakao.data.CommentResponse;
import com.example.loginui_kakao.data.NewCommentData;
import com.example.loginui_kakao.data.PostData;
import com.example.loginui_kakao.data.PostResponse;
import com.example.loginui_kakao.network.RetrofitClient;
import com.example.loginui_kakao.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewCommentActivity extends AppCompatActivity {

    private ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_comment);

        int postId = getIntent().getExtras().getInt("postId");
        String token = getIntent().getExtras().getString("token");
        EditText new_comment = findViewById(R.id.new_comment);
        ImageView check = findViewById(R.id.check5);
        ImageView back = findViewById(R.id.back_pressed5);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String payload = new_comment.getText().toString();
                Call<PostResponse> call = service.postComment(new NewCommentData(postId, payload), token);
                call.enqueue(new Callback<PostResponse>() {
                    @Override
                    public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                        //Toast.makeText(ListActivity.this, "클", Toast.LENGTH_SHORT).show();
                        PostResponse result = response.body();
                        if (result.getOk()) {
                            finish();
                        }
                    }
                    @Override
                    public void onFailure(Call<PostResponse> call, Throwable t) {
                        Log.e("로그인 에러 발생", t.getMessage());
                        Toast.makeText(NewCommentActivity.this, "실패", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}