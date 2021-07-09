package com.example.loginui_kakao.network;

import com.example.loginui_kakao.data.JoinData;
import com.example.loginui_kakao.data.JoinResponse;
import com.example.loginui_kakao.data.KakaoData;
import com.example.loginui_kakao.data.KakaoResponse;
import com.example.loginui_kakao.data.LoginData;
import com.example.loginui_kakao.data.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {
    @POST("/users/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/users/join")
    Call<JoinResponse> userJoin(@Body JoinData data);

    @POST("/users/kakao")
    Call<KakaoResponse> userKakao(@Body KakaoData data);
}
