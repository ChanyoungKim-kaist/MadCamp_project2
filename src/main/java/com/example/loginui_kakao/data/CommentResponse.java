package com.example.loginui_kakao.data;

import com.example.loginui_kakao.PostItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommentResponse {

    @SerializedName("ok")
    private boolean ok;

    @SerializedName("comments")
    private List<CommentItem> comments;

    public boolean getOk() {
        return ok;
    }

    public List<CommentItem> getComments() {
        return comments;
    }

}
