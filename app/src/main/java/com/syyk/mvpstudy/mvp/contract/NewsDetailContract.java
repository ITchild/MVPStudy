package com.syyk.mvpstudy.mvp.contract;

import com.syyk.mvpstudy.BasePersenter;
import com.syyk.mvpstudy.BaseView;

/**
 * Created by fei on 2018/3/20.
 */

public interface NewsDetailContract {

    interface View extends BaseView{
        void showData(String image,String title,String image_source,StringBuffer body);
    }

    interface Persenter extends BasePersenter<View>{
        void getDetailsNews();
    }
}
