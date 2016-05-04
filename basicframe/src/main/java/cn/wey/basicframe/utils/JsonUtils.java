package cn.wey.basicframe.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by wey on 2016/3/3.
 */
public class JsonUtils {

    private static Gson gson = null;

    public static Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        }
        return gson;
    }

    /**
     * java对象装换json字符串
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        return getGson().toJson(obj);
    }

    /**
     * java对象转换JsonObject
     *
     * @param obj
     * @return
     * @throws JSONException
     */
    public static JSONObject toJsonObject(Object obj) throws JSONException {
        return new JSONObject(toJson(obj));
    }

    /**
     * json字符串转JsonObject
     *
     * @param json
     * @return
     * @throws JSONException
     */
    public static JSONObject toJsonObject(String json) throws JSONException {
        return new JSONObject(json);
    }

    /**
     * json字符串转对象
     *
     * @param json
     * @param classOfT
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        try {
            return getGson().fromJson(json, classOfT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json字符串转对象
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Type type) {
        try {
            return getGson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
