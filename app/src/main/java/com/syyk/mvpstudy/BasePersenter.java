package com.syyk.mvpstudy;

import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2018/3/17 0017.
 */

public interface BasePersenter <T extends BaseView> {

    //绑定View，这个方法将会在Activity中调用
    void attachView(@NonNull T view);
    //解绑
    void detachView();

}
