package cn.wey.rxweycode.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.wey.rxweycode.R;
import cn.wey.rxweycode.model.DailyData;
import cn.wey.rxweycode.model.EntityData;
import cn.wey.rxweycode.presenter.DailyDetailPresenter;
import cn.wey.rxweycode.ui.BaseActivity;
import cn.wey.rxweycode.ui.view.DisplayInterfaceView;
import cn.wey.rxweycode.util.GlideUtils;
import cn.wey.rxweycode.util.UIHelper;

/**
 * 每日精彩详情
 * Created by wey on 2016/4/13.
 */
public class DailyDetailActivity extends BaseActivity implements DisplayInterfaceView<DailyData> {

    @Bind(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @Bind(R.id.top_image_iv)
    KenBurnsView topImage;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.detail_layout)
    LinearLayout detailLayout;
    public static final String ENTITY_DATA = "entity_data";
    private DailyDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_detail);
        ButterKnife.bind(this);
        initViews();
    }
    
    private void initViews() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);
        EntityData entityData = (EntityData) getIntent().getSerializableExtra(ENTITY_DATA);
        GlideUtils.displayUrl(topImage, entityData.getUrl(), 0);
        toolbarLayout.setTitle(entityData.getDesc());
        presenter = new DailyDetailPresenter();
        presenter.attachView(this);
        presenter.loadData(entityData);
    }

    

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoading(String msg) {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissProgressDialog();
            }
        }, 500);
    }

    @Override
    public void showError(String msg, View.OnClickListener onClickListener) {
        hideLoading();
        UIHelper.showToast(msg);
    }

    @Override
    public void showEmpty(String msg, View.OnClickListener onClickListener) {}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void showUI(DailyData data) {
        if (data != null) {
            DailyData.DailyResults results = data.getResults();
            addLayoutView(results.getAndroidData(), R.string.String_android, R.color.google_dark_blue);
            addLayoutView(results.getIosData(), R.string.String_ios, R.color.style_color_primary);
            addLayoutView(results.getJsData(), R.string.String_js, R.color.btn_send_pressed);
            addLayoutView(results.getWelfareData(), R.string.String_welfare, R.color.google_dark_green);
            addLayoutView(results.getVideoData(), R.string.String_video, R.color.google_dark_orange);
            addLayoutView(results.getAppData(), R.string.String_app, R.color.google_dark_red);
            addLayoutView(results.getResourcesData(), R.string.String_resource, R.color.purple);
            addLayoutView(results.getRecommendData(), R.string.String_recommend, R.color.style_color_primary_dark);
        }
    }

    private void addLayoutView(final ArrayList<EntityData> entityDataList, final int resTitle, int resColor) {
        if (entityDataList != null && entityDataList.size() != 0) {
            View itemDailyView = LayoutInflater.from(baseActivity).inflate(R.layout.item_daily_common, null);
            TextView tvTitle = (TextView) itemDailyView.findViewById(R.id.item_title);
            tvTitle.setText(resTitle);
            tvTitle.setTextColor(getResources().getColor(resColor));
            LinearLayout childLayout = (LinearLayout) itemDailyView.findViewById(R.id.item_child_layout);
            for (int i = 0; i < entityDataList.size(); i++) {
                View viewChild = LayoutInflater.from(baseActivity).inflate(R.layout.item_daily_child, null);
                ((TextView) viewChild.findViewById(R.id.item_desc_tv)).setText(entityDataList.get(i).getDesc());
                final int finalI = i;
                viewChild.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (resTitle == R.string.String_welfare) {
                            Intent intent = new Intent(baseActivity, ScanPictureActivity.class);
                            intent.putExtra(ScanPictureActivity.LIST_DATA, entityDataList);
                            startActivity(intent);
                        } else {
                            loadWebViewUrl(entityDataList.get(finalI).getUrl());
                        }
                    }
                });
                childLayout.addView(viewChild);
            }
            detailLayout.addView(itemDailyView);
        }
    }
}
