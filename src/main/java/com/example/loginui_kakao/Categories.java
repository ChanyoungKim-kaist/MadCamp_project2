package com.example.loginui_kakao;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Categories {

    @SerializedName("ok")
    private boolean ok;

    @SerializedName("title")
    private ArrayList<String> title;

    @SerializedName("subtitle")
    private ArrayList<String> subtitle;

    @SerializedName("authorId")
    private ArrayList<String> id;

    public boolean getOk() {
        return ok;
    }

    public ArrayList<String> getTitle() {
        return title;
    }

    public ArrayList<String> getSubtitle() {
        return subtitle;
    }

    public ArrayList<String> getId() {
        return id;
    }
}
