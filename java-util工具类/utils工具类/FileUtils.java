package com.zhang.jnhj.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.util.Log;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileUtils {

    /**
     * 第一种方法：
     * 将输入的用户名和密码保存在这个应用的某个文件中
     *
     * @param context Activity的上面的某一层是Context,所以传值过来的是一个Activity,此处可以写成Context
     * @param name    输入的用户名
     * @param pass    输入的密码
     */
    public static void saveToFile(Context context, String name, String pass) {
        File dir = context.getFilesDir(); //查找这个应用下的所有文件所在的目录  
        FileWriter writer;
        try {
            writer = new FileWriter(dir.getAbsolutePath() + "/userinfo.txt");
            writer.append(name + "," + pass);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从这个应用下的文件中读取保存的用户名和密码,再次登录时自动显示在输入框中
     *
     * @param context
     * @param tname
     * @param tpass
     */
    public static void readFromFile(Context context, EditText tname, EditText tpass) {
        File dir = context.getFilesDir();//目录为：/data/data/com.etc.login/files  
        FileReader reader;
        try {
            reader = new FileReader(dir.getAbsolutePath() + "/userinfo.txt");
            BufferedReader breader = new BufferedReader(reader);
            String line = breader.readLine();
            String strs[] = line.split(",");
            tname.setText(strs[0]);
            tpass.setText(strs[1]);
            breader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 第二种方法：
     * 将输入的用户名和密码保存到sdcard中
     *
     * @param context
     * @param name
     * @param pass
     */
    public static void savtToSDCard(Context context, String name, String pass) {
        File sdcardDir = Environment.getExternalStorageDirectory();
        Log.d("mytag", sdcardDir.toString());//目录为：/storage/emulated/0
        /**
         * 但是使用cmd工具查找文件时，不再这个目录下，而是在/mnt/sdcard目录下 
         *
         */
        FileWriter writer;
        try {
            writer = new FileWriter(sdcardDir.getAbsolutePath() + "/userinfo.txt");
            writer.append(name + "," + pass);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从sdcard中读取保存的用户名和密码
     *
     * @param context
     * @param tname
     * @param tpass
     */
    public static void readFromSDCard(Context context, EditText tname, EditText tpass) {
        File sdcardDir = Environment.getExternalStorageDirectory();
        FileReader reader;
        try {
            reader = new FileReader(sdcardDir.getAbsolutePath() + "/userinfo.txt");
            BufferedReader breader = new BufferedReader(reader);
            String line = breader.readLine();
            String strs[] = line.split(",");
            tname.setText(strs[0]);
            tpass.setText(strs[1]);
            breader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 第三种方法：
     * 使用文件api进行保存用户名和密码，不必得到对应的目录
     *
     * @param context
     * @param name
     * @param pass
     */
    public static void saveToFile2(Context context, String name, String pass) {
        try {
            FileOutputStream out = context.openFileOutput("userinfo2.txt", context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(out);
            writer.append(name + "," + pass);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用文件api进行读取保存的用户名和密码
     *
     * @param context
     * @param tname
     * @param tpass
     */
    public static void readFromFile2(Context context, EditText tname, EditText tpass) {
        try {
            FileInputStream in = context.openFileInput("userinfo2.txt");
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader breader = new BufferedReader(reader);
            String line = breader.readLine();
            String strs[] = line.split(",");
            tname.setText(strs[0]);
            tpass.setText(strs[1]);
            breader.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block  
            e.printStackTrace();
        }
    }

    /**
     * 最为通用的第四种方法：
     * 使用SharedPreference进行保存用户名和密码，可以实现拆分，不必手动的以，为分隔符进行拆分，
     * 如果用，进行拆分，那么一旦用户登录的时候输入了，号，程序就会出错，现在以Map的形式保存用户名和密码，
     * 就不用再担心这个问题
     *
     * @param context
     * @param name
     * @param pass
     */
    public static void saveToPre(Context context, String name, String pass) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("userinfo", context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putString("name", name);
        editor.putString("pass", pass);
        editor.commit();
    }

    /**
     * 使用SharedPreference进行读取保存的用户名和密码
     *
     * @param context
     * @param tname
     * @param tpass
     */
    public static void readFromPre(Context context, EditText tname, EditText tpass) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("userinfo", context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        String pass = sharedPreferences.getString("pass", "");
        tname.setText(name);
        tpass.setText(pass);
    }
}  