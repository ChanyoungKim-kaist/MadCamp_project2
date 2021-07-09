package com.example.loginui_kakao.data;

import com.google.gson.annotations.SerializedName;

public class KakaoResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
