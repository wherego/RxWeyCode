package cn.wey.basicframe.network;

import org.json.JSONObject;

/**
 * 数据请求返回接口
 * Created by wey on 2016/2/29.
 */
public interface PostCallback {

    /**
     * 返回成功（含数据）
     *
     * @param object
     * @param netStatus
     */
    void retResult(JSONObject object, NetStatus netStatus);

    /**
     * 返回为null
     *
     * @param netStatus
     */
    void retNull(NetStatus netStatus);
}
