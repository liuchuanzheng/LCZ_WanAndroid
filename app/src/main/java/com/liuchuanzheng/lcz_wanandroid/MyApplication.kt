package com.liuchuanzheng.lcz_wanandroid

import android.app.Application
import android.content.Context
import android.util.Log
import com.lengjiye.code.utils.CrashHandlerUtil
import com.liuchuanzheng.base_module.config.BaseConfig
import com.liuchuanzheng.base_module.config.BaseConfigBean
import com.liuchuanzheng.base_module.config.application.MasterApplication
import com.liuchuanzheng.base_module.config.inter.IApp

/**
 * @author 刘传政
 * @date 2020/8/4 09:10
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
class MyApplication : Application(),IApp {
    init {
        instance = this
    }

    override fun applicationContext(): Context {
        return applicationContext
    }

    override fun applicationId(): String {
        return BuildConfig.APPLICATION_ID
    }

    override fun versionCode(): Int {
        return BuildConfig.VERSION_CODE
    }

    override fun versionName(): String {
        return BuildConfig.VERSION_NAME
    }

    override fun isDebug(): Boolean {
        return BuildConfig.DEBUG
    }

    override fun buildType(): String {
        return BuildConfig.BUILD_TYPE
    }

    override fun baseUrl(): String {
        return BuildConfig.BASE_URL
    }

    override fun onCreate() {
        super.onCreate()
        MasterApplication.getInstance().setIApp(this)

        // 崩溃日志捕捉
        val handler = CrashHandlerUtil()
        Thread.setDefaultUncaughtExceptionHandler(handler)
    }

    companion object {
        lateinit var instance: MyApplication
    }
}