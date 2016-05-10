package cn.wey.rxweycode.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cn.wey.rxweycode.R;
import cn.wey.rxweycode.config.Const;
import cn.wey.rxweycode.model.EntityData;
import cn.wey.rxweycode.util.DateUtils;
import cn.wey.rxweycode.util.DisplayUtils;
import cn.wey.rxweycode.util.GlideUtils;
import cn.wey.rxweycode.util.StringUtils;

/**
 * Created by wey on 2016/4/7.
 */
public class DailyAdapter extends BaseRecyclerAdapter<EntityData> {
    LinearLayout.LayoutParams layoutParams;
    public DailyAdapter(Context ctx, List<EntityData> list) {
        super(ctx, list);
        int width = DisplayUtils.getWidth((Activity) ctx);
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,width*2/3);
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.item_daily;
    }

    @Override
    public void bindData(RecyclerViewHolder holder, int position, EntityData item) {
        final ImageView dailyPic = holder.getImageView(R.id.daily_pic_iv);
        dailyPic.setLayoutParams(layoutParams);
        TextView dailyDesc = holder.getTextView(R.id.daily_desc_tv);
        TextView dailyDate = holder.getTextView(R.id.daily_date_tv);
        GlideUtils.displayUrl(dailyPic, item.getUrl(), R.drawable.default_image);
        if (!StringUtils.isEmpty(item.getPublishedAt()) && item.getPublishedAt().length() > 10) {
            dailyDate.setText(DateUtils.DateToString(DateUtils
                    .formatStringByFormat(item.getPublishedAt(), Const.DATA_TIME_FORMAT))
                    .substring(0, 10));
        }
        dailyDesc.setText(item.getWho());
    }

}
