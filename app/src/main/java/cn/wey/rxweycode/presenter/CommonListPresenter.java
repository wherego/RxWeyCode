package cn.wey.rxweycode.presenter;

import java.util.List;

import cn.wey.rxweycode.api.HttpMethods;
import cn.wey.rxweycode.model.EntityData;
import cn.wey.rxweycode.model.ResultData;
import cn.wey.rxweycode.ui.view.CommonListView;
import cn.wey.rxweycode.util.StringUtils;
import rx.Subscriber;

/**
 * 这里进行通用列表数据的处理
 * Created by wey on 2016/5/9.
 */
public class CommonListPresenter extends BasePresenter<CommonListView<EntityData>> {

    public void loadData(String type, final int page) {
        getView().showLoading("加载中...");
        if (!StringUtils.isEmpty(type)) {
            HttpMethods.getInstance().getData(type, page, new Subscriber<ResultData>() {
                @Override
                public void onCompleted() {
                    getView().hideLoading();
                }

                @Override
                public void onError(Throwable e) {
                    getView().showError(e.getMessage(),null);
                }

                @Override
                public void onNext(ResultData resultData) {
                    List<EntityData> datas = resultData.getResults();
                    if (page == 1) {
                        getView().refreshData(datas);
                    }else {
                        getView().loadMoreData(datas);
                    }
                }
            });
        }
    }
}
