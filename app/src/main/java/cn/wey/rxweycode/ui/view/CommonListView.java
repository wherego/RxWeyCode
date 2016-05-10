package cn.wey.rxweycode.ui.view;

import java.util.List;

import cn.wey.rxweycode.ui.BaseView;

/**
 * Created by wey on 2016/5/9.
 */
public interface CommonListView<T> extends BaseView {

    void refreshData(List<T> dataList);

    void loadMoreData(List<T> dataList);
}
