package com.example.loginui_kakao;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginui_kakao.Adapter.CommentAdapter;
import com.example.loginui_kakao.data.CommentItem;
import com.example.loginui_kakao.data.CommentResponse;
import com.example.loginui_kakao.data.LikeResponse;
import com.example.loginui_kakao.data.PostResponse;
import com.example.loginui_kakao.data.postId;
import com.example.loginui_kakao.network.RetrofitClient;
import com.example.loginui_kakao.network.ServiceApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewActivity extends AppCompatActivity implements CommentAdapter.OnCommentListener{

    private ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    private RecyclerView recyclerView;
    private CommentAdapter adapter;
    private CommentResponse comment;
    private PostResponse ok;
    private List<CommentItem> commentlist;
    private RecyclerView.LayoutManager layoutManager;
    SwipeRefreshLayout swipeRefreshLayout;
    private String token;

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
        token = getIntent().getExtras().getString("token");
        int type = getIntent().getExtras().getInt("category");
        int authorid = getIntent().getExtras().getInt("username");

        TextView title_detail = findViewById(R.id.title_detail);
        TextView contents_detail = findViewById(R.id.contents_detail);
        TextView likes_detail = findViewById(R.id.likes4);
        ImageView thumb = findViewById(R.id.thumb4);
        ImageView back = findViewById(R.id.back_pressed4);
        TextView username_detail = findViewById(R.id.username_detail);
        ImageView comment_icon = findViewById(R.id.comment);
        TextView comment_txt = findViewById(R.id.comment_text);
        swipeRefreshLayout = findViewById(R.id.swipe_comment);
        ImageView edit = findViewById(R.id.edit4);

        title_detail.setText(title);
        contents_detail.setText(content);
        likes_detail.setText(String.valueOf(likes));
        username_detail.setText(String.valueOf(authorid));

        service = RetrofitClient.getClient().create(ServiceApi.class);

        Call<CommentResponse> call = service.getComment(pos);
        call.enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                //Toast.makeText(ListActivity.this, "클", Toast.LENGTH_SHORT).show();
                comment = response.body();
                if (comment.getOk()) {
                    commentlist = comment.getComments();
                    comment_txt.setText(String.valueOf(commentlist.size()));
                    adapter = new CommentAdapter(commentlist, NewActivity.this, NewActivity.this);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {
                Log.e("로그인 에러 발생", t.getMessage());
                Toast.makeText(NewActivity.this, "실패", Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        thumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.postLike(new postId(pos), token).enqueue(new Callback<LikeResponse>() {
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

        comment_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewActivity.this, NewCommentActivity.class);
                intent.putExtra("postId", pos);
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });

        edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(NewActivity.this);
                builder.setTitle("게시글을 수정 및 삭제하시겠습니까?").setMessage("수정을 원하시면 수정 버튼을 눌러주세요");

                builder.setPositiveButton("수정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Call<PostResponse> call2 = service.checkUser(pos, token);
                        call2.enqueue(new Callback<PostResponse>() {
                            @Override
                            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                                ok = response.body();
                                if (ok.getOk()) {
                                    Intent intent = new Intent(NewActivity.this, EditPostActivity.class);
                                    intent.putExtra("category", type);
                                    intent.putExtra("token", token);
                                    intent.putExtra("postId", pos);
                                    intent.putExtra("title", title);
                                    intent.putExtra("contents", content);
                                    startActivity(intent);
                                }else
                                    Toast.makeText(NewActivity.this, "수정할 수 있는 권한이 없습니다.", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<PostResponse> call, Throwable t) {

                            }
                        });
                    }
                });

                builder.setNegativeButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Call<PostResponse> call = service.deletePost(pos, token);
                        call.enqueue(new Callback<PostResponse>() {
                            @Override
                            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                                ok = response.body();
                                if (ok.getOk()) {
                                    Toast.makeText(NewActivity.this, "게시글이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                else
                                    Toast.makeText(NewActivity.this, "삭제할 수 있는 권한이 없습니다.", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<PostResponse> call, Throwable t) {
                                Log.e("삭제 에러 발생", t.getMessage());
                                Toast.makeText(NewActivity.this, "실패", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(NewActivity.this, "취소를 선택하셨습니다", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Call<CommentResponse> call = service.getComment(pos);
                call.enqueue(new Callback<CommentResponse>() {
                    @Override
                    public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                        //Toast.makeText(ListActivity.this, "클", Toast.LENGTH_SHORT).show();
                        comment = response.body();
                        if (comment.getOk()) {
                            commentlist = comment.getComments();
                            comment_txt.setText(String.valueOf(commentlist.size()));
                            adapter = new CommentAdapter(commentlist, NewActivity.this, NewActivity.this);
                            recyclerView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<CommentResponse> call, Throwable t) {
                        Log.e("로그인 에러 발생", t.getMessage());
                        Toast.makeText(NewActivity.this, "실패", Toast.LENGTH_SHORT).show();
                    }
                });
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    @Override
    public void onCommentClick(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(NewActivity.this);
        builder.setTitle("댓글을 삭제하시겠습니까?").setMessage("삭제를 원하시면 확인 버튼을 눌러주세요");

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Call<PostResponse> call = service.deleteComment(commentlist.get(position).getCommentId(), token);
                call.enqueue(new Callback<PostResponse>() {
                    @Override
                    public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                        ok = response.body();
                        if (ok.getOk())
                            Toast.makeText(NewActivity.this, "댓글이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(NewActivity.this, "삭제할 수 있는 권한이 없습니다.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<PostResponse> call, Throwable t) {
                        Log.e("삭제 에러 발생", t.getMessage());
                        Toast.makeText(NewActivity.this, "실패", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(NewActivity.this, "취소를 선택하셨습니다", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}