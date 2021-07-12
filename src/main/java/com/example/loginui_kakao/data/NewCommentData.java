package com.example.loginui_kakao.data;

import com.google.gson.annotations.SerializedName;

public class NewCommentData {
    @SerializedName("postId")
    int postId;

    @SerializedName("payload")
    String payload;

    public NewCommentData(int postId, String payload) {
        this.postId = postId;
        this.payload = payload;
    }
}
