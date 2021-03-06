package com.zhang.utils.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * 跟网络相关的工具类
 */
public class NetUtils {
     private NetUtils() {
         /* cannot be instantiated */
         throw new UnsupportedOperationException( "cannot be instantiated" );
    }

     /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
     public static boolean isConnected(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);

         if (null != connectivity) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
             if (null != info && info.isConnected()) {
                 if (info.getState() == NetworkInfo.State.CONNECTED) {
                     return true;
                }
            }
        }
         return false;
    }

     /**
     * 判断是否是wifi连接
     */
     public static boolean isWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
         if (wifiNetworkInfo.isConnected()) {
             return true;
        }
         return false;
    }

     /**
     * 打开网络设置界面
     */
     public static void openSetting(Activity activity) {
         if (android.os.Build.VERSION.SDK_INT > 10 ) {
            activity.startActivity( new Intent(android.provider.Settings.ACTION_SETTINGS));
        } else {
            activity.startActivity( new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
        }
    }

}
