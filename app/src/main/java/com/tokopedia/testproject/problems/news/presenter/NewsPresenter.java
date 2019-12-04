package com.tokopedia.testproject.problems.news.presenter;

import com.tokopedia.testproject.problems.news.model.Article;
import com.tokopedia.testproject.problems.news.model.NewsResult;
import com.tokopedia.testproject.problems.news.network.NewsDataSource;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hendry on 27/01/19.
 */
public class NewsPresenter {

    private CompositeDisposable composite = new CompositeDisposable();

    private View view;

    public interface View {
        void onSuccessGetNews(List<Article> articleList);

        void onErrorGetNews(Throwable throwable);
    }

    public NewsPresenter(NewsPresenter.View view) {
        this.view = view;
    }

    public void getEverything(String keyword) {
        NewsDataSource.getService().getEverything(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        composite.add(d);
                    }

                    @Override
                    public void onNext(NewsResult newsResult) {
                        view.onSuccessGetNews(newsResult.getArticles());
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onErrorGetNews(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void unsubscribe() {
        composite.dispose();
    }
}
