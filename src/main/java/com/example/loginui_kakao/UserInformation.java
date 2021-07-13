package com.example.loginui_kakao;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.loginui_kakao.data.UserInfoResponse;
import com.example.loginui_kakao.network.RetrofitClient;
import com.example.loginui_kakao.network.ServiceApi;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInformation extends AppCompatActivity {

    private ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        String token = getIntent().getExtras().getString("token");

        TextView like = findViewById(R.id.like_number);
        TextView post = findViewById(R.id.post_number);
        TextView comment = findViewById(R.id.comment_number);
        TextView name = findViewById(R.id.username);
        ImageView back = findViewById(R.id.back_pressed7);
        CircleImageView image = findViewById(R.id.profile);

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });

        Call<UserInfoResponse> call = service.getUserInfo(token);
        call.enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                UserInfoResponse res = response.body();
                if (res.isOk()) {
                    like.setText(String.valueOf(res.getLikeNum()));
                    post.setText(String.valueOf(res.getPostNum()));
                    comment.setText(String.valueOf(res.getCommentNum()));
                    name.setText(res.getUsername());
                }
            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {

            }
        });

//        image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setPermission();
//                startActivityForResult(Intent.createChooser(new Intent().setAction(Intent.ACTION_GET_CONTENT).
//                        setType("image/*"), "Selecton one image"), 1);
//
//            }
//        });

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 1 && resultCode == RESULT_OK) {
//            Uri imageUri =
//        }
//
//    }
}