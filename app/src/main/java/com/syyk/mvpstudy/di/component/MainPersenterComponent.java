package com.syyk.mvpstudy.di.component;

import com.syyk.mvpstudy.di.module.MainModule;
import com.syyk.mvpstudy.mvp.presenter.MainPersenter;
import com.syyk.mvpstudy.mvp.view.activity.MainActivity;

import dagger.Component;

/**
 * Created by Administrator on 2018/3/17 0017.
 * 用来给Activity注入Persenter对象
 *
 */
@Component(modules = MainModule.class)
public interface MainPersenterComponent {

    void inject(MainActivity mainActivity);
}
