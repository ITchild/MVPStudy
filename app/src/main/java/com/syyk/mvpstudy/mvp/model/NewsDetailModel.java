package com.syyk.mvpstudy.mvp.model;

import com.syyk.mvpstudy.api.ApiManager;
import com.syyk.mvpstudy.bean.NewsDetail;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by fei on 2018/3/20.
 */

public class NewsDetailModel {

    @Inject
    public NewsDetailModel(){}

    public Observable<NewsDetail> getNewsDetail(int id){
        return ApiManager.getNewsDetail(id);
    }
}
