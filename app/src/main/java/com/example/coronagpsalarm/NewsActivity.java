package com.example.coronagpsalarm;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coronagpsalarm.newsLibrary.NewsAdapter;
import com.example.coronagpsalarm.newsLibrary.Utils;
import com.example.coronagpsalarm.newsLibrary.newsApi.ApiClientJava;
import com.example.coronagpsalarm.newsLibrary.newsApi.ApiInterface;
import com.example.coronagpsalarm.newsLibrary.newsModels.Article;
import com.example.coronagpsalarm.newsLibrary.newsModels.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends Fragment {
    public static final String API_KEY = "349ce2b5bbef41c58d9b9bb38df0109a";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Article> articles = new ArrayList<>();
    private NewsAdapter adapter;
    private String TAG = MainActivity.class.getSimpleName();
    private View view;
    private Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    // 프레그먼트와 레이아웃을 연결시켜주는 부분
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_news, container, false);
        mContext = view.getContext();

        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        LoadJson();
        return view;
    }

    public void LoadJson() {
        ApiInterface apiInterface = ApiClientJava.getApiClient().create(ApiInterface.class);

        String keyword = "+코로나"; // 검색 키워드
        String language = Utils.getLanguage(); // 검색 언어
        Call<News> call;
        call = apiInterface.getNews(keyword, "relevancy", API_KEY);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if(response.isSuccessful() && response.body().getArticle() != null) {
                    if(!articles.isEmpty())
                        articles.clear();

                    articles = response.body().getArticle();
                    adapter = new NewsAdapter(articles, mContext);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
                else
                    Toast.makeText(mContext, "No Result!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });
    }
}
