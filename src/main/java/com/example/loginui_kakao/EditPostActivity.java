package com.example.loginui_kakao;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
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

public class EditPostActivity extends AppCompatActivity {

    ServiceApi service = RetrofitClient.getClient().create(ServiceApi .class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);

        String title = getIntent().getExtras().getString("title");
        String content = getIntent().getExtras().getString("contents");
        int pos = getIntent().getExtras().getInt("postId");
        String token = getIntent().getExtras().getString("token");
        int type = getIntent().getExtras().getInt("category");

        EditText editTitle = findViewById(R.id.make_title6);
        EditText editContent = findViewById(R.id.make_contents6);

        editTitle.setText(title);
        editContent.setText(content);

        ImageView check = findViewById(R.id.check6);
        ImageView back = findViewById(R.id.back_pressed6);

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userTitle = editTitle.getText().toString();
                String userContent = editContent.getText().toString();
                if (userTitle.isEmpty()){
                    Toast.makeText(EditPostActivity.this, "제목을 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                else if (userContent.isEmpty())
                    Toast.makeText(EditPostActivity.this, "내용을 입력하세요.", Toast.LENGTH_SHORT).show();
                else
                    service.editPost(pos, new PostData(userTitle, userContent, type), token).enqueue(new Callback<PostResponse>() {
                        @Override
                        public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                            PostResponse result = response.body();
                            //Log.e("result", result.toString());
                            if (result.getOk()) {
                                Toast.makeText(EditPostActivity.this, "게시글 수정 성공!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else
                                Toast.makeText(EditPostActivity.this, result.getError(), Toast.LENGTH_SHORT).show();
                            //showProgress(false);
                        }

                        @Override
                        public void onFailure(Call<PostResponse> call, Throwable t) {
                            Toast.makeText(EditPostActivity.this, "통신 에러 발생", Toast.LENGTH_SHORT).show();
                            Log.e("통신 에러 발생", t.getMessage());
                            t.printStackTrace();
                            //showProgress(false);
                        }
                    });
            }
        });

    }
}