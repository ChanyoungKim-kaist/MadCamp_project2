package com.example.loginui_kakao.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserInfoResponse {
    @SerializedName("ok")
    private boolean ok;

    @SerializedName("error")
    private String error;

    @SerializedName("username")
    private String username;

    @SerializedName("postNum")
    private int postNum;

    @SerializedName("commentNum")
    private int commentNum;

    @SerializedName("likeNum")
    private int likeNum;

    public boolean isOk() {
        return ok;
    }

    public String getError() {
        return error;
    }

    public String getUsername() {
        return username;
    }

    public int getPostNum() {
        return postNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public int getLikeNum() {
        return likeNum;
    }
}
