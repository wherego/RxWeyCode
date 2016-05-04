package cn.wey.basicframe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * listview通用适配器
 * Created by wey on 2015/11/30.
 */
public abstract class CommonBaseAdapter<VH extends CommonBaseAdapter.ViewHolder, T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> mList;
    public LayoutInflater mInflater;

    public CommonBaseAdapter(Context mContext, List<T> mList) {
        this.mContext = mContext;
        this.mList = mList;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return this.mList.size();
    }

    @Override
    public T getItem(int position) {
        return this.mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        VH holder = null;
        if (view == null) {
            holder = onCreateViewHolder(viewGroup, i);
            holder.view.setTag(holder);
        } else {
            holder = (VH) view.getTag();
        }

        onBindViewHolder(holder, i);
        return holder.view;
    }

    public View inflate(int resLayout, ViewGroup parent) {
        return mInflater.inflate(resLayout, parent, false);
    }

    public abstract VH onCreateViewHolder(ViewGroup parent, int position);

    public abstract void onBindViewHolder(VH holder, int position);

    public static class ViewHolder {
        View view;

        public ViewHolder(View view) {
            this.view = view;
        }
    }

}
