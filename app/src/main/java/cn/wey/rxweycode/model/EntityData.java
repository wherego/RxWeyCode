package cn.wey.rxweycode.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 标准数据
 * Created by wey on 2016/4/5.
 */
public class EntityData implements Serializable {

    @SerializedName("_id")
    private String _id;

    @SerializedName("_ns")
    private String _ns;

    //创建时间
    @SerializedName("createdAt")
    private String createdAt;

    //描述
    @SerializedName("desc")
    private String desc;

    //发布时间
    @SerializedName("publishedAt")
    private String publishedAt;

    //来源
    @SerializedName("source")
    private String source;

    //类型
    @SerializedName("type")
    private String type;

    @SerializedName("url")
    private String url;

    @SerializedName("used")
    private boolean used;

    @SerializedName("who")
    private String who;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_ns() {
        return _ns;
    }

    public void set_ns(String _ns) {
        this._ns = _ns;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}

