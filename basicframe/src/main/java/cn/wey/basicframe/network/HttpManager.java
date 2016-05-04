package cn.wey.basicframe.network;

import android.content.Context;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.AQUtility;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Map;

import cn.wey.basicframe.app.AppContext;
import cn.wey.basicframe.base.BaseObject;
import cn.wey.basicframe.utils.JsonUtils;

/**
 * AQuery框架
 * Created by wey on 2016/3/3.
 */
public class HttpManager extends BaseObject {

    private static HttpManager instance = null;

    private HttpManager() {
    }

    private AQuery aQuery = null;

    public static HttpManager getInstance() {
        if (instance == null) {
            instance = new HttpManager();
            instance.init();
        }
        return instance;
    }

    private void init() {
        if (aQuery == null) {
            aQuery = new AQuery(AppContext.getInstance());
        }
    }

    /**
     * post请求
     *
     * @param url
     * @param params
     * @param callback
     */
    public void post(String url, Map<String, ?> params, AjaxCallback<JSONObject> callback) {
        aQuery.ajax(url, params, JSONObject.class, callback);
    }

    /**
     * post请求
     *
     * @param url
     * @param callback
     */
    public void post(String url, AjaxCallback<JSONObject> callback) {
        aQuery.ajax(url, JSONObject.class, callback);
    }

    /**
     * post对象
     *
     * @param url
     * @param json
     * @param callback
     * @throws JSONException
     */
    public void post(String url, Object json, AjaxCallback<JSONObject> callback) throws JSONException {
        aQuery.post(url, JsonUtils.toJsonObject(json), JSONObject.class, callback);
    }

    /**
     * 下载文件
     *
     * @param url        下载地址
     * @param targetPath 下载文件目录
     * @param callback
     */
    public void downloadFile(String url, String targetPath, final FileCallback callback) {
        //设置目标文件
        File target = new File(targetPath);
        //若文件目录不存在，则创建目录
        if (target.getParentFile().exists() == false) {
            target.getParentFile().mkdirs();
        }
        //开始下载至目标文件目录
        aQuery.download(url, target, new AjaxCallback<File>() {
            public void callback(String url, File file, AjaxStatus status) {
                if (callback != null) {
                    NetStatus netStatus = new NetStatus(status.getCode(), status.getMessage(), status.getError());
                    if (file != null) {
                        callback.success(url, file, netStatus);
                    } else {
                        callback.failure(netStatus);
                    }
                }
            }
        });
    }

    /**
     * 下载文件
     *
     * @param url
     * @param targetPath
     * @param callback
     */
    public void downloadFileAjax(String url, String targetPath, final FileCallback callback) {
        //设置目标文件
        File target = new File(targetPath);
        //若文件目录不存在，则创建目录
        if (target.getParentFile().exists() == false) {
            target.getParentFile().mkdirs();
        }
        //开始下载至目标文件目录
        aQuery.ajax(url, File.class, new AjaxCallback<File>() {
            public void callback(String url, File file, AjaxStatus status) {
                if (callback != null) {
                    NetStatus netStatus = new NetStatus(status.getCode(), status.getMessage(), status.getError());
                    if (file != null) {
                        callback.success(url, file, netStatus);
                    } else {
                        callback.failure(netStatus);
                    }
                }
            }
        });
    }

    /**
     * 清理缓存,没下载成功的临时文件
     *
     * @param context
     */
    public void cleanHttpCache(Context context) {
        long triggerSize = 30000000;
        //remove the least recently used files until cache size is less than 2M
        long targetSize = 5000000;
        AQUtility.cleanCacheAsync(context, triggerSize, targetSize);
    }

    /**
     * 获取http请求回调
     *
     * @param callback
     * @return
     */
    public AjaxCallback<JSONObject> getHttpCallback(final PostCallback callback) {
        AjaxCallback<JSONObject> ajaxCallback = new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                if (callback != null) {
                    NetStatus netStatus = new NetStatus(status.getCode(), status.getMessage(), status.getError());
                    try {
                        if (status.getCode() == NetStatus.RETURN_CODE_SUCCESS) {
                            if (object != null) {
                                callback.retResult(object, netStatus);
                            }
                        } else {
                            callback.retNull(netStatus);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        callback.retNull(netStatus);
                    }
                }
            }
        };
        return ajaxCallback;
    }
}
