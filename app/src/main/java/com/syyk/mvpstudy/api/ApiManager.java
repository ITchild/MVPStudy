package com.syyk.mvpstudy.api;

import com.syyk.mvpstudy.bean.NewsDetail;
import com.syyk.mvpstudy.bean.NewsList;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by lidong on 2016/3/2.
 */
public class ApiManager {



    private static final Retrofit sRetrofit = new Retrofit.Builder()
            .baseUrl("http://news-at.zhihu.com/api/4/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 使用RxJava作为回调适配器
            .build();

    private static final ApiManagerService apiManager = sRetrofit.create(ApiManagerService.class);

    public static Observable<NewsList> getLatestNews(){
        return apiManager.getLatestNews();
    }

    public static Observable<NewsList> getBeforeNews(String date){
        return apiManager.getBeforeNews(date);
    }

    public static Observable<NewsDetail> getNewsDetail(int id) {
        return apiManager.getNewsDetail(id);
    }



}