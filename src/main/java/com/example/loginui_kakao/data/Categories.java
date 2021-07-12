package com.example.loginui_kakao.data;

import com.example.loginui_kakao.PostItem;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Categories {

    @SerializedName("ok")
    private boolean ok;

    @SerializedName("posts")
    private List<PostItem> posts;

    public boolean getOk() {
        return ok;
    }

    public List<PostItem> getPosts() {
        return posts;
    }

}


