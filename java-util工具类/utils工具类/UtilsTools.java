package com.zhang.utils.utils;

/**
 * Created by ZhangRuxing on 2017-06-09.
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Vibrator;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.InputType;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.zhang.zrx_utils.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

/**
 * 系统工具
 * <p>
 * <p>
 * 获得程序版本VersionName
 * 获得versionCode
 * 比较版本号
 * 安装APK文件
 * <p>
 * 检查网络是否可用
 * 验证身份证号码
 * 验证手机格式
 * 验证密码格式
 * 验证4位长度的验证码格式
 * 检查SD卡是否正常挂载
 * 判断GPS数据是否不等于零
 * 获取移动设备存储卡的路径
 * 判断sdcard上的文件是否可以删除
 * 字符串对比是否完全相同(两个, 区分/不区分大小写)
 * <p>
 * 隐藏手机号中间四位
 * 隐藏键盘
 * String 转 UTF-8编码
 * HashMap转Json
 * 价格转换 (String->Double) (String->Int)
 * 解决ScrollView嵌套ListView只显示一行
 * MD5
 * 网络请求传参数时获取签名
 * <p>
 * Log提示
 * Toast提示
 * 设置屏幕显示progressDialog
 * 返回结果关闭ProgressDialog
 * 通用dialog 带取消和确定按钮
 * 通用dialog 带一个按钮
 * 按返回键弹出的框
 * 拨打电话 Dialog
 * 拨打电话 不带Dialog
 * 发短信
 * 系统默认响铃
 * 系统震动 (多个, 根据情况选择)
 * <p>
 * bitmap比例压缩
 * bitmap质量压缩
 * 加载本地图片
 * 压缩指定图片
 * 获得图片角度
 * 按角度旋转图片
 * 获得真实路径的Uri 用于选择图片返回
 * 裁剪图片
 * <p>
 * 获取指定位置指定类型的文件
 * 获得图片资源
 * 从资源字符串加载图片资源ID
 * 获取资源文件的字符 getResources().getString(resId)
 * 获得定义在array中的内容 return String[]
 * 获得定义在array中的内容 return int[]
 * 日期时间选择器 (多个)
 * 获取当前时间 @return 毫秒值
 * 获取当前时间 @return %y%m%d%H%M%S  (161202170521)
 * 获取当前时间 @return 2012/07/07 12:12:01
 * 将字符串转为时间戳 (多个, 看此方法前后的)
 * 日期转时间戳 (多个, 用时搜索一下)
 * 日期之差
 * 毫秒时间转 xx日xx时xx分
 * 毫秒时间戳转日期
 * 毫秒时间戳转日期 yyyy-MM-dd HH:mm:ss
 * 将时间戳转为代表"距现在多久之前"的字符串
 * 获取随机String
 * 把Map的value 转成 List
 * 把Map的key 转成 List
 * String转成Map
 * <p>
 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
 * 将px值转换为sp值，保证文字大小不变
 * 将sp值转换为px值，保证文字大小不变
 * 获得设备屏幕大小 DM
 * 获取当前屏幕的宽
 * 获取当前屏幕的高
 * 获取状态栏高度
 * argb (多个)
 * <p>
 * <p>
 * 其他待定方法 :
 * encodeTable
 * getAbsolutePath
 * getDataColumn
 * isExternalStorageDocument
 * isDownloadsDocument
 * isMediaDocument
 * isGooglePhotosUri
 * toRoundBitmap
 * getDeviceInfo
 * pickImageUri
 */
public class UtilsTools {
     public static ProgressDialog progressDialog;
//    private static final String LOG_TAG = "Tools";

     //private static boolean isDebug = false;
     public static boolean isDebug = true;

     /**
     * 当前版本号或包名
     */
     /**
     * 返回当前程序版本名
     */
//    public static int getAppVersionName(Context context) {
//        String versionName = "";
//        int versioncode = 0;
//        try {
//            // ---get the package info---
//            PackageManager pm = context.getPackageManager();
//            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
//            versionName = pi.versionName;
//            versioncode = pi.versionCode;
//            if (versionName == null || versionName.length() <= 0) {
//                return -1;
//            }
//        } catch (Exception e) {
//            Log.e("VersionInfo", "Exception", e);
//        }
//        return versioncode;
//    }

     /**
     * 验证身份证号码
     */
     public static boolean personIdValidation(String text) {
        String regx = "[0-9]{17}[Xx]" ;
        String reg1 = "[0-9]{15}" ;
        String regex = "[0-9]{18}" ;
         return text.matches(regx) || text.matches(reg1) || text.matches(regex);
    }

     /**
     * 隐藏手机号中间四位
     *
     * @param mobile
     * @return
     */
     public static String HideMobile(String mobile) {
        StringBuilder sb = new StringBuilder();
         if ( ! TextUtils.isEmpty(mobile) && mobile.length() > 6 ) {

             for ( int i = 0 ; i < mobile.length(); i ++ ) {
                 char c = mobile.charAt(i);
                 if (i > = 3 && i < = 6 ) {
                    sb.append( '*' );
                } else {
                    sb.append(c);
                }
            }

        }
         return sb.toString();
    }

     /**
     * 消息Toast提示
     *
     * @param context
     * @param msg
     */
     public static void MsgBox(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

     /**
     * 消息Toast提示 (LENGTH_LONG)
     *
     * @param context
     * @param msg
     */
     public static void MsgBox_Long(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

     /**
     * 获得程序版本VersionName
     *
     * @param context
     * @return
     */
     public static String GetAppVersion(Context context) {
        String strVersionName = "" ;

        String strPack = context.getPackageName();
         try {
            PackageInfo packInfo = context.getPackageManager().getPackageInfo(strPack, 0 );

            strVersionName = packInfo.versionName;

        } catch (NameNotFoundException e) {

            e.printStackTrace();
        }

         return strVersionName;
    }

     /**
     * 获得程序版本代码versionCode
     *
     * @param context
     * @return
     */
     public static int GetAppVersionCode(Context context) {
         int versionCode = 0 ;

        String strPack = context.getPackageName();
         try {
            PackageInfo packInfo = context.getPackageManager().getPackageInfo(strPack, 0 );

            versionCode = packInfo.versionCode;

        } catch (NameNotFoundException e) {

            e.printStackTrace();
        }

         return versionCode;
    }

     /**
     * 比较版本号
     *
     * @param serverCode x.x.x
     * @param appCode    x.x.x
     * @return true when serverCode > appCode
     */
     public static boolean CompareAppVersion(String serverCode, String appCode) {

         boolean brt = false;

        String[] server = serverCode.split( "\\." );
        String[] app = appCode.split( "\\." );

         if (server != null && app != null && server.length == app.length) {

             for ( int i = 0 ; i < server.length; i ++ ) {
                 if (Integer.valueOf(server[i]) > Integer.valueOf(app[i])) {

                    brt = true;
                }
            }
        }

         return brt;
    }


     /**
     * 安装APK文件
     */
     public static void installApk(Activity context, String dirPath, String name) {

        File apkfile = new File(dirPath, name);
         if ( ! apkfile.exists()) {
             return ;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse( "file://" + apkfile.toString()), "application/vnd.android.package-archive" );
        context.startActivity(i);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

     /**
     * 获取资源文件的字符
     *
     * @param context
     * @param resId
     * @return
     */
     public static String getStringValue(Context context, int resId) {
         return context.getResources().getString(resId);
    }

     /**
     * 获得设备屏幕大小DM
     *
     * @param context
     * @return
     */
     private static DisplayMetrics getDisplayMetrics(Context context) {
         // Context.getResources().getDisplayMetrics()依赖于手机系统，获取到的是系统的屏幕信息；
         // WindowManager.getDefaultDisplay().getMetrics(dm)是获取到Activity的实际屏幕信息。
        WindowManager winMgr = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        winMgr.getDefaultDisplay().getMetrics(dm);
         // System.out.println("heigth : " + dm.heightPixels);
         // System.out.println("width : " + dm.widthPixels);
         return dm;
    }

     /**
     * 获得图片资源的相关
     *
     * @param id       图片资源ID
     * @param iconSize [out]:nSize[0]表示高度，nSize[1]表示宽度
     */
     public static void GetIconSize(Context context, int id, int [] iconSize) {
        Resources res = context.getResources();

        BitmapDrawable bitmapDrawable = (BitmapDrawable) res.getDrawable(id); // R.drawable.my_success_mark_two
        Bitmap bmBitmap = bitmapDrawable.getBitmap();

        iconSize[ 0 ] = bmBitmap.getHeight();
        iconSize[ 1 ] = bmBitmap.getWidth();
    }

     /**
     * 获取当前屏幕的宽
     *
     * @param context
     * @return
     */
     public static int getCurScreenWidth(Context context) {
         return getDisplayMetrics(context).widthPixels;
    }

     /**
     * 获取当前屏幕的高
     *
     * @param context
     * @return
     */
     public static int getCurScreenHeight(Context context) {
         return getDisplayMetrics(context).heightPixels;
    }

     /**
     * 获取状态栏高度
     *
     * @param mContext
     * @return
     */
     public static int getdecorViewHeight(Activity mContext) {
        Rect frame = new Rect();
        mContext.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
         return frame.top;

    }

     /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
     public static int dip2px(Context context, float dpValue) {
         final float scale = context.getResources().getDisplayMetrics().density;
         return ( int ) (dpValue * scale + 0 . 5f );
    }

     /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
     public static int px2dip(Context context, float pxValue) {
         final float scale = context.getResources().getDisplayMetrics().density;
         return ( int ) (pxValue / scale + 0 . 5f );
    }

     /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param context
     * @param pxValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
     public static int px2sp(Context context, float pxValue) {
         final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
         return ( int ) (pxValue / fontScale + 0 . 5f );
    }

     /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param context
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
     public static int sp2px(Context context, float spValue) {
         final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
         return ( int ) (spValue * fontScale + 0 . 5f );
    }

     /**
     * check if network avalable
     * 检查网络是否可用
     *
     * @param context
     * @return
     */
     public static boolean isNetWorkConnected(Context context) {
         if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
             if (mConnectivityManager == null) {
                 return false;
            }
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
             if (mNetworkInfo != null) {
                 return mNetworkInfo.isAvailable() && mNetworkInfo.isConnected();
            }
        }

         return false;
    }


     /**
     * 检查SD卡是否正常挂载
     *
     * @return
     */
     public static boolean isSdcardExist() {
         // MEDIA_MOUNTED : SD卡正常挂载
         return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

     /**
     * 按角度旋转图片
     *
     * @param resource
     * @param resId
     * @param degree
     * @return
     */
     public static Bitmap rotateIcon(Resources resource, int resId, float degree) {
        Bitmap bitOrg = BitmapFactory.decodeResource(resource, resId);
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap bitNew = Bitmap.createBitmap(bitOrg, 0 , 0 , bitOrg.getWidth(), bitOrg.getHeight(), matrix, false);
         return bitNew;
    }

     /**
     * 隐藏键盘
     *
     * @param context
     * @param view
     */
     public static void hideSoftKeyBorad(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0 );
    }

     /**
     * 从资源字符串加载图片资源ID
     *
     * @param context  Activity对象指针
     * @param strArray 资源文件id（在Res-->value中定义的<string-array>资源
     */
     public static int [] fetchDrawsResId(Context context, int strArray) {

        Resources res = context.getResources();
        String strPackageName = context.getPackageName();
        String[] strIcons = res.getStringArray(strArray);

         int nSize = strIcons.length;

         if (nSize > 0 ) {
             int [] iconsId = new int [nSize];

             for ( int i = 0 ; i < nSize; i ++ ) {
                iconsId[i] = res.getIdentifier(strIcons[i], "drawable" , strPackageName);
            }
             return iconsId;
        }

         return null;
    }


     /**
     * 获得定义在array中的内容
     *
     * @param context
     * @param arrayId
     * @return String[]
     */
     public static String[] getArrContent(Context context, int arrayId) {
        Resources res = context.getResources();
        String[] arrName = res.getStringArray(arrayId);
         return arrName;
    }

     /**
     * 获得定义在array中的内容
     *
     * @param context
     * @param arrayId
     * @return int[]
     */
     public static int [] getArrContent_int(Context context, int arrayId) {
        Resources res = context.getResources();
         int [] arrName = res.getIntArray(arrayId);
         return arrName;
    }

     /**
     * 拨打电话
     *
     * @param context
     * @param strTelNum
     */
//    public static void Call(Context context, String strTelNum) {
//        try {
//            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + strTelNum));
//            context.startActivity(intent);
//
//        } catch (Exception e) {
//        }
//    }

     /**
     * 拨打电话 Dialog
     *
     * @param mContext
     * @param phone
     */
     public static void CallDialog( final Context mContext, final String phone) {

         try {
             new AlertDialog.Builder(mContext).setTitle( "拨打电话: " + phone)
                    .setPositiveButton( "拨打" , new DialogInterface.OnClickListener() {

                         public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse( "tel:" + phone));
                            mContext.startActivity(intent);
                        }
                    }).setNegativeButton( "取消" , null).show();
        } catch (Exception e) {

        }

    }

     /**
     * 通用dialog 带取消和确定按钮
     *
     * @param mContext
     * @param text      要显示的文字
     * @param activityB 要跳转到的Activity
     */
     public static void BaseDialog( final Context mContext, final String text, final Class < ? > activityB) {

         try {
             new AlertDialog.Builder(mContext).setTitle(text)
                    .setPositiveButton( "确定" , new DialogInterface.OnClickListener() {

                         public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(mContext, activityB);
                            mContext.startActivity(intent);
                        }
                    }).setNegativeButton( "取消" , null).show();
        } catch (Exception e) {

        }
    }

     /**
     * 通用dialog 带一个按钮
     *
     * @param mContext
     * @param buttonText 按钮文字
     * @param text       提示文字
     * @param activityB  点击那一个按钮后要跳转的页面(如果点击后不需要跳就传null)
     * @param extra1     from 跳转携带的参数1
     * @param extra2     report_id 跳转携带的参数2
     */
     public static void BaseDialog2( final Context mContext, final String buttonText, final String text, final Class < ? > activityB, final String extra1, final String extra2) {

         try {
            AlertDialog.Builder mDialog = new AlertDialog.Builder(mContext);
            mDialog.setTitle(text).setPositiveButton(buttonText, new DialogInterface.OnClickListener() {

                 public void onClick(DialogInterface dialog, int which) {
                     if (activityB != null) {
                        Intent intent = new Intent(mContext, activityB);
                         if (activityB == AppointDetailDevActivity. class ) {
                            intent.putExtra( "from" , extra1);
                            intent.putExtra( "report_id" , extra2);
                        }
                        mContext.startActivity(intent);
                    }
                }
            });
            mDialog.setCancelable(false);
            mDialog.create().show();
             /*.setNegativeButton("取消", null)*/
//            .show();
        } catch (Exception e) {

        }
    }

     /**
     * 发短信
     *
     * @param context
     * @param strBody
     */
     public static void SendSMS(Context context, String strBody) {

        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse( "sms:" ));
        intent.putExtra( "sms_body" , strBody);
        context.startActivity(intent);

    }

     /**
     * 获取当前时间
     *
     * @return 毫秒值
     */
     public static long GetCurrentTime() {
        Time time = new Time();
        time.setToNow();
//        toMillis(false)与toMillis（true）的区别：其区别在于是否支持夏令时。
         return time.toMillis(false);
    }

     /**
     * 获取当前时间
     *
     * @return %y%m%d%H%M%S  (161202170521)
     */
     public static String GetCurrentFormatTime() {
        Time time = new Time();
        time.setToNow();
         return time.format( "%y%m%d%H%M%S" );
    }

     /**
     * 获取当前时间 自定义格式时例：2012/07/07 12:12:01
     * 如果要2012/07/07 12:12格式的, 就 .substring(0, 14)
     *
     * @return
     */
     public static String GetCurrentFormatTime1() {

        Time time = new Time();
        time.setToNow();
         return time.format( "%y/%m/%d %H:%M:%S" );
//        return time.format("%y/%m/%d_%H:%M");
    }

     /**
     * 日期转时间戳
     *
     * @param strDate
     * @param simpleFormate
     * @return
     */
     public static long FormateStringDateToLong(String strDate, String simpleFormate) {
        Date date = null;
         long dateLong = 0 ;
        SimpleDateFormat sdf = new SimpleDateFormat(simpleFormate);
         try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
         if (date != null) {
            dateLong = date.getTime();
        }
         return dateLong;
    }


     /**
     * 日期转时间戳 "yyyy-MM-dd HH:mm"
     *
     * @param strDate
     * @return
     */
     public static long FormateStringDateToLong1(String strDate) {

         return FormateStringDateToLong(strDate, "yyyy-MM-dd HH:mm" );
    }

     /**
     * 日期转时间戳 "yyyy-MM-dd"
     *
     * @param strDate
     * @return
     */
     public static long FormateStringDateToLong2(String strDate) {

         return FormateStringDateToLong(strDate, "yyyy-MM-dd" );
    }

     /**
     * 日期之差
     *
     * @return
     */
     public static long getDataExplan(String start, String end) {
        Date date_start = null;
        Date date_end = null;
         long dateLong = 0 ;
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy年MM月dd日HH:mm" );
         try {
            date_start = sdf.parse(start);
            date_end = sdf.parse(end);
        } catch (ParseException e) {
             // TODO Auto-generated catch block
            e.printStackTrace();
        }
         if (date_start != null && date_end != null) {
            dateLong = date_end.getTime() - date_start.getTime();
        }
         return dateLong;
    }

     /**
     * 毫秒时间转 xx日xx时xx分
     */
     public static String miSecondToDay( long mss) {

         long days = mss / ( 1000 * 60 * 60 * 24 );
         long hours = (mss % ( 1000 * 60 * 60 * 24 )) / ( 1000 * 60 * 60 );
         long minutes = (mss % ( 1000 * 60 * 60 )) / ( 1000 * 60 );
         // long seconds = (mss % (1000 * 60)) / 1000;

         return days + "天" + hours + "小时" + minutes + "分" ;

    }

     /**
     * 毫秒时间戳转日期
     */
     public static String FormateTimeStempToString( long timeStemp, String simpleFormate) {
        SimpleDateFormat sdf = new SimpleDateFormat(simpleFormate);
         return sdf.format(timeStemp);
    }

     /**
     * 毫秒时间戳转日期 yyyy-MM-dd HH:mm:ss
     */
     public static String FormateTimeStempToString1( long timeStemp) {
         return FormateTimeStempToString(timeStemp, "yyyy-MM-dd HH:mm:ss" );
    }


     /**
     * 判断GPS数据是否不等于零
     */
     public static boolean IsGpsDataValid( double dx, double dy) {

         boolean bResult = false;

         if ((dx > = 0 . 00000001 || dx < = - 0 . 00000001 ) && (dy > = 0 . 00000001 || dy < = - 0 . 00000001 )) {

            bResult = true;
        }
         return bResult;
    }

     /**
     * 加载本地图片
     *
     * @param url
     * @return
     */
     public static Bitmap getLoacalBitmap(String url) {
         try {
            FileInputStream fis = new FileInputStream(url);
             return BitmapFactory.decodeStream(fis); // /把流转化为Bitmap图片

        } catch (FileNotFoundException e) {
            e.printStackTrace();
             return null;
        }
    }

     /**
     * 压缩指定图片
     *
     * @param path ,倍率,压缩比 0-1 ,质量0-100
     * @return
     */
     public static boolean CompressLoacalBitmap(String path, int size, float rate, int quality) {

         boolean brt = false;

        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, opts);

        opts.inSampleSize = size;
        opts.inJustDecodeBounds = false;

         try {

            Bitmap bitMap = BitmapFactory.decodeFile(path, opts);

             int width = bitMap.getWidth();
             int height = bitMap.getHeight();

             float scaleWidth = rate;
             float scaleHeight = rate;

             int degree = readPictureDegree(path);

            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            matrix.postRotate(degree);

            bitMap = Bitmap.createBitmap(bitMap, 0 , 0 , width, height, matrix, true);

            FileOutputStream out = new FileOutputStream(path);
            bitMap.compress(CompressFormat.JPEG, quality, out);

            out.flush();
            out.close();
            bitMap.recycle();

            brt = true;

        } catch (Exception e) {
            e.printStackTrace();

        } catch (OutOfMemoryError err) {

        }
         return brt;

    }

     /**
     * 获得图片角度
     *
     * @param path
     * @return
     */
     public static int readPictureDegree(String path) {
         int degree = 0 ;
         try {
            ExifInterface exifInterface = new ExifInterface(path);
             int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
             switch (orientation) {
                 case ExifInterface.ORIENTATION_ROTATE_90 :
                    degree = 90 ;
                     break ;
                 case ExifInterface.ORIENTATION_ROTATE_180 :
                    degree = 180 ;
                     break ;
                 case ExifInterface.ORIENTATION_ROTATE_270 :
                    degree = 270 ;
                     break ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
         return degree;
    }

     /**
     * 系统默认响铃
     *
     * @param context
     * @return
     */
     public static int PlaySound( final Context context) {
        NotificationManager mgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification nt = new Notification();
        nt.defaults = Notification.DEFAULT_SOUND;
         int soundId = new Random(System.currentTimeMillis()).nextInt(Integer.MAX_VALUE);
        mgr.notify(soundId, nt);
         return soundId;

    }

     /**
     * 系统震动
     *
     * @param context
     * @param milliseconds
     */
     public static void Vibrate(Context context, long milliseconds) {
        Vibrator vib = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(milliseconds);
    }

     /**
     * 系统震动
     *
     * @param context
     * @param pattern
     * @param isRepeat 1:表示重复震动-1表示不重?
     */
     public static void Vibrate(Context context, long [] pattern, boolean isRepeat) {
        Vibrator vib = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(pattern, isRepeat ? 1 : - 1 );
    }

     /**
     * 判断sdcard上的文件是否可以删除
     */
     public static boolean canDeleteSDFile( long time, int days_before) {
         boolean canDel = false;
        Calendar calendar = Calendar.getInstance();
         // 删除x天之前日志信
        calendar.add(Calendar.DAY_OF_MONTH, - 1 * days_before);
        Date expiredDate = calendar.getTime();
         // 获得文件的最新时
        calendar.setTimeInMillis(time);
        Date lastDate = calendar.getTime();
         // 判断文件的最新时间是否是x天前
        canDel = lastDate.before(expiredDate);
         return canDel;
    }

     /**
     * argb
     *
     * @param rgba
     * @return
     */
     public static int rgba2argb( int rgba) {
         int argb = ((rgba << 24 ) & 0xFF000000) | ((rgba >> 8 ) & 0xFF);
         return argb;
    }

     /**
     * argb
     *
     * @param bgr
     * @return
     */
     public static int bgr2argb( int bgr) {
         int r = ((bgr & 0xFF) << 16 );
         int g = (bgr & 0xFF00);
         int b = ((bgr & 0xFF0000) >> 16 );
         int argb = (0xFF << 24 ) | r | g | b;
         return argb;
    }

     /**
     * argb
     *
     * @param argb
     * @return
     */
     public static int argb2bgr( int argb) {
         int r = ((argb >> 16 ) & 0xFF);
         int g = (argb & 0xFF00);
         int b = ((argb & 0xFF) << 16 );
         int bgr = r | g | b;
         return bgr;
    }

     private static final char [] encodeTable = new char []{ 'A' , 'B' , 'C' , 'D' , 'E' , 'F' , 'G' , 'H' , 'I' , 'J' , 'K' , 'L' ,
             'M' , 'N' , 'O' , 'P' , 'Q' , 'R' , 'S' , 'T' , 'U' , 'V' , 'W' , 'X' , 'Y' , 'Z' , '0' , '1' , '2' , '3' , '4' , '5' , '6' ,
             '7' , '8' , '9' };

     /**
     * 获取随机String
     *
     * @param len
     * @return
     */
     public static String getRandomString( int len) {
        String returnStr = "" ;
         char [] ch = new char [len];
        Random rd = new Random();
         for ( int i = 0 ; i < len; i ++ ) {
            ch[i] = ( char ) (rd.nextInt( 9 ) + 65 );
            ch[i] = encodeTable[rd.nextInt( 36 )];
        }
        returnStr = new String(ch);
         return returnStr;
    }

//    private static final String MAC_NAME = "HmacSHA1";
//    private static final String ENCODING = "UTF-8";
//    public static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception {
//        byte[] data = encryptKey.getBytes(ENCODING);
//        // 根据给定的字节数组构造一个密第二参数指定��密钥算法的名
//        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
//        // 生成��指定 Mac 算法 Mac 对象
//        Mac mac = Mac.getInstance(MAC_NAME);
//        // 用给定密钥初始化 Mac 对象
//        mac.init(secretKey);
//
//        byte[] text = encryptText.getBytes(ENCODING);
//        // 完成 Mac 操作
//        return mac.doFinal(text);
//    }

     /**
     * String 转UTF-8编码
     */
     public static String getUTF8XMLString(String xml) {
         // A StringBuffer Object
        StringBuffer sb = new StringBuffer();
        sb.append(xml);
        String xmString = "" ;
        String xmlUTF8 = "" ;
         try {
            xmString = new String(sb.toString().getBytes( "UTF-8" ));
            xmlUTF8 = URLEncoder.encode(xmString, "UTF-8" );
            System.out.println( "utf-8 编码" + xmlUTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
         return xmlUTF8;
    }

     /**
     * 获取移动设备存储卡的路径
     *
     * @return
     */
     public static String getExterPath() {
        String sdcard_path = "" ;
         // 得到路径
         try {
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec( "mount" );
            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            String line;
            BufferedReader br = new BufferedReader(isr);
             while ((line = br.readLine()) != null) {
                 if (line.contains( "secure" ))
                     continue ;
                 if (line.contains( "asec" ))
                     continue ;

                 if (line.contains( "fat" )) {
                    String columns[] = line.split( " " );
                     if (columns != null && columns.length > 1 ) {
                        sdcard_path = columns[ 1 ];
                    }
                } else if (line.contains( "fuse" )) {
                    String columns[] = line.split( " " );
                     if (columns != null && columns.length > 1 ) {
                        sdcard_path = columns[ 1 ];
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         return sdcard_path;
    }

     /***
     * 获取指定位置指定类型的文件
     *
     * @param path 指定位置
     * @param type 指定类型
     * @return
     */
     public static List < String > getOptTypeFileOnOptPath(String path, String type) {
        List < String > pathList = new ArrayList < String > ();
        File fileFolder = new File(path);
         if ( ! fileFolder.isDirectory()) {
             return null;
        } else {
            File[] files = fileFolder.listFiles();
             for (File file : files) {
                 if (file.isFile()) {
                    String strPath = file.getAbsolutePath();
                     int nTypeSize = type.length();
                    String strExpend = strPath.substring(strPath.length() - nTypeSize, strPath.length());
                     boolean bCompare = false;
                    bCompare = strExpend.equalsIgnoreCase(type);
                     if (bCompare) {
                        pathList.add(strPath);
                    }
                }
            }
        }
         return pathList;
    }

     /**
     * 验证手机格式
     */
     public static boolean isMobileNO(String mobiles) {
         /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188、147
       * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
       * 总结起来就是第一位必定为1，第二位必定为3或5或7或8(新加4, 6)，其他位置的可以为0-9
       */
        String telRegex = "[1][345678]\\d{9}" ; // "[1]"代表第1位为数字1，"[3578]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
         if (TextUtils.isEmpty(mobiles))
             return false;
         else
             return mobiles.matches(telRegex);
    }

     /**
     * 验证密码格式
     */
     public static boolean isPwd(String pwd) {
         // 8-16位. 16位已在maxLength中显示
         // 后来改成了6-16位. 已改
         if (pwd.length() < 6 ) {
             return false;
        } else {
             return true;
        }
    }

     /**
     * 验证4位长度的验证码格式
     */
     public static boolean isSmsCode(String code) {
         if (code.length() != 4 ) {
             return false;
        } else {
             return true;
        }
    }

     /**
     * 字符串对比是否完全相同(区分大小写)
     *
     * @param cs1
     * @param cs2
     * @return
     */
     public static boolean string_Equals(CharSequence cs1, CharSequence cs2) {
         return cs1 == cs2 ? true : (cs1 != null && cs2 != null ? (cs1 instanceof String && cs2 instanceof String ? cs1.equals(cs2) : regionMatches(cs1, false, 0 , cs2, 0 , Math.max(cs1.length(), cs2.length()))) : false);
    }

     /**
     * 字符串对比是否完全相同(不区分大小写)
     *
     * @param
     * @param
     * @return
     */
     public static boolean string_equalsIgnoreCase(CharSequence str1, CharSequence str2) {
         return str1 != null && str2 != null ? (str1 == str2 ? true : (str1.length() != str2.length() ? false : regionMatches(str1, true, 0 , str2, 0 , str1.length()))) : str1 == str2;
    }

     /**
     * 字符串对比包含的方法
     *
     * @param cs
     * @param ignoreCase
     * @param thisStart
     * @param substring
     * @param start
     * @param length
     * @return
     */
     public static boolean regionMatches(CharSequence cs, boolean ignoreCase, int thisStart, CharSequence substring, int start, int length) {
         if (cs instanceof String && substring instanceof String) {
             return ((String) cs).regionMatches(ignoreCase, thisStart, (String) substring, start, length);
        } else {
             int index1 = thisStart;
             int index2 = start;
             int tmpLen = length;

             while (tmpLen -- > 0 ) {
                 char c1 = cs.charAt(index1 ++ );
                 char c2 = substring.charAt(index2 ++ );
                 if (c1 != c2) {
                     if ( ! ignoreCase) {
                         return false;
                    }

                     if (Character.toUpperCase(c1) != Character.toUpperCase(c2) && Character.toLowerCase(c1) != Character.toLowerCase(c2)) {
                         return false;
                    }
                }
            }

             return true;
        }
    }

     /**
     * 将字符串转为时间戳
     */
     public static String String_to_Timestamp(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy年MM月dd日HH时mm分ss秒" );
        Date d;
         try {
            d = sdf.parse(user_time);
             long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring( 0 , 10 );
        } catch (ParseException e) {
             // TODO Auto-generated catch block
            e.printStackTrace();
        }
         return re_time;
    }

     /**
     * yyyy年MM月dd日HH:mm
     *
     * @param user_time
     * @return
     */
     public static String String_to_Timestamp2(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy年MM月dd日HH:mm" );
        Date d;
         try {
            d = sdf.parse(user_time);
             long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring( 0 , 10 );
        } catch (ParseException e) {
             // TODO Auto-generated catch block
            e.printStackTrace();
        }
         return re_time;
    }

     /**
     * yyyy-MM-dd HH:mm:ss
     *
     * @param user_time
     * @return
     */
     public static String String_to_Timestamp4(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        Date d;
         try {
            d = sdf.parse(user_time);
             long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring( 0 , 10 );
        } catch (ParseException e) {
            e.printStackTrace();
        }
         return re_time;
    }

     /**
     * yyyy-MM-dd
     *
     * @param user_time
     * @return
     */
     public static String String_to_Timestamp3(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        Date d;
         try {
            d = sdf.parse(user_time);
             long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring( 0 , 10 );
        } catch (ParseException e) {
            e.printStackTrace();
        }
         return re_time;
    }


     /**
     * 获得真实路径的Uri 用于选择图片返回
     *
     * @param context
     * @param
     * @return uri
     */
     public static Uri getRealUri(Activity context, Intent data) {
         if (Build.VERSION.SDK_INT < 19 ) {

             return data.getData();
        } else {
            String path = getAbsolutePath(context, data.getData());
             return Uri.fromFile( new File(path));
        }
    }

    @SuppressLint( "NewApi" )
     public static String getAbsolutePath( final Context context, final Uri uri) {

         final boolean isKitKat = Build.VERSION.SDK_INT > = Build.VERSION_CODES.KITKAT;

         // DocumentProvider
         if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
             // ExternalStorageProvider
             if (isExternalStorageDocument(uri)) {
                 final String docId = DocumentsContract.getDocumentId(uri);
                 final String[] split = docId.split( ":" );
                 final String type = split[ 0 ];

                 if ( "primary" .equalsIgnoreCase(type)) {
                     return Environment.getExternalStorageDirectory() + "/" + split[ 1 ];
                }

                 // TODO handle non-primary volumes
            }
             // DownloadsProvider
             else if (isDownloadsDocument(uri)) {
                 final String id = DocumentsContract.getDocumentId(uri);
                 final Uri contentUri = ContentUris.withAppendedId(Uri.parse( "content://downloads/public_downloads" ),
                        Long.valueOf(id));

                 return getDataColumn(context, contentUri, null, null);
            }
             // MediaProvider
             else if (isMediaDocument(uri)) {
                 final String docId = DocumentsContract.getDocumentId(uri);
                 final String[] split = docId.split( ":" );
                 final String type = split[ 0 ];

                Uri contentUri = null;
                 if ( "image" .equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ( "video" .equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ( "audio" .equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                 final String selection = "_id=?" ;
                 // MediaStore.Images.Media._ID + "=?"
                 final String[] selectionArgs = new String[]{split[ 1 ]};

                String path = getDataColumn(context, contentUri, selection, selectionArgs);
                 return path;
            }
        }
         // MediaStore (and general)
         else if ( "content" .equalsIgnoreCase(uri.getScheme())) {

             // Return the remote address
             if (isGooglePhotosUri(uri))
                 return uri.getLastPathSegment();

             return getDataColumn(context, uri, null, null);
        }
         // File
         else if ( "file" .equalsIgnoreCase(uri.getScheme())) {
             return uri.getPath();
        }

         return null;
    }

     public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String column = MediaStore.Images.Media.DATA;
        String[] projection = {column};
         try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
             if (cursor != null && cursor.moveToFirst()) {
                 int index = cursor.getColumnIndexOrThrow(column);
                 return cursor.getString(index);
            }
        } finally {
             if (cursor != null)
                cursor.close();
        }
         return null;
    }

     /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
     public static boolean isExternalStorageDocument(Uri uri) {
         return "com.android.externalstorage.documents" .equals(uri.getAuthority());
    }

     /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
     public static boolean isDownloadsDocument(Uri uri) {
         return "com.android.providers.downloads.documents" .equals(uri.getAuthority());
    }

     /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
     public static boolean isMediaDocument(Uri uri) {
         return "com.android.providers.media.documents" .equals(uri.getAuthority());
    }

     /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
     public static boolean isGooglePhotosUri(Uri uri) {
         return "com.google.android.apps.photos.content" .equals(uri.getAuthority());
    }

     /**
     * MD5
     *
     * @param content
     * @return
     */
     public static String getMD5(String content) {
         try {
            MessageDigest digest = MessageDigest.getInstance( "MD5" );
            digest.update(content.getBytes());
             return getHashString(digest);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
         return null;
    }

     public static String getHashString(MessageDigest digest) {
        StringBuilder builder = new StringBuilder();
         for ( byte b : digest.digest()) {
            builder.append(Integer.toHexString((b >> 4 ) & 0xf));
            builder.append(Integer.toHexString(b & 0xf));
        }
         return builder.toString();

    }

     public static Bitmap toRoundBitmap(Bitmap bitmap) {
         int width = bitmap.getWidth();
         int height = bitmap.getHeight();
         float roundPx;
         float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
         if (width < = height) {
            roundPx = width / 2 ;
            top = 0 ;
            bottom = width;
            left = 0 ;
            right = width;
            height = width;
            dst_left = 0 ;
            dst_top = 0 ;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2 ;
             float clip = (width - height) / 2 ;
            left = clip;
            right = width - clip;
            top = 0 ;
            bottom = height;
            width = height;
            dst_left = 0 ;
            dst_top = 0 ;
            dst_right = height;
            dst_bottom = height;
        }
        Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
         final int color = 0xff424242;
         final Paint paint = new Paint();
         final Rect src = new Rect(( int ) left, ( int ) top, ( int ) right, ( int ) bottom);
         final Rect dst = new Rect(( int ) dst_left, ( int ) dst_top, ( int ) dst_right, ( int ) dst_bottom);
         final RectF rectF = new RectF(dst);
        paint.setAntiAlias(true);
        canvas.drawARGB( 0 , 0 , 0 , 0 );
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode( new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);
         return output;
    }

     public static String getDeviceInfo(Context context) {
         try {
            org.json.JSONObject json = new org.json.JSONObject();
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);

            String device_id = tm.getDeviceId();

            android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);

            String mac = wifi.getConnectionInfo().getMacAddress();
            json.put( "mac" , mac);

             if (TextUtils.isEmpty(device_id)) {
                device_id = mac;
            }

             if (TextUtils.isEmpty(device_id)) {
                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
            }

            json.put( "device_id" , device_id);

             return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
         return null;
    }

     /**
     * 裁剪图片
     * 默认800px 1：1的图片
     *
     * @param activity
     * @param from
     * @param to
     * @param Request
     */
     public static void cropImageUri(Activity activity, Uri from, Uri to, int Request) {

         try {
            cropImageUri(activity, from, to, 1 , 1 , 800 , 800 , Request);

        } catch (Exception e) {

            MsgBox(activity, "系统不支持裁剪" );
             // activity.setResult(Constants.REQUEST.CROP_IMG);
        }

    }

     // 上方裁剪图片方法有调用
     public static void cropImageUri(Activity activity, Uri from, Uri to, int aspectX, int aspectY, int outputX,
                                     int outputY, int Request) {

        Intent intent = new Intent( "com.android.camera.action.CROP" );
        intent.setDataAndType(from, "image/*" );
        intent.putExtra( "crop" , "true" );
        intent.putExtra( "aspectX" , aspectX);
        intent.putExtra( "aspectY" , aspectY);
        intent.putExtra( "outputX" , outputX);
        intent.putExtra( "outputY" , outputY);
        intent.putExtra( "scale" , true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, to);
        intent.putExtra( "return-data" , false);
        intent.putExtra( "outputFormat" , CompressFormat.JPEG.toString());
        intent.putExtra( "noFaceDetection" , true); // no face detection
        activity.startActivityForResult(intent, Request);
    }

    @SuppressLint( "InlinedApi" )
     public static void pickImageUri(Activity context, int Request) {

        Intent intent = new Intent();

         if (Build.VERSION.SDK_INT < 19 ) {
            intent.setType( "image/*" );
            intent.setAction(Intent.ACTION_GET_CONTENT);
        } else {
            intent.setType( "image/*" );
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        }

        context.startActivityForResult(intent, Request);
    }

     /**
     * 设置屏幕显示progressDialog
     *
     * @param context
     * @param msg
     */
     public static void setProgressDialog(Context context, String msg) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage(msg);
        progressDialog.show();
    }

     /**
     * 返回结果关闭ProgressDialog
     *
     * @param context
     */
     public static void closeProgressDialog(Context context) {
        progressDialog.dismiss();
    }

     /**
     * bitmap比例压缩
     *
     * @param image
     * @return
     */
     public static Bitmap GetBitmap(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(CompressFormat.JPEG, 100 , baos);
         if (baos.toByteArray().length / 1024 > 1024 ) { // 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset(); // 重置baos
            image.compress(CompressFormat.JPEG, 50 , baos); // 这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
         // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
         int w = newOpts.outWidth;
         int h = newOpts.outHeight;
         // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
         float hh = 800f ; // 这里设置高度为800f
         float ww = 480f ; // 这里设置宽度为480f
         // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
         int be = 1 ; // be=1表示不缩放
         if (w > h && w > ww) { // 如果宽度大的话根据宽度固定大小缩放
            be = ( int ) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) { // 如果高度高的话根据宽度固定大小缩放
            be = ( int ) (newOpts.outHeight / hh);
        }
         if (be < = 0 )
            be = 1 ;
        newOpts.inSampleSize = be; // 设置缩放比例
         // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
         return compressImage(bitmap); // 压缩好比例大小后再进行质量压缩
    }

     /**
     * bitmap质量压缩
     *
     * @param image
     * @return
     */
     public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(CompressFormat.JPEG, 100 , baos); // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
         int options = 100 ;
         while (baos.toByteArray().length / 1024 > 100 ) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(CompressFormat.JPEG, options, baos); // 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10 ; // 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray()); // 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null); // 把ByteArrayInputStream数据生成图片
         return bitmap;
    }

     /**
     * 将时间戳转为代表"距现在多久之前"的字符串
     *
     * @param timeStr 时间戳
     * @return
     */
     public static String getStandardDate(String timeStr) {

        StringBuffer sb = new StringBuffer();

         long t = Long.parseLong(timeStr);

         long time = System.currentTimeMillis() - (t * 1000 );

         long mill = ( long ) Math.ceil(time / 1000 ); // 秒前

         long minute = ( long ) Math.ceil(time / 60 / 1000 .0f); // 分钟前

         long hour = ( long ) Math.ceil(time / 60 / 60 / 1000 .0f); // 小时

         long day = ( long ) Math.ceil(time / 24 / 60 / 60 / 1000 .0f); // 天前

         if (day - 1 > 0 ) {
             return FormateTimeStempToString1(Long.valueOf(timeStr));
        } else if (hour - 1 > 0 ) {

             if (hour > = 24 ) {
                 return FormateTimeStempToString1(Long.valueOf(timeStr));
            } else {
                sb.append(hour + "小时" );
            }

        } else if (minute - 1 > 0 ) {

             if (minute == 60 ) {
                sb.append( "1小时" );
            } else {
                sb.append(minute + "分钟" );
            }

        } else if (mill - 1 > 0 ) {

             if (mill == 60 ) {
                sb.append( "1分钟" );
            } else {
                sb.append(mill + "秒" );
            }

        } else {
            sb.append( "刚刚" );
        }

         if ( ! sb.toString().equals( "刚刚" )) {
            sb.append( "前" );
        }

         return sb.toString();
    }

     /**
     * 按返回键弹出的框
     *
     * @param context
     */
     public static void ExitDialog( final Context context) {

        AlertDialog.Builder build = new AlertDialog.Builder(context);
        build.setMessage( "确定退出？" );
        build.setPositiveButton( "退出" , new DialogInterface.OnClickListener() {

            @Override
             public void onClick(DialogInterface arg0, int arg1) {

                ((Activity) context).finish();
            }
        }).setNegativeButton( "取消" , null).show();

    }

     /**
     * 价格转换 (String->Double)
     *
     * @param money
     * @return
     */
     public static double StringMoneytoDouble(String money) {

         return Double.valueOf(money.replace( "," , "" ));
    }

     /**
     * 价格转换 (String->Int)
     *
     * @param money
     * @return
     */
     public static int StringMoneytoInt(String money) {
         int m = 0 ;
         if (money.contains( "." )) {
            m = Integer.valueOf(money.substring( 0 , money.lastIndexOf( "." )).replace( "," , "" ));
        } else {
            m = Integer.valueOf(money.replace( "," , "" ));
        }

         return m;
    }

     /**
     * 解决ScrollView嵌套ListView只显示一行
     */
     public static void setListViewHeightBasedOnChildren(ListView listView) {
         // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
         if (listAdapter == null) {
             return ;
        }

         int totalHeight = 0 ;
         for ( int i = 0 , len = listAdapter.getCount(); i < len; i ++ ) {
             // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
             // 计算子项View 的宽高
            listItem.measure( 0 , 0 );
             // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1 ));
         // listView.getDividerHeight()获取子项间分隔符占用的高度
         // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

     /**
     * 网络请求传参数时获取签名
     *
     * @param map         原map
     * @param API_URL     后缀
     * @param accessToken
     * @return 添加上签名等信息的最终map
     */
     public static TreeMap < String, String > addSignature(TreeMap < String, String > map, String API_URL, String accessToken) {
         try {
            String stringForApi = Get_Sha1_Md5.stringForApi( "POST" , API_URL, Get_Sha1_Md5.key_sort(map), accessToken);
            String sha1 = Get_Sha1_Md5.sha1(stringForApi);
            String signature = Get_Sha1_Md5.md5(sha1.substring( 0 , 32 ));
            map.put( "signature" , signature); // 签名
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            Log_d( "UtilsTools" , "签名失败" );
        }
         return map;
    }

     /**
     * 日期时间选择器
     *
     * @param context
     * @param tv1     要赋值的TextView
     */
     public static void chooseTimeData(Context context, final TextView tv1) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = View.inflate(context, R.layout.dialog_choose_time, null);
         final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
        datePicker.setFocusable(true);

         final TimePicker timePicker = (android.widget.TimePicker) view.findViewById(R.id.time_picker);
        builder.setView(view);

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);

        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(cal.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(Calendar.MINUTE);

         final int inType = tv1.getInputType();
        tv1.setInputType(InputType.TYPE_NULL);
        tv1.setInputType(inType);

//        builder.setTitle("选取日期和时间");
        builder.setPositiveButton( "确  定" , new DialogInterface.OnClickListener() {
            @Override
             public void onClick(DialogInterface dialog, int which) {
                StringBuffer sb = new StringBuffer();
                sb.append(String.format( "%d-%02d-%02d" ,
                        datePicker.getYear(),
                        datePicker.getMonth() + 1 ,
                        datePicker.getDayOfMonth()));
                 //sb.append(" ");
                 // 经测试,为了防止分钟和小时为个位数时显示为5而不是05,拼接个"0"
                String mHour = timePicker.getCurrentHour() + "" ;
                String mMinute = timePicker.getCurrentMinute() + "" ;
                 if (timePicker.getCurrentHour() < 10 ) {
                    mHour = "0" + timePicker.getCurrentHour();
                }
                 if (timePicker.getCurrentMinute() < 10 ) {
                    mMinute = "0" + timePicker.getCurrentMinute();
                }
                 // sb.append(mHour).append(":").append(mMinute);
                tv1.setText(sb);
                dialog.cancel();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

     /**
     * 日期时间选择器
     *
     * @param context
     * @param tv1     要赋值的TextView
     */
     public static void chooseTime(Context context, final TextView tv1) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = View.inflate(context, R.layout.dialog_choose_time, null);
         final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
        datePicker.setFocusable(true);

         final TimePicker timePicker = (android.widget.TimePicker) view.findViewById(R.id.time_picker);
        builder.setView(view);

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);

        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(cal.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(Calendar.MINUTE);

         final int inType = tv1.getInputType();
        tv1.setInputType(InputType.TYPE_NULL);
        tv1.setInputType(inType);

//        builder.setTitle("选取日期和时间");
        builder.setPositiveButton( "确  定" , new DialogInterface.OnClickListener() {
            @Override
             public void onClick(DialogInterface dialog, int which) {
                StringBuffer sb = new StringBuffer();
                sb.append(String.format( "%d-%02d-%02d" ,
                        datePicker.getYear(),
                        datePicker.getMonth() + 1 ,
                        datePicker.getDayOfMonth()));
                sb.append( " " );
                 // 经测试,为了防止分钟和小时为个位数时显示为5而不是05,拼接个"0"
                String mHour = timePicker.getCurrentHour() + "" ;
                String mMinute = timePicker.getCurrentMinute() + "" ;
                 if (timePicker.getCurrentHour() < 10 ) {
                    mHour = "0" + timePicker.getCurrentHour();
                }
                 if (timePicker.getCurrentMinute() < 10 ) {
                    mMinute = "0" + timePicker.getCurrentMinute();
                }
                sb.append(mHour).append( ":" ).append(mMinute);
                tv1.setText(sb);
                dialog.cancel();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

     /**
     * HashMap转Json
     *
     * @param map 原map
     * @return string类型的json
     */
     public static String hashMapToJson(HashMap map) {
        String string = "{" ;
         for (Iterator it = map.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry e = (Map.Entry) it.next();
            string += "\"" + e.getKey() + "\":" ;
            string += "\"" + e.getValue() + "\"," ;
        }
        string = string.substring( 0 , string.lastIndexOf( "," ));
        string += "}" ;
         return string;
    }

     public static String treeMapToJson(TreeMap map) {
        String string = "{" ;
         for (Iterator it = map.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry e = (Map.Entry) it.next();
            string += "\"" + e.getKey() + "\":" ;
            string += "\"" + e.getValue() + "\"," ;
        }
        string = string.substring( 0 , string.lastIndexOf( "," ));
        string += "}" ;
         return string;
    }

     //透传消息  将对象转为Json
     public static String ObjectToJson(_TcMessage tcMessage) {
        JSONObject jsonObject = new JSONObject();
         try {
            jsonObject.put( "mAgentHxId" , tcMessage.getmAgentHxId());
            jsonObject.put( "mAgentNickName" , tcMessage.getmAgentNickName());
            jsonObject.put( "mAgentIconUrl" , tcMessage.getmAgentIconUrl());

            jsonObject.put( "mDevHxId" , tcMessage.getmDevHxId());
            jsonObject.put( "mDevNickName" , tcMessage.getmDevNickName());
            jsonObject.put( "mDevIconUrl" , tcMessage.getmDevIconUrl());

            jsonObject.put( "mChatBuildName" , tcMessage.getmChatBuildName());
            jsonObject.put( "mBuildId" , tcMessage.getmBuildId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String json = jsonObject.toString();
         return json;
    }

     /**
     * String转成Map
     * Json 转成 Map<>
     *
     * @param jsonStr
     * @return
     */
     public static TreeMap < String, String > StringToMap(String jsonStr) {
        TreeMap map = new TreeMap();
        JSONObject jsonObject;
         try {
            jsonObject = new JSONObject(jsonStr);

            Iterator < String > keyIter = jsonObject.keys();
            String key;
            Object value;
//            Map<String, Object> valueMap = new HashMap<String, Object>();
             while (keyIter.hasNext()) {
                key = keyIter.next();
                value = jsonObject.get(key);
                map.put(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log_e( "UtilsTools" , "StringToMap --- ERROR --- " + e.getMessage());
        }
         return map;
    }

     /**
     * 把Map的key 转成 List
     *
     * @param map
     * @return
     */
     public static List < String > MapKeyToList(HashMap < String, String > map) {

        List < String > firstList = new ArrayList < > ();

        HashMap < String, String > hashMap = map;
        Iterator i = hashMap.entrySet().iterator();
         while (i.hasNext()) {
            Entry < String, String > entry = (Entry) i.next();
            firstList.add(entry.getKey());
        }

         return firstList;
    }

     /**
     * JSONArray转换成List
     *
     * @param jsonArray
     * @return
     */
     public static List < String > JsonArrayToList(JSONArray jsonArray) {
        List < String > firstList = new ArrayList < > ();

         for ( int i = 0 ; i < jsonArray.length(); i ++ ) {
             try {
                String s = jsonArray.get(i).toString();
                firstList.add(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
         return firstList;
    }

     /**
     * 把Map的value 转成 List
     *
     * @param map
     * @return
     */
     public static List < String > MapValueToList(HashMap < String, String > map) {

        List < String > firstList = new ArrayList < > ();

        HashMap < String, String > hashMap = map;
        Iterator i = hashMap.entrySet().iterator();
         while (i.hasNext()) {
            Entry < String, String > entry = (Entry) i.next();
            firstList.add(entry.getValue());
        }

         return firstList;
    }

     /**
     * Log提示
     */
     public static void Log_d(String tag, String msg) {
         if (isDebug) {
            Log.d(tag, msg);
        }
    }

     public static void Log_e(String tag, String msg) {
         if (isDebug) {
            Log.e(tag, msg);
        }
    }

     public static void Log_d(Object object, String msg) {
         if (isDebug) {
            Log.d(object.getClass().getSimpleName(), msg);
        }
    }

     public static void Log_e(Object object, String msg) {
         if (isDebug) {
            Log.e(object.getClass().getSimpleName(), msg);
        }
    }
}