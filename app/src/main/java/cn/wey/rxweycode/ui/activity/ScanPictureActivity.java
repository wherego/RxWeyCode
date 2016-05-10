package cn.wey.rxweycode.ui.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.wey.rxweycode.R;
import cn.wey.rxweycode.adapter.ImagePagerAdapter;
import cn.wey.rxweycode.config.Const;
import cn.wey.rxweycode.model.EntityData;
import cn.wey.rxweycode.ui.BaseActivity;
import cn.wey.rxweycode.util.StringUtils;
import de.greenrobot.event.EventBus;

/**
 * 查看大图
 * Created by wey on 2016/4/13.
 */
public class ScanPictureActivity extends BaseActivity {

    @Bind(R.id.tool_bar)
    Toolbar toolbar;

    @Bind(R.id.view_pager)
    ViewPager viewPager;

    private ImagePagerAdapter imagePagerAdapter;
    private int pagerPosition = 0;
    private List<EntityData> listData;
    public static final String LIST_DATA = "list_data";
    public static final String IMAGE_POSITION = "image_position";
    private static final String STATE_POSITION = "STATE_POSITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_pic);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        pagerPosition = getIntent().getExtras().getInt(IMAGE_POSITION, 0);
        if (savedInstanceState != null) {
            pagerPosition = savedInstanceState.getInt(STATE_POSITION);
        }
        initViews();
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(EntityData entityData) {
        if (entityData != null) {
            setTitle(entityData.getDesc());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_POSITION, viewPager.getCurrentItem());
    }
    
    private void initViews() {
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);
        ab.setDisplayHomeAsUpEnabled(true);
        listData = (List<EntityData>) getIntent().getSerializableExtra(LIST_DATA);
        imagePagerAdapter = new ImagePagerAdapter(baseActivity, listData);
        viewPager.setAdapter(imagePagerAdapter);
        viewPager.setCurrentItem(pagerPosition);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.scan_view_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_share:
                downloadPicture(listData.get(viewPager.getCurrentItem()).getUrl(), true);
                break;
            case R.id.action_download:
                downloadPicture(listData.get(viewPager.getCurrentItem()).getUrl(), false);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 下载图片（分享）
     *
     * @param url
     * @param isShare
     */
    private void downloadPicture(String url, final boolean isShare) {
        if (!StringUtils.isEmpty(url)) {
            final String filePath = Environment.getExternalStorageDirectory()
                    + File.separator + Const.IMAGE_FOLDER
                    + File.separator + "mn" + System.currentTimeMillis() + ".jpg";
        }
    }
}
