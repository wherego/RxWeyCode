package cn.wey.rxweycode.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import cn.wey.rxweycode.R;
import cn.wey.rxweycode.config.Const;
import cn.wey.rxweycode.model.EntityData;
import cn.wey.rxweycode.util.DateUtils;
import cn.wey.rxweycode.util.StringUtils;

/**
 * Created by wey on 2016/4/14.
 */
public class CommonItemAdapter extends BaseRecyclerAdapter<EntityData> {
    public CommonItemAdapter(Context ctx, List<EntityData> list) {
        super(ctx, list);
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.item_common;
    }

    @Override
    public void bindData(RecyclerViewHolder holder, int position, EntityData item) {
        TextView descTv = holder.getTextView(R.id.common_desc_tv);
        TextView whoTv = holder.getTextView(R.id.common_who_tv);
        TextView dateTv = holder.getTextView(R.id.common_date_tv);
        descTv.setText(item.getDesc());
        if (!StringUtils.isEmpty(item.getWho())) {
            whoTv.setText("via." + item.getWho());
        } else {
            whoTv.setText("via.None");
        }
        dateTv.setText(DateUtils.getFriendlyTime(DateUtils.formatStringByFormat(item.getCreatedAt(), Const.DATA_TIME_FORMAT)));
    }
}
