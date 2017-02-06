package com.rmtd.melo.floatingwindow;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Created by melo on 2017/2/6.
 */
public class SpeechReceiver extends BroadcastReceiver {
    private final String TAG = "SpeechReceiver";
    private WindowManager wm;
    WindowManager.LayoutParams params;
    MyHandler mHandler = new MyHandler(this);
//    Handler mHandler=new Handler();

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.i(TAG, "Broadcast:" + action);
        if (action.equals("speechcontent")) {
            Log.i(TAG, "收到语音文本并显示");


            wm = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
            params = new WindowManager.LayoutParams();
            params.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
            params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;

            final TextView tv = new TextView(context);
            tv.setBackgroundColor(Color.WHITE);
            tv.setTextColor(Color.BLACK);
            // 设置字体大小
            tv.setTextSize(25);
            tv.setText(intent.getStringExtra("content"));
            wm.addView(tv, params);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                        wm.removeView(tv);
                }
            }, 800);
        }
    }

    static class MyHandler extends Handler {
        private final WeakReference<SpeechReceiver> mReceiver;

        public MyHandler(SpeechReceiver receiver) {
            mReceiver = new WeakReference<SpeechReceiver>(receiver);
        }

        public void handleMessage(android.os.Message msg) {
            SpeechReceiver receiver = mReceiver.get();
            if (receiver != null) {
//                receiver.wm.removeView(tv);
            }
        }
    }


}
