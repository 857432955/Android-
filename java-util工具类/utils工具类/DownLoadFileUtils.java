package com.zhang.jnhj.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.zhang.jnhj.R;

import java.io.File;

import cz.msebera.android.httpclient.Header;

import static android.net.Uri.fromFile;

/**
 * Created by ZhangRuxing on 2017-09-20.
 */

public class DownLoadFileUtils {
    private static ProgressDialog progress;

    /**
     * @param url 要下载的文件URL
     * @throws Exception
     */
    public static void downloadFile(final Context mContext, String url) throws Exception {
        final String fileName = url.substring(url.lastIndexOf("\\") + 1);
        //下载之后存放的路径 获取SD卡的路径
        File file = new File(Environment.getExternalStorageDirectory() + "/com.zhang.jnhj/HJfile_recv/", fileName);
        AsyncHttpClient client = new AsyncHttpClient();
        progress = new ProgressDialog(mContext);
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setTitle("下载进度");
        progress.setCancelable(false);
        progress.show();
        client.get(url, new FileAsyncHttpResponseHandler(file) {
            @Override
            public void onSuccess(int statusCode, Header[] hander, File file) {
                if (statusCode == 200) {
                    progress.setProgress(0);
                    progress.dismiss();
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setCancelable(false);
                    builder.setTitle("下载完成");
                    builder.setIcon(R.drawable.ic_logo);
                    builder.setNegativeButton("取消", null);
                    builder.setPositiveButton("打开", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();
                            File file = new File(Environment.getExternalStorageDirectory() + "/com.zhang.jnhj/HJfile_recv/" + fileName);
                            Uri uri = null;
                            if (Build.VERSION.SDK_INT >= 24) {//判断版本大于等于7.0
                                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                intent.setAction(Intent.ACTION_VIEW);
                                uri = FileProvider.getUriForFile(mContext, mContext.getApplicationContext().getPackageName(), file);
                            } else {
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setAction(Intent.ACTION_VIEW);
                                uri = fromFile(file);
                            }

                            String type = getMIMEType(file);
                            //设置intent的data和Type属性。
                            //intent.setDataAndType(Uri.fromFile(file), type);
                            intent.setDataAndType(uri, type);
                            try {
                                mContext.startActivity(intent);
                            } catch (ActivityNotFoundException e) {
                                Toast.makeText(mContext, "您没有安装Office文件", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    builder.show();

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] hander, Throwable throwable, File file) {
                MyToast.show(mContext, "文件下载失败" + throwable.getMessage());
                MyLogger.i("文件下载失败", throwable.getMessage());
                throwable.printStackTrace();
                progress.dismiss();
            }

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                super.onProgress(bytesWritten, totalSize);
                int count = (int) ((bytesWritten * 1.0 / totalSize) * 100);
                //下载进度显示
                progress.setProgress(count);

            }

        });
    }


    /**
     * 根据文件后缀名匹配MIMEType
     *
     * @param file
     * @return
     */
    public static String getMIMEType(File file) {
        String type = "*/*";
        String name = file.getName();
        int index = name.lastIndexOf('.');
        if (index < 0) {
            return type;
        }
        String end = name.substring(index, name.length()).toLowerCase();
        //if (TextUtils.isEmpty(end)) return type;

        for (int i = 0; i < MIME_MapTable.length; i++) {
            if (end.equals(MIME_MapTable[i][0])) {
                type = MIME_MapTable[i][1];
            }
        }
        return type;
    }

    private static final String[][] MIME_MapTable = {
            {".3gp", "video/3gpp"},
            {".apk", "application/vnd.android.package-archive"},
            {".asf", "video/x-ms-asf"},
            {".avi", "video/x-msvideo"},
            {".bin", "application/octet-stream"},
            {".bmp", "image/bmp"},
            {".c", "text/plain"},
            {".class", "application/octet-stream"},
            {".conf", "text/plain"},
            {".cpp", "text/plain"},
            {".doc", "application/msword"},
            {".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},
            {".xls", "application/vnd.ms-excel"},
            {".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},
            {".exe", "application/octet-stream"},
            {".gif", "image/gif"},
            {".gtar", "application/x-gtar"},
            {".gz", "application/x-gzip"},
            {".h", "text/plain"},
            {".htm", "text/html"},
            {".html", "text/html"},
            {".jar", "application/java-archive"},
            {".java", "text/plain"},
            {".jpeg", "image/jpeg"},
            {".jpg", "image/jpeg"},
            {".js", "application/x-javascript"},
            {".log", "text/plain"},
            {".m3u", "audio/x-mpegurl"},
            {".m4a", "audio/mp4a-latm"},
            {".m4b", "audio/mp4a-latm"},
            {".m4p", "audio/mp4a-latm"},
            {".m4u", "video/vnd.mpegurl"},
            {".m4v", "video/x-m4v"},
            {".mov", "video/quicktime"},
            {".mp2", "audio/x-mpeg"},
            {".mp3", "audio/x-mpeg"},
            {".mp4", "video/mp4"},
            {".mpc", "application/vnd.mpohun.certificate"},
            {".mpe", "video/mpeg"},
            {".mpeg", "video/mpeg"},
            {".mpg", "video/mpeg"},
            {".mpg4", "video/mp4"},
            {".mpga", "audio/mpeg"},
            {".msg", "application/vnd.ms-outlook"},
            {".ogg", "audio/ogg"},
            {".pdf", "application/pdf"},
            {".png", "image/png"},
            {".pps", "application/vnd.ms-powerpoint"},
            {".ppt", "application/vnd.ms-powerpoint"},
            {".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"},
            {".prop", "text/plain"},
            {".rc", "text/plain"},
            {".rmvb", "audio/x-pn-realaudio"},
            {".rtf", "application/rtf"},
            {".sh", "text/plain"},
            {".tar", "application/x-tar"},
            {".tgz", "application/x-compressed"},
            {".txt", "text/plain"},
            {".wav", "audio/x-wav"},
            {".wma", "audio/x-ms-wma"},
            {".wmv", "audio/x-ms-wmv"},
            {".wps", "application/vnd.ms-works"},
            {".xml", "text/plain"},
            {".z", "application/x-compress"},
            {".zip", "application/x-zip-compressed"},
            {"", "*/*"}
    };
}