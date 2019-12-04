package com.tokopedia.testproject.problems.news.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.tokopedia.testproject.R;
import com.tokopedia.testproject.problems.news.model.Article;
import com.tokopedia.testproject.problems.news.presenter.NewsPresenter;

import java.util.List;

public class NewsActivity extends AppCompatActivity implements com.tokopedia.testproject.problems.news.presenter.NewsPresenter.View {

    private NewsPresenter newsPresenter;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        newsPresenter = new NewsPresenter(this);
        newsAdapter = new NewsAdapter(null);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setAdapter(newsAdapter);
        newsPresenter.getEverything("android");
    }

    @Override
    public void onSuccessGetNews(List<Article> articleList) {
        newsAdapter.setArticleList(articleList);
        newsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onErrorGetNews(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        newsPresenter.unsubscribe();
    }
}
