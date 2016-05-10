package cn.wey.rxweycode.ui;

import android.view.View;

import cn.wey.rxweycode.presenter.BasePresenter;

/**
 * Created by wey on 2016/5/9.
 */
public interface BaseView {

    void showLoading(String msg);

    void hideLoading();

    void showError(String msg, View.OnClickListener onClickListener);

    void showEmpty(String msg, View.OnClickListener onClickListener);
    
}
