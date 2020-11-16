package com.example.coronagpsalarm.newsLibrary.newsApi;

import com.example.coronagpsalarm.newsLibrary.newsModels.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("everything")
    Call<News> getNews(
            @Query("qInTitle") String keyword,
            @Query("sortBy") String sortBy,
            @Query("apiKey") String apiKey
    );
}