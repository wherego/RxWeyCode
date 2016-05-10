package cn.wey.rxweycode.presenter;

import cn.wey.rxweycode.ui.BaseView;

/**
 * Created by wey on 2016/5/9.
 */
public interface Presenter<V extends BaseView> {

    /**
     * 绑定view
     * @param baseView
     */
    void attachView(V baseView);

    /**
     * 分离view
     */
    void detachView();
}
