package com.liuchuanzheng.base_module.config.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.liuchuanzheng.base_module.config.viewmodel.BaseViewModel

/**
 * @author 刘传政
 * @date 2020/8/5 10:00
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
abstract class BaseActivity <VDB: ViewDataBinding,VM: BaseViewModel> : AppCompatActivity(){
    lateinit var mBinding:VDB
    lateinit var mViewModel:VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,getLayoutId())
        mViewModel = ViewModelProvider(this).get(getViewModel()::class.java)
        mBinding.lifecycleOwner = this
        bindViewModel()
        mViewModel.onCreate()
        initIntent(savedInstanceState)
        initView()
        initToolBar()
        initLiveDataObserver()
        initData()
    }

    abstract fun getLayoutId(): Int
    abstract fun getViewModel():VM
    /**
     * 绑定 ViewModel
     */
    abstract fun bindViewModel()
    /**
     * intent 传值
     */
    abstract fun initIntent(savedInstanceState: Bundle?)
    abstract fun initView()
    abstract fun initToolBar()
    /**
     * LiveData 数据监听
     */
    abstract fun initLiveDataObserver()
    /**
     * 初始化数据
     * 设置数据
     * 请求接口等
     */
    abstract fun initData()
    override fun onDestroy() {
        super.onDestroy()
        mViewModel.onDestroy()
    }
}
