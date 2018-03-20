package com.syyk.mvpstudy.mvp.presenter;

import android.support.annotation.NonNull;

import com.syyk.mvpstudy.bean.NewsDetail;
import com.syyk.mvpstudy.mvp.contract.NewsDetailContract;
import com.syyk.mvpstudy.mvp.model.NewsDetailModel;
import com.syyk.mvpstudy.utils.HtmlUtil;

import java.util.List;
import java.util.Observable;

import javax.inject.Inject;

import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by fei on 2018/3/20.
 */

public class NewsDetailPersenter implements NewsDetailContract.Persenter {

    private NewsDetailContract.View view;
    private int id;

    @Inject
    NewsDetailModel newsDetailModel;
    @Inject
    public NewsDetailPersenter(int id){
        this.id = id;
    }

    Subscription subscription;

    @Override
    public void getDetailsNews() {
        subscription = newsDetailModel.getNewsDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NewsDetail>() {
                    @Override
                    public void call(NewsDetail newsDetail) {
                        StringBuffer stringBuffer = HtmlUtil.handleHtml(newsDetail.getBody());
                        view.showData(newsDetail.getImage(), newsDetail.getTitle(), newsDetail.getImage_source(), stringBuffer);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println("-------onFailure" + throwable.getMessage());
                    }
                });
    }

    @Override
    public void attachView(@NonNull NewsDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        if(subscription != null && subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
        view = null;
    }
}
