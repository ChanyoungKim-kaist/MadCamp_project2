package com.example.loginui_kakao.data;

import com.google.gson.annotations.SerializedName;

public class KakaoResponse {
    @SerializedName("ok")
    private boolean ok;

    @SerializedName("error")
    private String error;

    @SerializedName("token")
    private String token;

    public boolean getOk() {
        return ok;
    }

    public String getError() {
        return error;
    }

    public String getToken() {
        return token;
    }
}
