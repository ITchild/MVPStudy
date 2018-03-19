package com.syyk.mvpstudy.mvp.model;

import com.syyk.mvpstudy.api.ApiManager;
import com.syyk.mvpstudy.bean.NewsList;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Administrator on 2018/3/17 0017.
 */

public class MainModel {


    @Inject
    public MainModel(){}


    public Observable<NewsList> getLatestNews(){
        return ApiManager.getLatestNews();
    }

    public Observable<NewsList> getBeforeNews(String date){
        return ApiManager.getBeforeNews(date);
    }



}
