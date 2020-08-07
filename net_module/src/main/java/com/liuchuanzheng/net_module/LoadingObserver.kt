package com.liuchuanzheng.net_module

import com.google.gson.JsonSyntaxException
import com.lengjiye.network.exception.ApiException
import com.liuchuanzheng.base_module.config.application.MasterApplication
import com.liuchuanzheng.net_module.exception.ErrorCodeConstant
import com.liuchuanzheng.tools_module.LogTool
import com.liuchuanzheng.tools_module.NetWorkTool
import com.liuchuanzheng.tools_module.ResTool
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.lang.NullPointerException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * 请求dialog统一处理
 *
 * code 拦截
 */
class LoadingObserver<T>() : Observer<T> {
    private var observerListener: ObserverListener<T>? = null

    constructor(observerListener: ObserverListener<T>?) : this() {
        this.observerListener = observerListener
    }

    private var disposable: Disposable? = null

    override fun onSubscribe(d: Disposable) {
        disposable = d
        if (!NetWorkTool.isNetWorkConnected(MasterApplication.getInstance().applicationContext())) {
            val errorCode = ErrorCodeConstant.NO_NETWORK
            val errorMsg = ResTool.getString(MasterApplication.getInstance().applicationContext(),R.string.net_0001)
            val apiException = ApiException(errorCode, errorMsg, null)
            observerListener?.observerOnError(apiException)
            disposable?.dispose()
        }
    }

    override fun onNext(t: T) {
        observerListener?.observerOnNext(t)
    }

    override fun onComplete() {
        observerListener?.observerOnComplete()
    }

    override fun onError(e: Throwable) {
        val errorCode: Int?
        val errorMsg: String?
        val apiException: ApiException?
        LogTool.e("message:${e.message}")
        when (e) {
            is NullPointerException -> {
                observerListener?.observerOnNext(null)
                return
            }
            is SocketTimeoutException -> {
                errorCode = ErrorCodeConstant.NETWORK_TIMEOUT
                errorMsg = ResTool.getString(MasterApplication.getInstance().applicationContext(),R.string.net_0003)
                apiException = ApiException(errorCode, errorMsg, null)
            }
            is ConnectException, is UnknownHostException -> {
                errorCode = ErrorCodeConstant.NO_NETWORK
                errorMsg = ResTool.getString(MasterApplication.getInstance().applicationContext(),R.string.net_0002)
                apiException = ApiException(errorCode, errorMsg, null)
            }
            is JsonSyntaxException -> {
                errorCode = ErrorCodeConstant.JSON_ERROR
                errorMsg = e.message
                apiException = ApiException(errorCode, errorMsg, null)
            }
            is ApiException -> {
                interceptStatusCode(e)
                apiException = e
            }
            else -> {
                // 线上环境显示未知错误
                if ("release" == MasterApplication.getInstance().buildType()) {
                    errorCode = ErrorCodeConstant.UNKNOWN_ERROR
                    errorMsg = ResTool.getString(MasterApplication.getInstance().applicationContext(),R.string.net_0004)
                    apiException = ApiException(errorCode, errorMsg, null)
                } else {
                    errorMsg = e.message
                    apiException = ApiException(ErrorCodeConstant.UNKNOWN_ERROR, errorMsg, null)
                }
            }
        }
        observerListener?.observerOnError(apiException)
    }

    /**
     * 取消请求
     */
    fun cancelRequest() {
        disposable?.let {
            if (it.isDisposed) {
                it.dispose()
            }
        }
    }

    // 统一处理错误的errorCode 比如token失效
    private fun interceptStatusCode(e: ApiException) {
        val statusCode = e.mErrorCode

        statusCode?.let {
            when (statusCode) {
                ErrorCodeConstant.LOGIN_INVALID -> {
                    // TODO 登录失效
                }
            }
        }
    }

    abstract class ObserverListener<T> {
        open fun observerOnNext(data: T?) {}
        open fun observerOnError(e: ApiException) {}
        open fun observerOnComplete() {}
    }
}