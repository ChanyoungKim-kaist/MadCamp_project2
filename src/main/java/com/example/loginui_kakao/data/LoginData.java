package com.example.loginui_kakao.data;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("email")
    String email;

    @SerializedName("password")
    String password;

    public LoginData(String userEmail, String userPwd) {
        this.email = userEmail;
        this.password = userPwd;
    }
}