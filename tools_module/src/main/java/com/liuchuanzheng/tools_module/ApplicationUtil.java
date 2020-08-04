package com.liuchuanzheng.tools_module;

import android.app.Application;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * @author 刘传政
 * @date 2020/8/2 20:54
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class ApplicationUtil {
    /**
     * 获取当前应用的Application
     * 先使用ActivityThread里获取Application的方法，如果没有获取到，
     * 再使用AppGlobals里面的获取Application的方法
     *
     * @return
     */
    public static Application getCurApplication() {
        Application application = null;
        try {
            Class atClass = Class.forName("android.app.ActivityThread");
            Method currentApplicationMethod = atClass.getDeclaredMethod("currentApplication");
            currentApplicationMethod.setAccessible(true);
            application = (Application) currentApplicationMethod.invoke(null);
            Log.d("ApplicationUtil", "curApp class1:" + application);
        } catch (Exception e) {
            Log.d("ApplicationUtil", "e:" + e.toString());
        }

        if (application != null)
            return application;

        try {
            Class atClass = Class.forName("android.app.AppGlobals");
            Method currentApplicationMethod = atClass.getDeclaredMethod("getInitialApplication");
            currentApplicationMethod.setAccessible(true);
            application = (Application) currentApplicationMethod.invoke(null);
            Log.d("ApplicationUtil", "curApp class2:" + application);
        } catch (Exception e) {
            Log.d("ApplicationUtil", "e:" + e.toString());
        }

        return application;
    }
}
