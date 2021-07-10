package com.example.loginui_kakao.data;

import com.google.gson.annotations.SerializedName;

public class KakaoData {
    @SerializedName("username")
    private String username;

    @SerializedName("email")
    private String email;

    @SerializedName("kakaoId")
    private long kakaoId;

    public KakaoData(String userName, String userEmail, long userId) {
        this.username = userName;
        this.email = userEmail;
        this.kakaoId = userId;
    }
}