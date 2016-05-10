package cn.wey.rxweycode.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import cn.wey.rxweycode.adapter.CommonItemAdapter;
import cn.wey.rxweycode.model.EntityData;

/**
 * 通用型FragmentList
 * Created by wey on 2016/5/10.
 */
public class DataListFragment extends CommonListFragment {

    public DataListFragment(String type) {
        dataType = type;
    }

    @Override
    protected void initAdapter() {
        recyclerAdapter = new CommonItemAdapter(baseActivity, new ArrayList<EntityData>());
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return null;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return mLinearLayoutManager;
    }

    @Override
    protected void onListItemClick(int position) {
        loadWebViewUrl(recyclerAdapter.getItem(position).getUrl());
    }
}
