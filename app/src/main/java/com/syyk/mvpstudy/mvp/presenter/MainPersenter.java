package com.syyk.mvpstudy.mvp.presenter;

import android.support.annotation.NonNull;
import android.text.AndroidCharacter;

import com.syyk.mvpstudy.bean.News;
import com.syyk.mvpstudy.bean.NewsList;
import com.syyk.mvpstudy.mvp.contract.MainContract;
import com.syyk.mvpstudy.mvp.model.MainModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/3/17 0017.
 */

public class MainPersenter implements MainContract.Persenter{

    private MainContract.View view;

    private List<News> listData = new ArrayList<>();

    private Subscription subscription;

    @Inject
    MainModel mainModel;
    @Inject
    public MainPersenter(){
    }


    @Override
    public void getBeforeNewsListData(String date) {
        subscription = mainModel.getBeforeNews(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NewsList>() {
                    @Override
                    public void call(NewsList newsList) {
                        for(int i=0;i<newsList.getStories().size();i++){
                            listData.add(newsList.getStories().get(i));
                        }
                        view.onLoad(listData);

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println("-------onFailure" + throwable.getMessage());
                    }
                });
    }

    @Override
    public void getNewsListData() {
        subscription =mainModel.getLatestNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NewsList>() {
                    @Override
                    public void call(NewsList newsList) {
                        listData.clear();
                        for (int z = 0; z < newsList.getStories().size(); z++) {
                            listData.add(newsList.getStories().get(z));
                        }
                        view.refresh(listData);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println("-------onFailure" + throwable.getMessage());
                    }
                });

    }

    @Override
    public void getRefreshNewsListData() {
        subscription =mainModel.getLatestNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NewsList>() {
                    @Override
                    public void call(NewsList newsList) {
                        listData.clear();
                        for (int z = 0; z < newsList.getStories().size(); z++) {
                            listData.add(newsList.getStories().get(z));
                        }
                        view.refresh(listData);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println("-------onFailure" + throwable.getMessage());
                    }
                });

    }

    @Override
    public void attachView(@NonNull MainContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        if(subscription != null && !subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
        view = null;
    }

}
