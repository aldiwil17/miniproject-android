package com.tokopedia.testproject.problems.news.network;

import com.tokopedia.testproject.problems.news.model.NewsResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {
    @GET("everything")
    Observable<NewsResult> getEverything(@Query("q") String query);
}
