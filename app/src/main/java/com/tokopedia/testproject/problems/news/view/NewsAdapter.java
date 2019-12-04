package com.tokopedia.testproject.problems.news.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tokopedia.testproject.R;
import com.tokopedia.testproject.problems.news.model.Article;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<Article> articleList;

    NewsAdapter(List<Article> articleList) {
        setArticleList(articleList);
    }

    void setArticleList(List<Article> articleList) {
        if (articleList == null) {
            this.articleList = new ArrayList<>();
        } else {
            this.articleList = articleList;
        }
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new NewsViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_news, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int i) {
        newsViewHolder.bind(articleList.get(i));
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvTitle;
        TextView tvDescription;
        TextView tvDate;

        NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvDate = itemView.findViewById(R.id.tvDate);
        }

        void bind(Article article) {
            Glide.with(itemView).load(article.getUrlToImage()).into(imageView);
            tvTitle.setText(article.getTitle());
            tvDescription.setText(article.getDescription());
            tvDate.setText(article.getPublishedAt());
        }
    }
}
