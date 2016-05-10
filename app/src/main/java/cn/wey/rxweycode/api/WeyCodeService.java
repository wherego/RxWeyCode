package cn.wey.rxweycode.api;

import cn.wey.rxweycode.model.DailyData;
import cn.wey.rxweycode.model.ResultData;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wey on 2016/5/4.
 */
public interface WeyCodeService {

    @GET("data/{type}/{size}/{page}")
    Observable<ResultData> getData(@Path("type") String type, @Path("size") int size, @Path("page") int page);

    @GET("day/{year}/{month}/{day}")
    Observable<DailyData> getDaily(@Path("year") int year, @Path("month") int month, @Path("day") int day);
}
