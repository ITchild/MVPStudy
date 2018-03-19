package com.syyk.mvpstudy.mvp.contract;

import com.syyk.mvpstudy.BasePersenter;
import com.syyk.mvpstudy.BaseView;
import com.syyk.mvpstudy.bean.News;

import java.util.List;

/**
 * Created by Administrator on 2018/3/17 0017.
 *
 * 契约类
 */

public interface MainContract {

    interface View extends BaseView{
        void refresh(List<News> data);
        void onLoad(List<News> data);

    }

    interface Persenter extends BasePersenter<View>{
        void getBeforeNewsListData(String date);
        void getNewsListData();
        void getRefreshNewsListData();
    }

}
