package com.zhang.jnhj.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.Base64;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import cz.msebera.android.httpclient.Header;

/**
 * Base64文件上传工具
 * Created by ZhangRuxing on 2017-09-19.
 */

public class UploadFileUtils {

    private static ProgressDialog progress;
    static String FileName = "";

    public static String getFileName() {
        return FileName;
    }

    public static void setFileName(String fileName) {
        FileName = fileName;
    }

    public static void uploadFile(final Context context, String filePath, String fileName, String type) {
        File file = new File(filePath);

        if (file.exists() && file.length() > 0) {
            try {
                if (getFileSize(file) / 1024 / 1024 > 10) {
                    MyToast.showLong(context, "您选择的文件>10M,请重新选择");
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            AsyncHttpClient client = new AsyncHttpClient();
            client.setTimeout(6 * 1000);
            RequestParams params = new RequestParams();

            try {
                FileInputStream in = new FileInputStream(file);
                byte[] buffer = new byte[(int) file.length() + 100];
                int length = in.read(buffer);
                String data = Base64.encodeToString(buffer, 0, length, Base64.DEFAULT);

                params.put("username", PrefUtils.getString(context, "userName", ""));
                params.put("FileName", fileName);
                params.put("imgBase64StringData", data);
                params.put("type", type);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                MyToast.show(context, "文件未找到");
                setFileName("");
            } catch (IOException e) {
                e.printStackTrace();
            }
            progress = new ProgressDialog(context);
            progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progress.setTitle("上传进度");
            progress.setCancelable(false);
            progress.show();

            client.post(Constants.UPLOADFILE_URL, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    try {
                        String result = new String(responseBody);
                        JSONArray jsonArray = new JSONArray(result);
                        JSONObject object = (JSONObject) jsonArray.get(0);
                        String fileName = object.getString("filename");
                        setFileName(fileName);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    MyToast.show(context, "上传成功");
                    progress.setProgress(0);
                    progress.dismiss();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    MyToast.show(context, "上传失败" + error.getMessage());
                    setFileName("");
                    progress.dismiss();
                }

                @Override
                public void onProgress(long bytesWritten, long totalSize) {
                    super.onProgress(bytesWritten, totalSize);
                    int count = (int) ((bytesWritten * 1.0 / totalSize) * 100);
                    // 上传进度显示
                    progress.setProgress(count);
                }
            });
        } else {
            Toast.makeText(context, "文件不存在", Toast.LENGTH_SHORT).show();
            setFileName("");
        }
    }

    //获取文件大小
    public static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
            file.createNewFile();
            Log.e("获取文件大小", "文件不存在!");
        }
        return size;
    }

}
