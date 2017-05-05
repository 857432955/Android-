package com.imscoket.lenovo.utils;

import android.os.Handler;

/**
 * Created by ZhangRuxing on 2016-11-09.
 */

public class ThreadUtils {
    public static void runInThread(Runnable runnable) {
        new Thread(runnable).start();
    }

    private static Handler handler=new Handler();
    public static void runUIThread(Runnable runnable) {
        handler.post(runnable);//new Message  sendMessage-->handleMessage(); r.run();
    }

}
