package com.lengjiye.code.home.fragment

import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lengjiye.base.fragment.LazyBaseFragment
import com.lengjiye.code.home.bean.HomeBean
import com.lengjiye.code.utils.LayoutManagerUtils
import com.lengjiye.code.utils.toast
import com.liuchuanzheng.lcz_wanandroid.MyApplication
import com.liuchuanzheng.lcz_wanandroid.R
import com.liuchuanzheng.lcz_wanandroid.databinding.FragmentHomeBinding
import com.liuchuanzheng.lcz_wanandroid.me.bean.UserBean
import com.liuchuanzheng.lcz_wanandroid.module.home.viewmodel.HomeViewModel
import com.liuchuanzheng.tools_module.ResTool
import com.scwang.smart.refresh.footer.BallPulseFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener

/**
 * 首页
 */
class HomeFragment : LazyBaseFragment<FragmentHomeBinding, HomeViewModel>() {

    private var page = 0
    var models: MutableList<HomeBean>? = arrayListOf()
    lateinit var myAdapter:MyAdapter
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

        myAdapter = MyAdapter(models)
        mBinding.rlList.layoutManager = LayoutManagerUtils.verticalLinearLayoutManager(getBaseActivity())
        mBinding.rlList.adapter = myAdapter
        mBinding.srlLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener{
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                mViewModel.getHomeData(this@HomeFragment, page)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                refresh()
            }

        })

    }


    override fun initData() {
        super.initData()
        mViewModel.article.observe(this, Observer {
            mBinding.srlLayout.finishLoadMore()
            val dates = it.datas
            if (dates.isEmpty()) {
                ResTool.getString(activity,R.string.s_5).toast()
                return@Observer
            }
            myAdapter.data.addAll(dates.toMutableList())
            myAdapter.notifyDataSetChanged()
            page = it.curPage
        })

        mViewModel.homeBeanList.observe(this, Observer {
            mBinding.srlLayout.finishRefresh()
            if (page == 0) {
                myAdapter.data.clear()
            }
            myAdapter.data.addAll(it.toMutableList())
            myAdapter.notifyDataSetChanged()
            page = 1
        })
    }


    override fun loadData() {
        refresh()
    }

    private fun refresh() {
        page = 0
        mViewModel.getHomeTopAndFirstListData(this)
        mViewModel.homeBeanTopAndFirstList?.clear()
    }

    inner class MyAdapter( models: MutableList<HomeBean>?) : BaseQuickAdapter<HomeBean, BaseViewHolder>(R.layout.item_home, models) {


        /**
         * Implement this method and use the helper to adapt the view to the given item.
         *
         * @param helper A fully initialized helper.
         * @param item   The item that needs to be displayed.
         */
        override fun convert(helper: BaseViewHolder, item: HomeBean?) {
            item?.let {
                helper.setText(R.id.tv_title,it.title)
            }

        }

    }
}