package com.lengjiye.code.home.fragment

import android.os.Bundle
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lengjiye.base.fragment.LazyBaseFragment
import com.liuchuanzheng.lcz_wanandroid.MyApplication
import com.liuchuanzheng.lcz_wanandroid.R
import com.liuchuanzheng.lcz_wanandroid.databinding.FragmentHomeBinding
import com.liuchuanzheng.lcz_wanandroid.module.home.viewmodel.HomeViewModel
import com.scwang.smart.refresh.footer.BallPulseFooter
import com.scwang.smart.refresh.header.MaterialHeader

/**
 * 首页
 */
class HomeFragment : LazyBaseFragment<FragmentHomeBinding, HomeViewModel>() {


    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun getViewModel(): HomeViewModel {
        return HomeViewModel(MyApplication.instance)
    }

    override fun bindViewModel() {
        mBinding.viewModel = mViewModel
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        mBinding.srlLayout.setRefreshHeader(MaterialHeader(getBaseActivity()))
        mBinding.srlLayout.setRefreshFooter(BallPulseFooter(getBaseActivity()))

    }


    override fun initData() {
        super.initData()


    }


    override fun loadData() {

    }

    inner class MyAdapter(data: List<String>) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_home, data) {


        /**
         * Implement this method and use the helper to adapt the view to the given item.
         *
         * @param helper A fully initialized helper.
         * @param item   The item that needs to be displayed.
         */
        override fun convert(helper: BaseViewHolder, item: String) {
        }

    }
}