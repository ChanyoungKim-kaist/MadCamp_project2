package com.example.loginui_kakao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginui_kakao.data.Categories;
import com.example.loginui_kakao.data.CommentItem;
import com.example.loginui_kakao.data.CommentResponse;
import com.example.loginui_kakao.data.LikeResponse;
import com.example.loginui_kakao.network.RetrofitClient;
import com.example.loginui_kakao.network.ServiceApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewActivity extends AppCompatActivity {

    private ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    private RecyclerView recyclerView;
    private CommentAdapter adapter;
    private CommentResponse comment;
    private List<CommentItem> commentlist;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        recyclerView = (RecyclerView) findViewById(R.id.comment_list);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        String title = getIntent().getExtras().getString("title");
        String content = getIntent().getExtras().getString("contents");
        int likes = getIntent().getExtras().getInt("likes");
        int pos = getIntent().getExtras().getInt("postId");
        String token = getIntent().getExtras().getString("token");

        TextView title_detail = findViewById(R.id.title_detail);
        TextView contents_detail = findViewById(R.id.contents_detail);
        TextView likes_detail = findViewById(R.id.likes4);

        title_detail.setText(title);
        contents_detail.setText(content);
        likes_detail.setText(String.valueOf(likes));

        service = RetrofitClient.getClient().create(ServiceApi.class);

        Call<CommentResponse> call = service.getComment(pos);
        call.enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                //Toast.makeText(ListActivity.this, "클", Toast.LENGTH_SHORT).show();
                comment = response.body();
                if (comment.getOk()) {
                    commentlist = comment.getComments();
                    adapter = new CommentAdapter(commentlist, NewActivity.this);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {
                Log.e("로그인 에러 발생", t.getMessage());
                Toast.makeText(NewActivity.this, "실패", Toast.LENGTH_SHORT).show();
            }
        });


        findViewById(R.id.thumb4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("postId", String.valueOf(pos));
                Log.e("token", token);

                service.postLike(pos, token).enqueue(new Callback<LikeResponse>() {
                    @Override
                    public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                        LikeResponse res = response.body();
                        if (res.getOk())
                            likes_detail.setText(String.valueOf(res.getLikes()));
                    }

                    @Override
                    public void onFailure(Call<LikeResponse> call, Throwable t) {
                        Toast.makeText(NewActivity.this, "에러", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        findViewById(R.id.edit4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}