package cn.wey.rxweycode.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.bm.library.PhotoView;

import java.util.List;

import cn.wey.rxweycode.R;
import cn.wey.rxweycode.model.EntityData;
import cn.wey.rxweycode.util.GlideUtils;
import de.greenrobot.event.EventBus;

/**
 * Created by wey on 2016/4/18.
 */
public class ImagePagerAdapter extends PagerAdapter {

    private Context context;
    private List<EntityData> dataList;
    private LayoutInflater inflater;

    public ImagePagerAdapter(Context context, List<EntityData> dataList) {
        this.context = context;
        this.dataList = dataList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public int getCount() {
        if (dataList == null)
            return 0;
        return dataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View imageLayout = inflater.inflate(R.layout.item_image_pager, null);
        assert imageLayout != null;
        PhotoView photoView = (PhotoView) imageLayout.findViewById(R.id.photo_view);
        photoView.enable();
        GlideUtils.displayUrl(photoView, dataList.get(position).getUrl(), 0);
        EventBus.getDefault().post(dataList.get(position));
        container.addView(imageLayout, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        return imageLayout;
    }
}
