package com.tokopedia.testproject.problems.news.network;

import com.tokopedia.testproject.BuildConfig;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsDataSource {
    private static NewsService service;

    public static NewsService getService() {
        if (service == null) {
            final Retrofit.Builder builder = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());

            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                okHttpClientBuilder.addInterceptor(loggingInterceptor);
            }
            OkHttpClient okHttpClient = okHttpClientBuilder
                    .readTimeout(25, TimeUnit.SECONDS)
                    .addInterceptor(new Interceptor() {
                        @NotNull
                        @Override
                        public Response intercept(@NotNull Chain chain) throws IOException {
                            return chain.proceed(chain.request()
                                    .newBuilder()
                                    .header("Authorization", BuildConfig.NEWS_API_KEY)
                                    .build());
                        }
                    })
                    .build();
            Retrofit retrofit = builder.baseUrl(NewsURL.BASE_URL).client(okHttpClient).build();
            service = retrofit.create(NewsService.class);
        }
        return service;
    }

}
