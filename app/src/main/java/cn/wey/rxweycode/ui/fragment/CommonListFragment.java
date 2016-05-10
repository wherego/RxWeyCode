package cn.wey.rxweycode.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.wey.rxweycode.R;
import cn.wey.rxweycode.adapter.BaseRecyclerAdapter;
import cn.wey.rxweycode.api.ApiConfig;
import cn.wey.rxweycode.model.EntityData;
import cn.wey.rxweycode.presenter.CommonListPresenter;
import cn.wey.rxweycode.ui.BaseFragment;
import cn.wey.rxweycode.ui.view.CommonListView;
import cn.wey.rxweycode.util.UIHelper;

/**
 * Created by wey on 2016/5/9.
 */
public abstract class CommonListFragment extends BaseFragment implements CommonListView<EntityData> {

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout refreshLayout;

    @Bind(R.id.list_rv)
    RecyclerView recyclerView;
    protected BaseRecyclerAdapter<EntityData> recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private CommonListPresenter listPresenter;

    boolean isLoading = false;
    protected int page = 1; //页码
    protected String dataType = ""; //数据类型


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.swipe_refresh_layout, container, false);
        initViews(view);
        return view;
    }

    protected void initViews(View view) {
        ButterKnife.bind(this, view);
        if (getItemDecoration() != null) {
            recyclerView.addItemDecoration(getItemDecoration());
        }
        if (getLayoutManager() != null) {
            layoutManager = getLayoutManager();
            recyclerView.setLayoutManager(layoutManager);
        }
        initAdapter();
        // 第一次进入页面的时候显示加载进度条  
        refreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics()));
        refreshLayout.setColorSchemeResources(R.color.style_color_primary, R.color.style_color_accent);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                listPresenter.loadData(dataType, page);
            }
        });
        recyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                onListItemClick(pos);
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = getLastVisiblePosition(layoutManager);
                if (lastVisibleItemPosition + 1 == recyclerAdapter.getItemCount()) {
                    boolean isRefreshing = refreshLayout.isRefreshing();
                    if (isRefreshing) {
                        return;
                    }
                    if (!isLoading) {
                        isLoading = true;
                        listPresenter.loadData(dataType, ++page);
                    }
                }
            }
        });
        listPresenter = new CommonListPresenter();
        listPresenter.attachView(this);
        listPresenter.loadData(dataType, page);
    }

    @Override
    public void refreshData(List<EntityData> dataList) {
        recyclerAdapter.removeAll();
        recyclerAdapter.addAll(dataList);
    }

    @Override
    public void loadMoreData(List<EntityData> dataList) {
        isLoading = false;
        recyclerAdapter.addAll(dataList);
    }

    @Override
    public void showLoading(String msg) {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showError(String msg, View.OnClickListener onClickListener) {
        refreshLayout.setRefreshing(false);
        page = --page; //请求出错，页码减1
        UIHelper.showToast(msg);
    }

    @Override
    public void showEmpty(String msg, View.OnClickListener onClickListener) {
    }

    /**
     * 初始化Adapter
     */
    protected abstract void initAdapter();

    /**
     * 添加ItemDecoration
     *
     * @return
     */
    protected abstract RecyclerView.ItemDecoration getItemDecoration();

    /**
     * 设置LayoutManager
     *
     * @return
     */
    protected abstract RecyclerView.LayoutManager getLayoutManager();

    /**
     * 点击item
     *
     * @param position
     */
    protected abstract void onListItemClick(int position);

    /**
     * 获取最后一条展示的位置
     *
     * @return
     */
    private int getLastVisiblePosition(RecyclerView.LayoutManager layoutManager) {
        int position;
        if (layoutManager instanceof LinearLayoutManager) {
            position = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        } else if (layoutManager instanceof GridLayoutManager) {
            position = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            int[] lastPositions = staggeredGridLayoutManager.findLastVisibleItemPositions(new int[staggeredGridLayoutManager.getSpanCount()]);
            position = getMaxPosition(lastPositions);
        } else {
            position = getLayoutManager().getItemCount() - 1;
        }
        return position;
    }

    /**
     * 获得最大的位置
     *
     * @param positions
     * @return
     */
    private int getMaxPosition(int[] positions) {
        int size = positions.length;
        int maxPosition = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            maxPosition = Math.max(maxPosition, positions[i]);
        }
        return maxPosition;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        listPresenter.detachView();
    }
}
