package com.chaoge.utils;

/**
 * Created by ZhangRuxing on 2017-11-24.
 * <p>
 * 获取当前执行位置的文件名/类名/方法名/行号 工具类
 */

public class CurrentLineInfo {

//通过静态方法调用Thread.currentThread().getStackTrace()获取当前函数栈时, 已多加了两层方法调用.

//Main.main() --> CurrentLineInfo.getLineNumber() --> Thread.getStackStrace()

     private static int originStackIndex = 3 ;

     public static String getFileName() {

         return Thread.currentThread().getStackTrace()[originStackIndex].getFileName();

    }

     public static String getClassName() {

         return Thread.currentThread().getStackTrace()[originStackIndex].getClassName();

    }

     public static String getMethodName() {

         return Thread.currentThread().getStackTrace()[originStackIndex].getMethodName();

    }

     public static int getLineNumber() {

         return Thread.currentThread().getStackTrace()[originStackIndex].getLineNumber();

    }

}
