package com.syyk.mvpstudy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2018/3/17 0017.
 */

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        setupComponent();
        initView();
        initData();
        initListener();
    }



    public abstract int setLayout();
    public abstract void initView();
    public abstract void initData();
    public abstract void initListener();
    public abstract void setupComponent();


}
