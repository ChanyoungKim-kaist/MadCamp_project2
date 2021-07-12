package com.example.loginui_kakao.data;

import com.google.gson.annotations.SerializedName;

public class LikeResponse {
    @SerializedName("ok")
    private boolean ok;

    @SerializedName("error")
    private String error;

    @SerializedName("likes")
    private int likes;

    public boolean getOk() {
        return ok;
    }

    public String getError() {
        return error;
    }

    public int getLikes() {
        return likes;
    }
}
