package com.rmtd.melo.floatingwindow;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by duyanfeng on 2017/2/9.
 */
public class WindownManagerHelper {
    private static WindownManagerHelper instance;
    private WindowManager mWindowManager;

    private TextView tv;
    private StringBuilder newString = new StringBuilder();

    private WindownManagerHelper() {

    }

    public static WindownManagerHelper getInsrance() {
        if (instance == null) {
            instance = new WindownManagerHelper();
        }
        return instance;
    }


    public void init(Context context) {
        mWindowManager = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.format = PixelFormat.RGBA_8888;
        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL; // 调整悬浮窗口至左侧侧中间
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
//        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//        params.x = 0;
//        params.y = 0;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = 200;


        tv = new TextView(context);
        tv.setTextColor(Color.BLACK);
        // 设置字体大小
        tv.setTextSize(25);
        tv.setText("content");
        mWindowManager.addView(tv, params);
    }

    public void setSoundText(String soundText) {
//        newString.append(soundText).append("\r\n");
        tv.setText(soundText);
    }


}
