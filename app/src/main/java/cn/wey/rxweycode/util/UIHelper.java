package cn.wey.rxweycode.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import cn.wey.rxweycode.RxWeyCodeApp;

/**
 * UI工具类
 * Created by wey on 2016/2/29.
 */
public class UIHelper {

    /**
     * 显示自定义的toast
     *
     * @param context
     * @param text
     */
    public static void makeDefaultToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示默认toast
     *
     * @param text
     */
    public static void showToast(String text) {
        makeDefaultToast(RxWeyCodeApp.getInstance(), text);
    }

    /**
     * 创建进程对话框,可以点击屏幕取消
     */
    public static ProgressDialog createProgressDialog(Context context,
                                                      String title,
                                                      String message,
                                                      boolean cancelable) {

        ProgressDialog dialog = new ProgressDialog(context);
        // 设置ProgressDialog 的进度条是否不明确
        dialog.setIndeterminate(true);
        // 设置ProgressDialog 是否可以按退回按键取消
        dialog.setCancelable(true);
        dialog.setInverseBackgroundForced(false);
        // 设置按其他边缘可以取消
        dialog.setCanceledOnTouchOutside(cancelable);
        dialog.setTitle(title);
        dialog.setMessage(message);
        return dialog;
    }

    public static ProgressBar createProgressBar(Context context) {
        ProgressBar progressBar = new ProgressBar(context);
        progressBar.setIndeterminate(true);
        return progressBar;
    }
}


