package cn.wey.rxweycode.ui;

/**
 * Created by wey on 2016/5/9.
 */
public interface BaseView {

    void showLoading(String msg);

    void hideLoading();

    void showError(String msg);

    void showEmpty(String msg);

}
