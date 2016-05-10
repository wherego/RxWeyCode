package cn.wey.rxweycode.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 每日数据
 * Created by wey on 2016/4/7.
 */
public class DailyData extends Error implements Serializable {

    @SerializedName("category")
    private ArrayList<String> category;

    @SerializedName("results")
    private DailyResults results;

    public class DailyResults {

        @SerializedName("福利")
        private ArrayList<EntityData> welfareData;

        @SerializedName("Android")
        private ArrayList<EntityData> androidData;

        @SerializedName("iOS")
        private ArrayList<EntityData> iosData;

        @SerializedName("前端")
        private ArrayList<EntityData> jsData;

        @SerializedName("休息视频")
        private ArrayList<EntityData> videoData;

        @SerializedName("拓展资源")
        private ArrayList<EntityData> resourcesData;

        @SerializedName("App")
        private ArrayList<EntityData> appData;

        @SerializedName("瞎推荐")
        private ArrayList<EntityData> recommendData;

        public ArrayList<EntityData> getWelfareData() {
            return welfareData;
        }

        public void setWelfareData(ArrayList<EntityData> welfareData) {
            this.welfareData = welfareData;
        }

        public ArrayList<EntityData> getAndroidData() {
            return androidData;
        }

        public void setAndroidData(ArrayList<EntityData> androidData) {
            this.androidData = androidData;
        }

        public ArrayList<EntityData> getIosData() {
            return iosData;
        }

        public void setIosData(ArrayList<EntityData> iosData) {
            this.iosData = iosData;
        }

        public ArrayList<EntityData> getJsData() {
            return jsData;
        }

        public void setJsData(ArrayList<EntityData> jsData) {
            this.jsData = jsData;
        }

        public ArrayList<EntityData> getVideoData() {
            return videoData;
        }

        public void setVideoData(ArrayList<EntityData> videoData) {
            this.videoData = videoData;
        }

        public ArrayList<EntityData> getResourcesData() {
            return resourcesData;
        }

        public void setResourcesData(ArrayList<EntityData> resourcesData) {
            this.resourcesData = resourcesData;
        }

        public ArrayList<EntityData> getAppData() {
            return appData;
        }

        public void setAppData(ArrayList<EntityData> appData) {
            this.appData = appData;
        }

        public ArrayList<EntityData> getRecommendData() {
            return recommendData;
        }

        public void setRecommendData(ArrayList<EntityData> recommendData) {
            this.recommendData = recommendData;
        }
    }

    public ArrayList<String> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<String> category) {
        this.category = category;
    }

    public DailyResults getResults() {
        return results;
    }

    public void setResults(DailyResults results) {
        this.results = results;
    }
}
