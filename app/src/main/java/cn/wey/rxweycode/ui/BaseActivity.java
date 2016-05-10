package cn.wey.rxweycode.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.thefinestartist.finestwebview.FinestWebView;

import cn.wey.rxweycode.R;
import cn.wey.rxweycode.util.AppManager;
import cn.wey.rxweycode.util.UIHelper;

/**
 * Created by wey on 2016/3/25.
 */
public class BaseActivity extends AppCompatActivity {

    protected BaseActivity baseActivity;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseActivity = BaseActivity.this;
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissProgressDialog();
        AppManager.getAppManager().finishActivity(this);
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

    protected void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = UIHelper.createProgressDialog(baseActivity, null, null, false);
        }
        progressDialog.show();
    }

    protected void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

}
