package com.rmtd.melo.floatingwindow;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by duyanfeng on 2017/2/9.
 */
public class WindownManagerHelper {
    private static WindownManagerHelper instance;
    private WindowManager mWindowManager;

    private TextView tv;
    private ScrollView mScrollView;
    private StringBuilder newString = new StringBuilder();
    private Handler handler = new Handler();

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
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.format = PixelFormat.RGBA_8888;
//        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL; // 调整悬浮窗口至左侧侧中间
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        // 设置Window flag，下面的flag如果不设置那么，不能向下传递事件

        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//        params.x = 0;
//        params.y = 0;
        params.width = 400;
        params.height = 400;


        tv = new TextView(context);
        tv.setTextColor(Color.BLACK);
        // 设置字体大小
        tv.setTextSize(25);
        tv.setText("content");

        mScrollView = new ScrollView(context);
        mScrollView.setBackgroundColor(Color.BLUE);
        mScrollView.setVerticalScrollBarEnabled(true);
        mScrollView.addView(tv);

        mWindowManager.addView(mScrollView, params);


        //设置可拖动
        mScrollView.setOnTouchListener(new View.OnTouchListener() {

            int lastX, lastY;
            int paramX, paramY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = (int) event.getRawX();
                        lastY = (int) event.getRawY();
                        paramX = params.x;
                        paramY = params.y;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int dx = (int) event.getRawX() - lastX;
                        int dy = (int) event.getRawY() - lastY;
                        params.x = paramX + dx;
                        params.y = paramY + dy;
                        // 更新悬浮窗位置
                        mWindowManager.updateViewLayout(mScrollView, params);
                        break;
                }
                return false;
            }
        });
    }


    public void setSoundText(String soundText) {
        newString.append(soundText).append("\r\n");
        tv.setText(newString);
        scroll2Bottom(mScrollView, tv);
    }

    public void scroll2Bottom(final ScrollView scroll, final View inner) {

        handler.post(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                if (scroll == null || inner == null) {
                    return;
                }
                // 内层高度超过外层
                int offset = inner.getMeasuredHeight()
                        - scroll.getMeasuredHeight();
                if (offset < 0) {
                    System.out.println("定位...");
                    offset = 0;
                }
                scroll.scrollTo(0, offset);
            }
        });
    }

}
