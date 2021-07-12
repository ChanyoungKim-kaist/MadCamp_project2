package com.example.loginui_kakao.data;

import com.google.gson.annotations.SerializedName;

public class PostData {
    @SerializedName("title")
    String title;

    @SerializedName("contents")
    String contents;

    @SerializedName("categoryId")
    int categoryId;

    public PostData(String title, String contents, int type) {
        this.title = title;
        this.contents = contents;
        this.categoryId = type;
    }
}
