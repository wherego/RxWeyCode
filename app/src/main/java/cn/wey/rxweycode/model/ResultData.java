package cn.wey.rxweycode.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 分类数据
 * Created by wey on 2016/4/5.
 */
public class ResultData extends Error implements Serializable {

    @SerializedName("results")
    private List<EntityData> results;

    public List<EntityData> getResults() {
        return results;
    }

    public void setResults(List<EntityData> results) {
        this.results = results;
    }
}
