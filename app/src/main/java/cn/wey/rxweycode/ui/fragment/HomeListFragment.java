package cn.wey.rxweycode.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import cn.wey.rxweycode.adapter.DailyAdapter;
import cn.wey.rxweycode.model.EntityData;
import cn.wey.rxweycode.ui.activity.DailyDetailActivity;

/**
 * Created by wey on 2016/5/10.
 */
public class HomeListFragment extends CommonListFragment {

    public HomeListFragment(String type) {
        dataType = type;
    }

    @Override
    protected void initAdapter() {
        recyclerAdapter = new DailyAdapter(baseActivity, new ArrayList<EntityData>());
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
        Intent intent = new Intent(baseActivity, DailyDetailActivity.class);
        intent.putExtra(DailyDetailActivity.ENTITY_DATA, recyclerAdapter.getItem(position));
        startActivity(intent);
    }
}
