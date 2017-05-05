package com.jwty.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 包信息工具类
 * Created by ZhangRuxing on 2016-12-17.
 */

public class PackageInfoUtils {
    /**
     * 获取应用程序apk包的版本信息
     *
     * @param context 上下文
     * @return
     */
    public static String getPackageVersion(Context context) {//没有使用成员变量 写成静态的 方便调用
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            String version = packageInfo.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "解析版本号失败";
        }
    }
}
