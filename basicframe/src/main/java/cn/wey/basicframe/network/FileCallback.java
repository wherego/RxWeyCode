package cn.wey.basicframe.network;

import java.io.File;

/**
 * 文件下载请求返回
 * Created by wey on 2016/3/3.
 */
public interface FileCallback {

    /**
     * 下载失败
     *
     * @param netStatus
     */
    void failure(NetStatus netStatus);

    /**
     * 下载成功
     *
     * @param url
     * @param file
     * @param netStatus
     */
    void success(String url, File file, NetStatus netStatus);
}
