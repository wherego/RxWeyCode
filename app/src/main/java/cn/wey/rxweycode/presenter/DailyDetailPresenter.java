package cn.wey.rxweycode.presenter;

import cn.wey.rxweycode.api.HttpMethods;
import cn.wey.rxweycode.config.Const;
import cn.wey.rxweycode.model.DailyData;
import cn.wey.rxweycode.model.EntityData;
import cn.wey.rxweycode.ui.view.DisplayInterfaceView;
import cn.wey.rxweycode.util.DateUtils;
import cn.wey.rxweycode.util.StringUtils;
import rx.Subscriber;

/**
 * Created by wey on 2016/5/10.
 */
public class DailyDetailPresenter extends BasePresenter<DisplayInterfaceView<DailyData>> {

    public void loadData(EntityData entity) {
        String dateTime = DateUtils.DateToString(DateUtils.formatStringByFormat(entity.getPublishedAt(), Const.DATA_TIME_FORMAT));
        if (!StringUtils.isEmpty(dateTime) && dateTime.length() > 10) {
            int year = Integer.parseInt(dateTime.substring(0, 4));
            int month = Integer.parseInt(dateTime.substring(5, 7));
            int day = Integer.parseInt(dateTime.substring(8, 10));
            HttpMethods.getInstance().getDaily(year, month, day, new Subscriber<DailyData>() {
                @Override
                public void onCompleted() {
                    getView().hideLoading();
                }

                @Override
                public void onError(Throwable e) {
                    getView().showError(e.getMessage(), null);
                }

                @Override
                public void onNext(DailyData dailyData) {
                    getView().showUI(dailyData);
                }
            });
        }
    }
}
