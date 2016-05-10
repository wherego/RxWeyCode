package cn.wey.rxweycode.adapter;

import android.content.Context;
import android.widget.ImageView;

import java.util.List;

import cn.wey.rxweycode.R;
import cn.wey.rxweycode.model.EntityData;
import cn.wey.rxweycode.util.GlideUtils;

/**
 * Created by wey on 2016/4/13.
 */
public class WelfareAdapter extends BaseRecyclerAdapter<EntityData> {
    public WelfareAdapter(Context ctx, List<EntityData> list) {
        super(ctx, list);
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.item_welfare;
    }

    @Override
    public void bindData(RecyclerViewHolder holder, int position, EntityData item) {
        ImageView welfarePic = holder.getImageView(R.id.welfare_pic_iv);
        GlideUtils.displayUrl(welfarePic, item.getUrl(), R.drawable.default_image);
    }
}
