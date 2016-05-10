package cn.wey.rxweycode.config;

import android.content.Context;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;

/**
 * Glide初始配置
 * Created by wey on 2016/3/31.
 */
public class CacheGlideModule implements GlideModule {
    int cacheSize = 250 * 1024 * 1024; //缓存空间大小

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize(); //获取默认内存缓存大小
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();

        int customMemoryCacheSize = (int) (1.2 * defaultMemoryCacheSize);
        int customBitmapPoolSize = (int) (1.2 * defaultBitmapPoolSize);

        builder.setMemoryCache(new LruResourceCache(customMemoryCacheSize));
        builder.setBitmapPool(new LruBitmapPool(customBitmapPoolSize));
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        String cachePath = Environment.getExternalStorageDirectory().getPath();

        builder.setDiskCache(new DiskLruCacheFactory(cachePath, Const.CACHE_FOLDER, cacheSize));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
