package com.liuchuanzheng.lcz_wanandroid

import android.app.Application
import android.content.Context
import com.lengjiye.code.utils.CrashHandlerUtil
import com.liuchuanzheng.base_module.config.application.MasterApplication
import com.liuchuanzheng.base_module.config.inter.IApp
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

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
        initLog()
    }

    companion object {
        lateinit var instance: MyApplication
    }

    /**
     * 初始化logger库
     */
    private fun initLog() {
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false) // (Optional) Whether to show thread info or not. Default true
            .methodCount(2) // (Optional) How many method line to show. Default 2
            .methodOffset(0) // (Optional) Hides internal method calls up to offset. Default 5
            //                .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
            .tag("玩安卓") // (Optional) Global tag for every log. Default PRETTY_LOGGER
            .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }
}