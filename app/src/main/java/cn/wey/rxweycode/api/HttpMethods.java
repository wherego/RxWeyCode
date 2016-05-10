package cn.wey.rxweycode.api;

import java.util.concurrent.TimeUnit;

import cn.wey.rxweycode.model.DailyData;
import cn.wey.rxweycode.model.ResultData;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wey on 2016/5/4.
 */
public class HttpMethods {

    private static HttpMethods instance = null;
    private static final int DEFAULT_TIMEOUT = 10;

    private Retrofit retrofit;

    private WeyCodeService weyCodeService;

    public static HttpMethods getInstance() {
        if (instance == null) {
            instance = new HttpMethods();
        }
        return instance;
    }

    private HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiConfig.HTTP_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        weyCodeService = retrofit.create(WeyCodeService.class);
    }

    /**
     * 获取列表数据
     *
     * @param type
     * @param page
     */
    public void getData(String type, int page, Subscriber<ResultData> subscriber) {
        weyCodeService.getData(type, ApiConfig.DEFAULT_DATA_SIZE, page)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获取每日数据
     *
     * @param year
     * @param month
     * @param day
     */
    public void getDaily(int year, int month, int day, Subscriber<DailyData> subscriber) {
        weyCodeService.getDaily(year, month, day)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
