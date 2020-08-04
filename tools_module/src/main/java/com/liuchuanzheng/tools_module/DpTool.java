package com.liuchuanzheng.tools_module;

import android.util.TypedValue;

public class DpTool {

    public static int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                ApplicationUtil.getCurApplication().getResources().getDisplayMetrics());
    }

    public static int dip2px(float dipValue) {
        return (int) (dipValue * ApplicationUtil.getCurApplication().getResources().getDisplayMetrics().density + 0.5f);
    }

    public static int px2dp(float pxValue) {
        return (int) (pxValue / ApplicationUtil.getCurApplication().getResources().getDisplayMetrics().density + 0.5f);
    }

    public static int px2dip(float pxValue) {
        return (int) (pxValue / ApplicationUtil.getCurApplication().getResources().getDisplayMetrics().density + 0.5f);
    }

    public static int px2sp(float pxValue) {
        return (int) (pxValue / ApplicationUtil.getCurApplication().getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }

    public static float px2sp(int size) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, size,
                ApplicationUtil.getCurApplication().getResources().getDisplayMetrics());
    }

    public static int sp2px(float spValue) {
        return (int) (spValue * ApplicationUtil.getCurApplication().getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }

    public static float sp2px(int size) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, size,
                ApplicationUtil.getCurApplication().getResources().getDisplayMetrics());
    }
}
