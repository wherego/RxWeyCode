package cn.wey.rxweycode;


import android.app.Application;

/**
 * Created by wey on 2016/5/4.
 */
public class RxWeyCodeApp extends Application{

    private static RxWeyCodeApp rxWeyCodeApp;
    @Override
    public void onCreate() {
        super.onCreate();
        rxWeyCodeApp = this;
    }
    
    public static RxWeyCodeApp getInstance() {
        return rxWeyCodeApp;
    }
}
