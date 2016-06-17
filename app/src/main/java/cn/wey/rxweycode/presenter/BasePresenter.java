package cn.wey.rxweycode.presenter;

import cn.wey.rxweycode.ui.BaseView;

/**
 * Created by wey on 2016/5/9.
 */
public class BasePresenter<V extends BaseView> implements Presenter<V> {

    private V view;

    @Override
    public void attachView(V baseView) {
        view = baseView;
    }

    @Override
    public void detachView() {
        view = null;
    }
    
    public V getView() {
        return view;
    }
    
    public boolean isViewAttached() {
        return view != null;
    }
}
