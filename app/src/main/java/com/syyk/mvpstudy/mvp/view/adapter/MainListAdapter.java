package com.syyk.mvpstudy.mvp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.syyk.mvpstudy.R;
import com.syyk.mvpstudy.bean.News;
import com.syyk.mvpstudy.mvp.view.activity.NewsDetailActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/19 0019.
 */

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.NewsViewHolder> {
    private Context mContext;
    private List<News> mNewsList;
    private long lastPos = -1;

    public MainListAdapter(Context context, List<News> newsList) {
        this.mContext = context;
        this.mNewsList = newsList;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_list, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        final News news = mNewsList.get(position);
        if(news == null){
            return;
        }
        bindViewHolder(holder,position,news);
    }

    private void bindViewHolder(NewsViewHolder holder, int position, News news) {
        holder.mTvTitle.setText(news.getTitle());
        List<String> images = news.getImages();
        if(images != null && images.size() > 0){
            Glide.with(mContext).load(images.get(0)).placeholder(R.drawable.ic_placeholder).into(holder.mIvNews);
            holder.mCvItem.setOnClickListener(getListener(holder,news));
        }
    }

    private View.OnClickListener getListener(NewsViewHolder holder, final News news) {
        return new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NewsDetailActivity.class);
                intent.putExtra("new",news.getId());
                mContext.startActivity(intent);
            }
        };
    }

    @Override
    public int getItemCount() {
        return mNewsList == null ? 0 : mNewsList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.cv_item)
        RelativeLayout mCvItem;

        @Bind(R.id.iv_news)
        ImageView mIvNews;

        @Bind(R.id.tv_title)
        TextView mTvTitle;

        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
