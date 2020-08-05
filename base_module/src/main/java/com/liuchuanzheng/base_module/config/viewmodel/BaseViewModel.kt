package com.liuchuanzheng.base_module.config.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

/**
 * @author 刘传政
 * @date 2020/8/5 10:13
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
abstract class BaseViewModel(application: Application):AndroidViewModel(application) {
    /**
     * 错误处理
     */
    val errorCode = MutableLiveData<Any>()

    abstract fun onCreate()

    abstract fun onDestroy()
}