package com.tokopedia.testproject.problems.news.utils;

import com.tokopedia.testproject.problems.news.model.Article;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface NewsDao {
    @Query("SELECT * FROM Article")
    List<Article> getAll();

    @Insert
    void insertAll(Article... users);

    @Delete
    void delete(Article user);

    @Query("DELETE FROM Article")
    void deleteAll();
}
