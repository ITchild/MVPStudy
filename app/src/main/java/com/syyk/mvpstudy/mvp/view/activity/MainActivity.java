package com.syyk.mvpstudy.mvp.view.activity;


import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.syyk.mvpstudy.BaseActivity;
import com.syyk.mvpstudy.R;
import com.syyk.mvpstudy.bean.News;
import com.syyk.mvpstudy.di.component.DaggerMainPersenterComponent;
import com.syyk.mvpstudy.di.module.MainModule;
import com.syyk.mvpstudy.mvp.contract.MainContract;
import com.syyk.mvpstudy.mvp.presenter.MainPersenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainContract.View{

    @Bind(R.id.main_disList_xrv)
    XRecyclerView main_disList_xrv;

    @Inject
    MainPersenter mainPersenter;

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);

        main_disList_xrv.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void initData() {
        mainPersenter.attachView(this);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupComponent() {
        DaggerMainPersenterComponent.builder().mainModule(new MainModule(this)).build().inject(this);
    }


    @Override
    public void refresh(List<News> data) {

    }

    @Override
    public void onLoad(List<News> data) {

    }
}
