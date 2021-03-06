package com.lost.temple.transportation.entity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StatFs;

import com.lost.temple.transportation.util.HttpUtil;
import com.lost.temple.transportation.util.MyToast;
import com.lost.temple.transportation.util.PackageInfoUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.InputStream;
import java.lang.Exception;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ZhangRuxing on 2017-05-31.
 */

public class CheckVersionTask implements Runnable {
     private static final int UPDATE = 0 ;
     private static final int NOTUPDATE = 1 ;
     private static final int ERROR = 2 ;
     private final Context context;
     private ProgressDialog pd;
    InputStream is;
     private Update info;

     public CheckVersionTask(Context context) {
         this .context = context;
    }

    @Override
     public void run() {

         try {
            URL url = new URL(HttpUtil.URL_update);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod( "GET" );
            conn.setConnectTimeout( 5000 );
             int code = conn.getResponseCode();
             if (code == 200 ) {
                is = conn.getInputStream();

                info = UpdateInfoParser.getUpdataInfo(is);
                 if ( ! info.getVersion().equals(PackageInfoUtils.getPackageVersion(context))) {
                    Message msg = Message.obtain();
                    msg.what = UPDATE;
                    mHandler.sendMessage(msg);
                } else {
                    Message msg = Message.obtain();
                    msg.what = NOTUPDATE;
                    mHandler.sendMessage(msg);
                }
            } else {
                Message msg = Message.obtain();
                msg.what = ERROR;
                mHandler.sendMessage(msg);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Message msg = Message.obtain();
            msg.what = ERROR;
            mHandler.sendMessage(msg);
        }
    }

    Handler mHandler = new Handler() {
        @Override
         public void handleMessage(Message msg) {
             switch (msg.what) {
                 case UPDATE :
                    showUpdateDialog();
                     break ;
                 case NOTUPDATE :
                    MyToast.show(context, "已是最新版本" );
                     break ;
                 case ERROR :
                    MyToast.show(context, "请检查网络连接" );
                     break ;
            }
        }
    };

     public void showUpdateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle( "发现新版本" );
        builder.setMessage(info.getDisplayMessage()); //更新内容
        builder.setPositiveButton( "马上更新" , new android.content.DialogInterface.OnClickListener() {
            @Override
             public void onClick(android.content.DialogInterface dialogInterface, int i) {
                pd = new ProgressDialog(context);
                pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pd.show();
                File sdDir = Environment.getExternalStorageDirectory();
                File file = new File(sdDir, "Transportation.apk" );
                 if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    RequestParams requestParams = new RequestParams(info.getDownloadURL());
                    requestParams.setSaveFilePath(String.valueOf(file));
                    x.http().get(requestParams, new Callback.ProgressCallback < File > () {

                        @Override
                         public void onSuccess(File result) {
                            pd.dismiss();
                            MyToast.show(context, "下载成功" );
                             //覆盖安装apk
                            Intent intent = new Intent();
                            intent.setAction( "android.intent.action.VIEW" );
                            intent.addCategory( "android.intent.category.DEFAULT" );
                            intent.setDataAndType(Uri.fromFile(result), "application/vnd.android.package-archive" );
                            context.startActivity(intent);
                        }

                        @Override
                         public void onLoading( long total, long current, boolean isDownloading) {
                             long sdSize = getAvailSpace(Environment.getExternalStorageDirectory().getAbsolutePath()); //外部存储大小
                            pd.setMax(( int ) total);
                            pd.setProgress(( int ) current);
                             if (sdSize < = 0 ) {
                                MyToast.show(context, "存储空间不够\n剩余存储空间为：" + sdSize + "M" );
                                pd.dismiss();
                            }
                        }

                        @Override
                         public void onError(Throwable ex, boolean isOnCallback) {
                            MyToast.show(context, "下载失败" );
                            ex.printStackTrace();
                            pd.dismiss();
                        }

                        @Override
                         public void onCancelled(CancelledException cex) {

                        }

                        @Override
                         public void onFinished() {

                        }

                        @Override
                         public void onWaiting() {
                        }

                        @Override
                         public void onStarted() {
                        }

                    });
                } else {
                    MyToast.show(context, "SD卡不可用，不能自动更新" );
                }
            }
        });
        builder.setNegativeButton( "稍后再说" , new android.content.DialogInterface.OnClickListener() {
            @Override
             public void onClick(android.content.DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

     /**
     * 获取某个目录的可用空间
     *
     * @param path
     * @return
     */
     public long getAvailSpace(String path) {
        StatFs statfs = new StatFs(path);
         long size = statfs.getBlockSizeLong(); //获取分区的大小
         long count = statfs.getAvailableBlocksLong(); //获取可用分区块的个数
         return (size * count) / 1024 / 1024 ;
    }
}
