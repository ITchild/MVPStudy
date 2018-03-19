package com.syyk.mvpstudy.mvp.presenter;

import android.support.annotation.NonNull;

import com.syyk.mvpstudy.mvp.contract.MainContract;
import com.syyk.mvpstudy.mvp.model.MainModel;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/3/17 0017.
 */

public class MainPersenter implements MainContract.Persenter{

    private MainContract.View view;

    @Inject
    MainModel mainModel;
    @Inject
    public MainPersenter(){
    }


    @Override
    public void getBeforeNewsListData(String date) {

    }

    @Override
    public void getNewsListData() {

    }

    @Override
    public void getRefreshNewsListData() {

    }

    @Override
    public void attachView(@NonNull MainContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {

    }

}
