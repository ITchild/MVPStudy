package com.syyk.mvpstudy.mvp.view.activity;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.syyk.mvpstudy.BaseActivity;
import com.syyk.mvpstudy.R;
import com.syyk.mvpstudy.di.component.DaggerNewsDetailPersenterComponent;
import com.syyk.mvpstudy.di.module.NewsDetailModule;
import com.syyk.mvpstudy.mvp.contract.NewsDetailContract;
import com.syyk.mvpstudy.mvp.presenter.NewsDetailPersenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/19 0019.
 */

public class NewsDetailActivity extends BaseActivity implements NewsDetailContract.View{
    @Bind(R.id.newDetail_appBarLayout)
    AppBarLayout newDetail_appBarLayout;
    @Bind(R.id.newDetail_collapsingToolbarLayout)
    CollapsingToolbarLayout newDetail_collapsingToolbarLayout;
    @Bind(R.id.newDetail_header_iv)
    ImageView newDetail_header_iv;
    @Bind(R.id.newDetail_title_tv)
    TextView newDetail_title_tv;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.newDetail_source_tv)
    TextView newDetail_source_tv;
    @Bind(R.id.newDetail_nested_view)
    NestedScrollView newDetail_nested_view;
    @Bind(R.id.newDetail_news_wv)
    WebView newDetail_news_wv;

    private String title;

    @Inject
    NewsDetailPersenter newsDetailPersenter;

    @Override
    public int setLayout() {
        return R.layout.activity_newdetail;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);

    }

    @Override
    public void initData() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        newDetail_collapsingToolbarLayout.setTitleEnabled(true);
        newsDetailPersenter.attachView(this);
        newsDetailPersenter.getDetailsNews();

    }
    @OnClick({R.id.newDetail_share_iv})
    public void newDetailOnClick(View view){
        switch (view.getId()){
            case R.id.newDetail_share_iv://分享
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                intent.putExtra(Intent.EXTRA_TEXT, "来自「纯净日报」的分享：" + title + "，http://daily.zhihu.com/story/" + getIntent().getIntExtra("new", 0));
                startActivity(Intent.createChooser(intent, title));
                break;
        }
    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupComponent() {
        DaggerNewsDetailPersenterComponent.builder().newsDetailModule(
                new NewsDetailModule(this,getIntent().getIntExtra("new",0))).build().inject(this);
    }

    @Override
    public void showData(String image, String title, String image_source, StringBuffer body) {
        Glide.with(this).load(image).into(newDetail_header_iv);
        this.title = title;
        newDetail_title_tv.setText(title);
        newDetail_source_tv.setText(image_source);
        newDetail_news_wv.setDrawingCacheEnabled(true);
        newDetail_news_wv.loadDataWithBaseURL("file:///android_asset/", body.toString(), "text/html", "utf-8", null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
