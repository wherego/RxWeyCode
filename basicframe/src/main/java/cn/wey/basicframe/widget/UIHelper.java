package cn.wey.basicframe.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import cn.wey.basicframe.R;
import cn.wey.basicframe.app.AppContext;

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
    public static void makeDefaultToast(Context context, String text, boolean isCenter) {
        View view = LayoutInflater.from(context).inflate(R.layout.default_toast, null);
        TextView textView = (TextView) view.findViewById(R.id.toast_text);
        textView.setText(text);
        Toast toast = new Toast(context);
        if (isCenter) {
            toast.setGravity(Gravity.CENTER, 0, 0);
        }
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }

    /**
     * 显示默认toast
     * @param text
     */
    public static void showToast(String text) {
        makeDefaultToast(AppContext.getInstance(), text, false);
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


