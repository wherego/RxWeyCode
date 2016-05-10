package cn.wey.rxweycode.util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * glide加载图片工具
 * Created by wey on 2016/4/8.
 */
public class GlideUtils {

    /**
     * glide加载本地图片资源
     *
     * @param view
     * @param resId
     */
    public static void displayNative(final ImageView view, @DrawableRes int resId) {
        if (view == null) {
            return;
        }
        Context context = view.getContext();
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        Glide.with(context)
                .load(resId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .centerCrop()
                .into(view);
    }

    /**
     * Glide 加载网络图片资源
     *
     * @param view
     * @param url
     * @param defaultImage 可设置为0
     */
    public static void displayUrl(final ImageView view, String url, @DrawableRes int defaultImage) {
        if (view == null) {
            return;
        }
        Context context = view.getContext();
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(defaultImage)
                .crossFade()
//                .centerCrop()
                .into(view);
    }
}
