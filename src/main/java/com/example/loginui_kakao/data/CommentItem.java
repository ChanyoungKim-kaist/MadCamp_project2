package com.example.loginui_kakao.data;

import com.google.gson.annotations.SerializedName;

public class CommentItem {

    @SerializedName("payload")
    private String subtitle;

    @SerializedName("userId")
    private String title;

    public String getAuthorId() {
        return title;
    }

    public String getContents() {
        return subtitle;
    }

}
