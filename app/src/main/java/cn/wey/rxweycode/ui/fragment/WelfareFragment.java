package cn.wey.rxweycode.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.io.Serializable;
import java.util.ArrayList;

import cn.wey.rxweycode.adapter.WelfareAdapter;
import cn.wey.rxweycode.model.EntityData;
import cn.wey.rxweycode.ui.activity.ScanPictureActivity;

/**
 * Created by wey on 2016/5/10.
 */
public class WelfareFragment extends CommonListFragment {

    public WelfareFragment(String type) {
        dataType = type;
    }

    @Override
    protected void initAdapter() {
        recyclerAdapter = new WelfareAdapter(baseActivity, new ArrayList<EntityData>());
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return null;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    protected void onListItemClick(int position) {
        Intent intent = new Intent(baseActivity, ScanPictureActivity.class);
        intent.putExtra(ScanPictureActivity.IMAGE_POSITION, position);
        intent.putExtra(ScanPictureActivity.LIST_DATA, (Serializable) recyclerAdapter.getDataList());
        startActivity(intent);
    }
}
