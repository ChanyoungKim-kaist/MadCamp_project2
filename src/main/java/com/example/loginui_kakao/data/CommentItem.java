package com.example.loginui_kakao.data;

import com.google.gson.annotations.SerializedName;

public class CommentItem {

    @SerializedName("payload")
    private String payload;

    @SerializedName("userId")
    private String userId;

    @SerializedName("id")
    private int commentId;

    public String getAuthorId() {
        return userId;
    }

    public String getContents() {
        return payload;
    }

    public int getCommentId() { return commentId; }

}
