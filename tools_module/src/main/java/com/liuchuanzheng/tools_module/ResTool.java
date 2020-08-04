package com.liuchuanzheng.tools_module;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

/**
 * 获取资源文件
 */
public class ResTool {


    public static String getString(Context context, int resId) {
        return context.getApplicationContext().getString(resId);
    }


    public static String getString(Context context, int resId, Object... formatArgs) {
        return context.getApplicationContext().getString(resId, formatArgs);
    }


    public static String getStringFormat(Context context, int resId, Object... formatArgs) {
        return String.format(context.getApplicationContext().getResources().getString(resId), formatArgs);
    }


    public static int getColor(Context context, int resId) {
        return ContextCompat.getColor(context.getApplicationContext(), resId);
    }


    public static Drawable getDrawable(Context context, int resId) {
        return ContextCompat.getDrawable(context.getApplicationContext(), resId);
    }


    public static int getDimens(Context context, int resId) {
        return context.getApplicationContext().getResources().getDimensionPixelSize(resId);
    }
}
