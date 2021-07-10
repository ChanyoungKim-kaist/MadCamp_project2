package com.example.loginui_kakao.network;

import com.example.loginui_kakao.Categories;
import com.example.loginui_kakao.data.JoinData;
import com.example.loginui_kakao.data.JoinResponse;
import com.example.loginui_kakao.data.KakaoData;
import com.example.loginui_kakao.data.KakaoResponse;
import com.example.loginui_kakao.data.LoginData;
import com.example.loginui_kakao.data.LoginResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceApi {
    @POST("/users/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/users/join")
    Call<JoinResponse> userJoin(@Body JoinData data);

    @POST("/users/kakao")
    Call<KakaoResponse> userKakao(@Body KakaoData data);

    @GET("/posts/all/{category}")
    Call<Categories> category(@Path("category") int category);
}
