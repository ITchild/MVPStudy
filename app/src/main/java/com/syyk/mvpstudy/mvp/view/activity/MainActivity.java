package com.syyk.mvpstudy.mvp.view.activity;


import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.syyk.mvpstudy.BaseActivity;
import com.syyk.mvpstudy.R;
import com.syyk.mvpstudy.bean.News;
import com.syyk.mvpstudy.di.component.DaggerMainPersenterComponent;
import com.syyk.mvpstudy.di.module.MainModule;
import com.syyk.mvpstudy.mvp.contract.MainContract;
import com.syyk.mvpstudy.mvp.presenter.MainPersenter;
import com.syyk.mvpstudy.mvp.view.adapter.MainListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainContract.View{

    @Bind(R.id.main_disList_xrv)
    XRecyclerView main_disList_xrv;

    private MainListAdapter mainListAdapter;

    private List<News> listData = new ArrayList<>();

    @Inject
    MainPersenter mainPersenter;

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        main_disList_xrv.setLayoutManager(layoutManager);

        main_disList_xrv.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);//设置刷新类型
        main_disList_xrv.setLoadingMoreProgressStyle(ProgressStyle.Pacman);//设置加载类型
        //  main_disList_xrv.setArrowImageView(R.drawable.iconfont_downgrey);//设置下拉箭头
        main_disList_xrv.setRefreshing(true);

    }

    @Override
    public void initData() {
        mainPersenter.attachView(this);
        mainListAdapter = new MainListAdapter(this, listData);
        main_disList_xrv.setAdapter(mainListAdapter);

        mainPersenter.attachView(this);
        mainPersenter.getNewsListData();
    }

    @Override
    public void initListener() {
        main_disList_xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mainPersenter.getRefreshNewsListData();
                        main_disList_xrv.refreshComplete();
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mainPersenter.getBeforeNewsListData("20160811");
                        main_disList_xrv.loadMoreComplete();

                    }
                }, 1000);
            }
        });


    }

    @Override
    public void setupComponent() {
        DaggerMainPersenterComponent.builder().mainModule(new MainModule(this)).build().inject(this);
    }


    @Override
    public void refresh(List<News> data) {
        listData.clear();
        for (int i = 0; i < data.size(); i++) {
            listData.add(data.get(i));
        }
        mainListAdapter.notifyDataSetChanged();

    }

    @Override
    public void onLoad(List<News> data) {
        for(int i=0;i<data.size();i++){
            listData.add(data.get(i));
        }
        mainListAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPersenter.detachView();
    }
}
