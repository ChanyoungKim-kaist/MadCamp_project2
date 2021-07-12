package com.example.loginui_kakao;

import com.example.loginui_kakao.data.CommentItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostItem {
    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("contents")
    private String subtitle;

    @SerializedName("authorId")
    private int authorId;

    @SerializedName("likes")
    private int likes;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getLikes() { return likes; }

}
