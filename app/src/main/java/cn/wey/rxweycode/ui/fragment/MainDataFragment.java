package cn.wey.rxweycode.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.wey.rxweycode.R;
import cn.wey.rxweycode.api.ApiConfig;
import cn.wey.rxweycode.ui.BaseFragment;

/**
 * Created by wey on 2016/5/10.
 */
public class MainDataFragment extends BaseFragment {

    @Bind(R.id.tab_layout)
    TabLayout tabLayout;

    @Bind(R.id.view_pager)
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_viewpager, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        FragmentAdapter adapter = new FragmentAdapter(((FragmentActivity) baseActivity).getSupportFragmentManager());
        adapter.addFragment(new DataListFragment(ApiConfig.DATA_TYPE_ANDROID), getString(R.string.String_android));
        adapter.addFragment(new DataListFragment(ApiConfig.DATA_TYPE_IOS), getString(R.string.String_ios));
        adapter.addFragment(new DataListFragment(ApiConfig.DATA_TYPE_JS), getString(R.string.String_js));
        adapter.addFragment(new DataListFragment(ApiConfig.DATA_TYPE_EXTEND_RESOURCES), getString(R.string.String_resource));
        adapter.addFragment(new DataListFragment(ApiConfig.DATA_TYPE_APP), getString(R.string.String_app));
        adapter.addFragment(new DataListFragment(ApiConfig.DATA_TYPE_RECOMMEND), getString(R.string.String_recommend));
        viewPager.setOffscreenPageLimit(adapter.getCount());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    static class FragmentAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
