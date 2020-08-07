package com.liuchuanzheng.lcz_wanandroid.module.home.viewmodel

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.lengjiye.code.home.bean.ArticleBean
import com.lengjiye.code.home.bean.HomeBean
import com.lengjiye.code.home.model.HomeModel
import com.lengjiye.code.utils.toast
import com.lengjiye.network.exception.ApiException
import com.liuchuanzheng.base_module.config.viewmodel.BaseViewModel
import com.liuchuanzheng.lcz_wanandroid.R
import com.liuchuanzheng.net_module.LoadingObserver
import com.liuchuanzheng.tools_module.ResTool

/**
 * 数据请求接口
 */
class HomeViewModel(application: Application) : BaseViewModel(application) {
    var article = MutableLiveData<ArticleBean>()
    var homeBeanList = MutableLiveData<List<HomeBean>>()
    var homeBeanTopAndFirstList: MutableList<HomeBean>? = null
    private lateinit var loadingObserver: LoadingObserver<ArticleBean>
    private lateinit var loadingObserverTopAndFirst: LoadingObserver<List<HomeBean>>
    private lateinit var loadingDefault: LoadingObserver<String>
    override fun onCreate() {
        loadingObserver = LoadingObserver(object : LoadingObserver.ObserverListener<ArticleBean>() {
            override fun observerOnNext(data: ArticleBean?) {
                article.value = data
            }

            override fun observerOnError(e: ApiException) {

            }
        })
        homeBeanTopAndFirstList = arrayListOf()
        loadingObserverTopAndFirst = LoadingObserver(object : LoadingObserver.ObserverListener<List<HomeBean>>() {

            override fun observerOnComplete() {
                homeBeanList.value = homeBeanTopAndFirstList
            }

            override fun observerOnNext(data: List<HomeBean>?) {
                data?.let {
                    homeBeanTopAndFirstList?.addAll(it)
                }
            }

            override fun observerOnError(e: ApiException) {

            }
        })
        loadingDefault = LoadingObserver(object : LoadingObserver.ObserverListener<String>() {
            override fun observerOnNext(data: String?) {
                ResTool.getString(getApplication(),R.string.s_35).toast()
            }

            override fun observerOnError(e: ApiException) {
                ResTool.getString(getApplication(),R.string.s_36).toast()
            }

        })
    }

    override fun onDestroy() {
        loadingDefault.cancelRequest()
        loadingObserver.cancelRequest()
        loadingObserverTopAndFirst.cancelRequest()
    }
    /**
     * 获取置顶和首页数据
     */
    fun getHomeTopAndFirstListData(lifecycleOwner: LifecycleOwner) {
        loadingObserverTopAndFirst.cancelRequest()
        loadingObserverTopAndFirst.let {
            HomeModel.singleton.getHomeTopAndFirstListData(lifecycleOwner, it)
        }
    }
    /**
     * 获取首页列表数据
     */
    fun getHomeData(lifecycleOwner: LifecycleOwner, page: Int) {
        loadingObserver.cancelRequest()
        loadingObserver.let {
            HomeModel.singleton.getHomeListData(lifecycleOwner, page, it)
        }
    }
}