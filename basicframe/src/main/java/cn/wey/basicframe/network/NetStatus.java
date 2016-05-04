package cn.wey.basicframe.network;

/**
 * Created by wey on 2016/2/29.
 */
public class NetStatus {

    /**
     * 服务器返回代码：正常
     */
    public static final String RETURN_STATUS_SUCCESS = "200";
    /**
     * 服务器返回代码：正常
     */
    public static final int RETURN_CODE_SUCCESS = 200;
    /**
     * 服务器返回代码：请求超时
     */
    public static final int ERROR_CODE_NG101 = -101;

    public static final int AUTH_ERROR = -102;
    public static final int TRANSFORM_ERROR = -103;
    private int code = 200;
    private String message = "OK";
    private String error;

    public NetStatus(int code, String message, String error) {
        this.code = code;
        this.message = message;
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
