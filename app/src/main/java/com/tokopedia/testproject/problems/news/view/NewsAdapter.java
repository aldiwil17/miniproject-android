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
import com.tokopedia.testproject.problems.news.utils.DateFormater;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    public static int TYPE_HEADER = 0;
    public static int TYPE_ARTICLE = 1;

    private List<Article> articleList = new ArrayList<>();
    private List<Article> articleListOnly = new ArrayList<>();

    NewsAdapter(List<Article> articleList) {
        setArticleList(articleList);
    }

    void setArticleList(List<Article> articleList) {
        if (articleList == null) { return; }
        this.articleListOnly = articleList;
        sortArticle();
    }

    public void addArticle(List<Article> articles){
        articleListOnly.addAll(articles);
        sortArticle();
        notifyDataSetChanged();
    }

    public void clearArticle(){
        articleListOnly.clear();
        articleList.clear();
        notifyDataSetChanged();
    }

    private void sortArticle() {
        Collections.sort(articleListOnly, new Comparator<Article>() {
            @Override
            public int compare(Article article, Article t1) {
                Date d1 = DateFormater.parseToDate(article.getPublishedAt());
                Date d2 = DateFormater.parseToDate(t1.getPublishedAt());
                if (d1.after(d2)) {
                    return -1;
                }
                if (d1.before(d2)) {
                    return 1;
                }
                return 0;
            }
        });

        grouping();
    }

    private void grouping() {
        if (articleListOnly.isEmpty()) { return; }
        List<Article> currentArticle = new ArrayList<>();
        currentArticle.add(getHeader(articleListOnly.get(0).getPublishedAt()));
        currentArticle.add(articleListOnly.get(0));
        Date headerDate = DateFormater.simpleDate(articleListOnly.get(0).getPublishedAt());
        for (int i = 1; i < articleListOnly.size(); i++) {
            Date date = DateFormater.simpleDate(articleListOnly.get(i).getPublishedAt());
            if (headerDate.equals(date)) {
                currentArticle.add(articleListOnly.get(i));
            } else {
                headerDate = DateFormater.simpleDate(articleListOnly.get(i).getPublishedAt());
                currentArticle.add(getHeader(articleListOnly.get(i).getPublishedAt()));
                currentArticle.add(articleListOnly.get(i));
            }
        }

        articleList = currentArticle;
    }

    private Article getHeader(String publishedAt) {
        return new Article("", null, null, null, publishedAt, null, 0);
    }

    @Override
    public int getItemViewType(int position) {
        return articleList.get(position).getType();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_ARTICLE) {
            return new NewsViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_news, viewGroup, false));
        } else {
            return new NewsViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_header, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int i) {
        if (articleList.get(i).getType() == TYPE_ARTICLE) {
            newsViewHolder.bindArticle(articleList.get(i));
        } else {
            newsViewHolder.bindHeader(articleList.get(i));
        }
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

        void bindHeader(Article article) {
            tvTitle.setText(DateFormater.format(DateFormater.formatDate, article.getPublishedAt()));
        }

        void bindArticle(Article article) {
            Glide.with(itemView).load(article.getUrlToImage()).into(imageView);
            tvTitle.setText(article.getTitle());
            tvDescription.setText(article.getDescription());
            tvDate.setText(DateFormater.format(DateFormater.formatDate, article.getPublishedAt()));
        }
    }
}
