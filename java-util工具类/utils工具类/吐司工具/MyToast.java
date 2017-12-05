package com.zhang.jnhj.utils;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhang.jnhj.R;


/**
 * Created by ZhangRuxing on 2014-05-26.
 * 吐司工具类
 */
public class MyToast extends Toast {

    /**
     * 图标状态 不显示图标
     */
    private static final int TYPE_HIDE = -1;
    /**
     * 图标状态 显示√
     */
    private static final int TYPE_TRUE = 0;
    /**
     * 图标状态 显示×
     */
    private static final int TYPE_FALSE = 1;

    /**
     * Toast单例
     */
    private static MyToast toast;

    /**
     * Toast所显示的图片
     */
    private static ImageView toast_img;

    /**
     * 构造
     *
     * @param context
     */
    public MyToast(Context context) {
        super(context);
    }

    /**
     * 显示一个纯文本吐司 短
     *
     * @param context 上下文
     * @param text    显示的文本
     */
    public static void show(Context context, CharSequence text) {
        showToast(context, text, Toast.LENGTH_SHORT, TYPE_HIDE);
    }

    /**
     * 显示一个纯文本吐司 长
     *
     * @param context 上下文
     * @param text    显示的文本
     */
    public static void showLong(Context context, CharSequence text) {
        showToast(context, text, Toast.LENGTH_LONG, TYPE_HIDE);
    }

    /**
     * 显示一个带图标的吐司 短
     *
     * @param context   上下文
     * @param text      显示的文本
     * @param isSucceed 显示【对号图标】还是【叉号图标】
     */
    public static void showImage(Context context, CharSequence text, boolean isSucceed) {
        showToast(context, text, Toast.LENGTH_SHORT, isSucceed ? TYPE_TRUE : TYPE_FALSE);
    }

    /**
     * 显示一个带图标的吐司 长
     *
     * @param context   上下文
     * @param text      显示的文本
     * @param isSucceed 显示【对号图标】还是【叉号图标】
     */
    public static void showImageLong(Context context, CharSequence text, boolean isSucceed) {
        showToast(context, text, Toast.LENGTH_LONG, isSucceed ? TYPE_TRUE : TYPE_FALSE);
    }

    /**
     * 显示Toast
     *
     * @param context 上下文
     * @param text    显示的文本
     * @param time    显示时长
     * @param imgType 图标状态
     */
    private static void showToast(Context context, CharSequence text, int time, int imgType) {
        // 初始化一个新的Toast对象
        initToast(context, text);

        // 设置显示时长
        if (time == Toast.LENGTH_LONG) {
            toast.setDuration(Toast.LENGTH_LONG);
        } else {
            toast.setDuration(Toast.LENGTH_SHORT);
        }

        // 判断图标是否该显示，显示√还是×
        if (imgType == TYPE_HIDE) {
            toast_img.setVisibility(View.GONE);
        } else {
            if (imgType == TYPE_TRUE) {
                toast_img.setBackgroundResource(R.drawable.toast_y);
            } else {
                toast_img.setBackgroundResource(R.drawable.toast_n);
            }
            toast_img.setVisibility(View.VISIBLE);
            ObjectAnimator.ofFloat(toast_img, "rotationY", 0, 360).setDuration(1700).start();
        }

        // 显示Toast
        toast.show();
    }

    /**
     * 初始化Toast
     *
     * @param context 上下文
     * @param text    显示的文本
     */
    private static void initToast(Context context, CharSequence text) {
        try {
            cancelToast();

            toast = new MyToast(context);
            // 获取LayoutInflater对象
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // 由layout文件创建一个View对象
            View layout = inflater.inflate(R.layout.toast_layout, null);

            // 吐司上的图片
            toast_img = (ImageView) layout.findViewById(R.id.toast_img);

            // 实例化ImageView和TextView对象
            TextView toast_text = (TextView) layout.findViewById(R.id.toast_text);
            toast_text.setText(text);
            toast.setView(layout);
            toast.setGravity(Gravity.CENTER, 0, 70);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏当前Toast
     */
    public static void cancelToast() {
        if (toast != null) {
            toast.cancel();
        }
    }

    public void cancel() {
        try {
            super.cancel();
        } catch (Exception e) {

        }
    }

    @Override
    public void show() {
        try {
            super.show();
        } catch (Exception e) {

        }
    }


    private static TextView textView;
    private static Toast toast1;

    /**
     * 自定义样式的吐司 短
     *
     * @param context
     * @param text
     */
    public static void showCustom(Context context, String text) {

        if (toast1 == null) { // 1. 创建前 2.消失后toast为null
            // 获取打气筒
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //创建视图
            View view = inflater.inflate(R.layout.item_toast_bg, null);
            textView = (TextView) view.findViewById(R.id.tv_toast_text);
            //创建土司
            toast1 = new Toast(context);
            //设置居中方式  默认在底部
            toast1.setGravity(Gravity.CENTER, 0, 0);//如果不设置剧中方式,使用系统默认的吐司位置
            //设置土司的持续时长
            toast1.setDuration(Toast.LENGTH_SHORT);
            //设置土司的背景View
            toast1.setView(view);
        }
        //设置土司的显示额内容
        textView.setText(text);
        toast1.show();
    }

}