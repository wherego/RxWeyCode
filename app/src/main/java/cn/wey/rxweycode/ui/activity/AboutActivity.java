package cn.wey.rxweycode.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.wey.rxweycode.R;
import cn.wey.rxweycode.api.ApiConfig;
import cn.wey.rxweycode.api.HttpMethods;
import cn.wey.rxweycode.model.EntityData;
import cn.wey.rxweycode.model.ResultData;
import cn.wey.rxweycode.ui.BaseActivity;
import cn.wey.rxweycode.ui.view.CommonListView;
import rx.Subscriber;

/**
 * Created by wey on 2016/4/25.
 */
public class AboutActivity extends BaseActivity {

    @Bind(R.id.tool_bar)
    Toolbar toolbar;

    @Bind(R.id.github_tv)
    TextView gitHubTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        initViews();
    }

    protected void initViews() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);
        setTitle(R.string.about);
        gitHubTv.setText(Html.fromHtml("https://github.com/skyway9647"));
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

    @OnClick(R.id.github_tv)
    public void onClickGitHub() {
        loadWebViewUrl("https://github.com/skyway9647");
    }
}
