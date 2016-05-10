package cn.wey.rxweycode.ui.view;

import cn.wey.rxweycode.ui.BaseView;

/**
 * 加载数据后显示界面
 * Created by wey on 2016/5/10.
 */
public interface DisplayInterfaceView<T> extends BaseView {

    void showUI(T data);
}
