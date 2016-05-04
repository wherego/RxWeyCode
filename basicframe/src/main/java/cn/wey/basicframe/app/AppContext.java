package cn.wey.basicframe.app;

import android.app.Application;

/**
 * Created by wey on 2016/2/25.
 */
public class AppContext extends Application {

    private static AppContext instance = null;

    public static AppContext getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
