package cn.wey.rxweycode.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.wey.rxweycode.R;
import cn.wey.rxweycode.api.ApiConfig;
import cn.wey.rxweycode.ui.BaseActivity;
import cn.wey.rxweycode.ui.fragment.DataListFragment;
import cn.wey.rxweycode.ui.fragment.HomeListFragment;
import cn.wey.rxweycode.ui.fragment.MainDataFragment;
import cn.wey.rxweycode.ui.fragment.WelfareFragment;

/**
 * 首页
 */
public class MainActivity extends BaseActivity {

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.tool_bar)
    Toolbar toolbar;
    @Bind(R.id.nav_view)
    NavigationView navigationView;

    HomeListFragment homeListFragment;
    DataListFragment videoFragment;
    WelfareFragment welfareFragment;
    MainDataFragment mainDataFragment;
    private FragmentManager fragmentManager;
    private int menuIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        initViews();
    }

    private void initViews() {
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeButtonEnabled(true);
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_white);
        ab.setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(navigationView);
        setTitle(R.string.String_all);
        switchMenu(0); //初始化展示首页
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.menu_about:
                startActivity(new Intent(baseActivity, AboutActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItemClick(menuItem);
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        setTitle(menuItem.getTitle());
                        return true;
                    }
                });
    }

    /**
     * MenuItem点击
     *
     * @param menuItem
     */
    private void menuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_daily:
                onClickIndex(0);
                break;
            case R.id.nav_other:
                onClickIndex(1);
                break;
            case R.id.nav_welfare:
                onClickIndex(2);
                break;
            case R.id.nav_video:
                onClickIndex(3);
                break;
            default:
                break;
        }
    }

    private void onClickIndex(int index) {
        if (menuIndex != index) {
            switchMenu(index);
            menuIndex = index;
        }
    }

    private void switchMenu(int index) {
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                if (homeListFragment == null) {
                    // 如果HomeFragment为空，则创建一个并添加到界面上
                    homeListFragment = new HomeListFragment(ApiConfig.DATA_TYPE_WELFARE);
                    transaction.add(R.id.main_fragment_content, homeListFragment);
                } else {
                    // 如果HomeFragment不为空，则直接将它显示出来
                    transaction.show(homeListFragment);
                }
                break;
            case 1:
                if (mainDataFragment == null) {
                    mainDataFragment = new MainDataFragment();
                    transaction.add(R.id.main_fragment_content, mainDataFragment);
                } else {
                    transaction.show(mainDataFragment);
                }
                break;
            case 2:
                if (welfareFragment == null) {
                    welfareFragment = new WelfareFragment(ApiConfig.DATA_TYPE_WELFARE);
                    transaction.add(R.id.main_fragment_content, welfareFragment);
                } else {
                    transaction.show(welfareFragment);
                }
                break;
            case 3:
                if (videoFragment == null) {
                    videoFragment = new DataListFragment(ApiConfig.DATA_TYPE_REST_VIDEO);
                    transaction.add(R.id.main_fragment_content, videoFragment);
                } else {
                    transaction.show(videoFragment);
                }
                break;

            default:
                break;
        }
        transaction.commit();
    }

    /**
     * 隐藏fragment
     *
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (homeListFragment != null) {
            transaction.hide(homeListFragment);
        }
        if (mainDataFragment != null) {
            transaction.hide(mainDataFragment);
        }
        if (videoFragment != null) {
            transaction.hide(videoFragment);
        }
        if (welfareFragment != null) {
            transaction.hide(welfareFragment);
        }
    }

}
