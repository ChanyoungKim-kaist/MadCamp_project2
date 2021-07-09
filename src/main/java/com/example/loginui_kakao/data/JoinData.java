package com.example.loginui_kakao.data;

import com.google.gson.annotations.SerializedName;

public class JoinData {
    @SerializedName("username")
    private String username;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public JoinData(String userName, String userEmail, String userPwd) {
        this.username = userName;
        this.email = userEmail;
        this.password = userPwd;
    }
}

