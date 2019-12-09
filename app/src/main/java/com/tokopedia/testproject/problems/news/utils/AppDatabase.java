package com.tokopedia.testproject.problems.news.utils;

import com.tokopedia.testproject.problems.news.model.Article;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Article.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NewsDao newsDao();
}
