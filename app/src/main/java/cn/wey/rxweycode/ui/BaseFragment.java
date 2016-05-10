package cn.wey.rxweycode.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.thefinestartist.finestwebview.FinestWebView;

import cn.wey.rxweycode.R;

/**
 * Created by wey on 2016/3/25.
 */
public class BaseFragment extends Fragment {

    protected Activity baseActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseActivity = getActivity();
    }

    /**
     * 加载url
     *
     * @param url
     */
    protected void loadWebViewUrl(String url) {
        FinestWebView.Builder builder = new FinestWebView.Builder(baseActivity);
        builder.titleSize(32);
        builder.iconDefaultColor(getResources().getColor(R.color.white));
        builder.menuColor(getResources().getColor(R.color.white));
        builder.statusBarColor(getResources().getColor(R.color.style_color_primary));
        builder.showMenuRefresh(false);
        builder.show(url);
    }
}







