package com.liuchuanzheng.tools_module;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/**
 * 网络相关
 */
public class NetWorkTool {

    /**
     * 检测网络是否可用
     *
     * @return
     */
    public static boolean isNetWorkConnected(Context context) {
        ConnectivityManager mConnectivityManager =
                (ConnectivityManager) context.getApplicationContext()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return false;
    }
}
