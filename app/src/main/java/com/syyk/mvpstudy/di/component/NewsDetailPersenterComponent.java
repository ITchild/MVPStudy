package com.syyk.mvpstudy.di.component;

import com.syyk.mvpstudy.di.module.NewsDetailModule;
import com.syyk.mvpstudy.mvp.view.activity.NewsDetailActivity;

import dagger.Component;

/**
 * Created by fei on 2018/3/20.
 */
@Component(modules = NewsDetailModule.class)
public interface NewsDetailPersenterComponent {

    void inject(NewsDetailActivity newsDetailActivity);
}
