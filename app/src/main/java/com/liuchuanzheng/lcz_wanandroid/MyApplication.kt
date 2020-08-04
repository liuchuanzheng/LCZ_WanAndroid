package com.liuchuanzheng.lcz_wanandroid

import android.app.Application
import android.util.Log
import com.liuchuanzheng.base_module.config.BaseConfig
import com.liuchuanzheng.base_module.config.BaseConfigBean

/**
 * @author 刘传政
 * @date 2020/8/4 09:10
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        var buildConfigBean = BaseConfigBean.Builder()
            .setLogTag("玩安卓")
            .build()
        BaseConfig.getInstance().init(buildConfigBean)
        Log.i("试试",BaseConfig.getInstance().baseConfigBean.logTag)
    }
}