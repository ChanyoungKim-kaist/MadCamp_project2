package com.example.loginui_kakao.data;

import com.google.gson.annotations.SerializedName;

public class PostResponse {
    @SerializedName("ok")
    private boolean ok;

    @SerializedName("error")
    private String error;

    public boolean getOk() {
        return ok;
    }

    public String getError() {
        return error;
    }
}
