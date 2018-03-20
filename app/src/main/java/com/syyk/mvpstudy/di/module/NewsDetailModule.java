package com.syyk.mvpstudy.di.module;

import com.syyk.mvpstudy.mvp.contract.NewsDetailContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fei on 2018/3/20.
 */
@Module
public class NewsDetailModule {
    private NewsDetailContract.View view;
    private int id;
    public NewsDetailModule(NewsDetailContract.View view,int id){
        this.view = view;
        this.id = id;
    }

    @Provides
    public int providesId(){
        return id;
    }

}
