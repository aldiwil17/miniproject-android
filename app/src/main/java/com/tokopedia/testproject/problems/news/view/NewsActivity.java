package com.tokopedia.testproject.problems.news.view;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tokopedia.testproject.R;
import com.tokopedia.testproject.problems.news.model.Article;
import com.tokopedia.testproject.problems.news.presenter.NewsPresenter;
import com.tokopedia.testproject.problems.news.utils.AppDatabase;
import com.viewpagerindicator.CirclePageIndicator;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.room.Room;
import dmax.dialog.SpotsDialog;

public class NewsActivity extends AppCompatActivity implements com.tokopedia.testproject.problems.news.presenter.NewsPresenter.View {

    private NewsPresenter newsPresenter;
    public NewsAdapter newsAdapter;
    private SpotsDialog dialog;
    private EditText etSearch;
    private ImageView ivSearch;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private LinearLayoutManager linearLayoutManager;
    private LinearLayout llNotFound, llError;
    private Button btnRetry;
    private String keyword = "android";
    private int page = 1;
    private ViewPager viewPager;
    private BannerViewPager pagerAdapter;
    private List<String> imageUrls = new ArrayList<>();
    private Handler handler = new Handler();
    private int currentPage = 0;
    private int numberPage = 0;
    private Timer swipeTimer;
    private AppDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        dialog = new SpotsDialog(NewsActivity.this);
        newsPresenter = new NewsPresenter(this);
        newsAdapter = new NewsAdapter(null);
        setupView();

        recyclerView.setAdapter(newsAdapter);

        db = Room.databaseBuilder(getApplicationContext(),
        AppDatabase.class, "database-news").build();
        checkOnline();
    }

    private void setupView(){
        recyclerView = findViewById(R.id.recyclerView);
        etSearch = findViewById(R.id.et_search);
        ivSearch = findViewById(R.id.iv_search);
        refreshLayout = findViewById(R.id.refresh);
        llNotFound = findViewById(R.id.ll_not_found);
        llError = findViewById(R.id.ll_error);
        btnRetry = findViewById(R.id.btn_retry);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etSearch.getText().toString().trim().isEmpty()){
                    keyword = etSearch.getText().toString();
                    searchNews();
                }
            }
        });

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // Your piece of code on keyboard search click
                    if(!etSearch.getText().toString().trim().isEmpty()){
                        keyword = etSearch.getText().toString();
                        searchNews();
                    }
                    return true;
                }
                return false;
            }
        });

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastPosition = linearLayoutManager.findLastVisibleItemPosition();
                if (lastPosition == newsAdapter.getItemCount() - 1) {
                    if (isConnect()){
                        page++;
                        searchNews();
                    }
                }
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private void searchNews(){
        dialog.show();
        newsPresenter.getEverything(keyword, page);
    }

    private void refresh(){
        page = 1;
        newsAdapter.clearArticle();
        searchNews();
    }

    public void setupBannerLayout() {
        pagerAdapter = new BannerViewPager(this, imageUrls);
        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);

        CirclePageIndicator indicator = findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(5 * density);
        numberPage = imageUrls.size();

        // Auto start of viewpager
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == numberPage) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 0, 5000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int pos) {
            }
        });
    }

    public void generateImageUrl(List<Article> articleList){
        imageUrls.clear();
        for(int i = 0; i < articleList.size() && i < 5; i++){
            imageUrls.add(articleList.get(i).getUrlToImage());
        }
    }

    private void saveToLocal(List<Article> articleList){
        new AgentAsyncTask(this, articleList).execute();
    }

    @Override
    public void onSuccessGetNews(List<Article> articleList) {
        llError.setVisibility(View.GONE);
        llNotFound.setVisibility(articleList.isEmpty()? View.VISIBLE: View.GONE);
        if(page == 1){
            generateImageUrl(articleList);
            setupBannerLayout();
            newsAdapter.setArticleList(articleList);
            newsAdapter.notifyDataSetChanged();
            saveToLocal(articleList);
        }else{
            newsAdapter.addArticle(articleList);
        }
        dialog.dismiss();
        refreshLayout.setRefreshing(false);
    }

    public void checkOnline(){
        if(isConnect()){
            searchNews();
        }else{
            new ShowAsyncTask(this).execute();
        }
    }

    public boolean isConnect(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //connected to a network
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void onErrorGetNews(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_LONG).show();
        llError.setVisibility(View.VISIBLE);
        dialog.dismiss();
        refreshLayout.setRefreshing(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        newsPresenter.unsubscribe();
    }

    private static class AgentAsyncTask extends AsyncTask<Void, Void, Integer> {

        //Prevent leak
        private WeakReference<Activity> weakActivity;
        List<Article> articles = new ArrayList<>();

        public AgentAsyncTask(Activity activity, List<Article> articles) {
            weakActivity = new WeakReference<>(activity);
            this.articles = articles;
        }

        @Override
        protected Integer doInBackground(Void... params) {
            AppDatabase db = Room.databaseBuilder(weakActivity.get(),
            AppDatabase.class, "database-news").fallbackToDestructiveMigration().build();
            db.newsDao().deleteAll();
            for(int i = 0; i < articles.size(); i++){
                db.newsDao().insertAll(articles.get(i));
            }
            return 1;
        }

        @Override
        protected void onPostExecute(Integer agentsCount) {
            Activity activity = weakActivity.get();
            if(activity == null) {
                return;
            }
        }
    }

    private static class ShowAsyncTask extends AsyncTask<Void, Void, List<Article>> {

        //Prevent leak
        private WeakReference<NewsActivity> weakActivity;

        public ShowAsyncTask(NewsActivity activity) {
            weakActivity = new WeakReference<>(activity);
        }

        @Override
        protected List<Article> doInBackground(Void... params) {
            AppDatabase db = Room.databaseBuilder(weakActivity.get(),
                    AppDatabase.class, "database-news").fallbackToDestructiveMigration().build();
            return db.newsDao().getAll();
        }

        @Override
        protected void onPostExecute(List<Article> articles) {
            NewsActivity activity = weakActivity.get();
            if(activity == null) {
                return;
            }
            activity.newsAdapter.setArticleList(articles);
            activity.newsAdapter.notifyDataSetChanged();
            activity.generateImageUrl(articles);
            activity.setupBannerLayout();
        }
    }
}
