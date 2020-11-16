package com.example.coronagpsalarm.newsLibrary.newsModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class News {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("totalResults")
    @Expose
    private int totalResults;
    @SerializedName("articles")
    @Expose
    private List<Article> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotoalResult() {
        return totalResults;
    }

    public void setTotoalResult(int totoalResults) {
        this.totalResults = totoalResults;
    }

    public List<Article> getArticle() {
        return articles;
    }

    public void setArticle(List<Article> articles) {
        this.articles = articles;
    }
}
