package com.syyk.mvpstudy.di.module;

import com.syyk.mvpstudy.mvp.contract.MainContract;

import dagger.Module;

/**
 * Created by Administrator on 2018/3/17 0017.
 */
@Module
public class MainModule {
    private MainContract.View view;

    public MainModule(MainContract.View view){
        this.view = view;
    }

}
