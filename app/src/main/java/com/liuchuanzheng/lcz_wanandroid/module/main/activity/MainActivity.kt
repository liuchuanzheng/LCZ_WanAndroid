package com.liuchuanzheng.lcz_wanandroid.module.main.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.lengjiye.code.home.fragment.HomeFragment
import com.lengjiye.code.main.manager.MainFragmentManager
import com.liuchuanzheng.base_module.config.activity.BaseActivity
import com.liuchuanzheng.lcz_wanandroid.R
import com.liuchuanzheng.lcz_wanandroid.databinding.ActivityMainBinding
import com.liuchuanzheng.lcz_wanandroid.module.main.viewmodel.MainViewModel
import com.liuchuanzheng.tools_module.LogTool

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    private lateinit var mTempFragment: Fragment
    /**
     * 初始化数据
     * 设置数据
     * 请求接口等
     */
    override fun initData() {
        mTempFragment = MainFragmentManager.instance.getHomeFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.f_container, mTempFragment as HomeFragment)
            .show(mTempFragment as HomeFragment).commit()
    }

    override fun initView() {
        initBottomNavigation()
    }

    /**
     * LiveData 数据监听
     */
    override fun initLiveDataObserver() {
    }

    override fun initToolBar() {
    }

    /**
     * intent 传值
     */
    override fun initIntent(savedInstanceState: Bundle?) {
    }

    /**
     * 绑定 ViewModel
     */
    override fun bindViewModel() {
        mBinding.viewModel = mViewModel
    }

    override fun getViewModel(): MainViewModel {
        return MainViewModel(application)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }
    /**
     * 初始化底部按钮
     */
    private fun initBottomNavigation() {
        mBinding.bnBar.let {
            it.setBarBackgroundColor(R.color.c_ff)
                .addItem(
                    BottomNavigationItem(R.drawable.ic_home_2cac77_24dp_pre, R.string.s_1)
                        .setInactiveIconResource(R.drawable.ic_home_a4aca9_24dp_nor)
                        .setActiveColorResource(R.color.c_4697fa).setInActiveColorResource(R.color.c_99)
                )
                .addItem(
                    BottomNavigationItem(R.drawable.ic_data_usage_2cac77_24dp_pre, R.string.s_33)
                        .setInactiveIconResource(R.drawable.ic_data_usage_a4aca9_24dp_nor)
                        .setActiveColorResource(R.color.c_4697fa).setInActiveColorResource(R.color.c_99)
                )
                .addItem(
                    BottomNavigationItem(R.drawable.ic_device_hub_2cac77_24dp_pre, R.string.s_2)
                        .setInactiveIconResource(R.drawable.ic_device_hub_a4aca9_24dp_nor)
                        .setActiveColorResource(R.color.c_4697fa).setInActiveColorResource(R.color.c_99)
                )
                .addItem(
                    BottomNavigationItem(R.drawable.ic_mode_edit_2cac77_24dp_pre, R.string.s_3)
                        .setInactiveIconResource(R.drawable.ic_mode_edit_a4aca9_24dp_nor)
                        .setActiveColorResource(R.color.c_4697fa).setInActiveColorResource(R.color.c_99)
                )
                .addItem(
                    BottomNavigationItem(R.drawable.ic_person_2cac77_24dp_pre, R.string.s_4)
                        .setInactiveIconResource(R.drawable.ic_person_a4aca9_24dp_nor)
                        .setActiveColorResource(R.color.c_4697fa).setInActiveColorResource(R.color.c_99)
                )
                .initialise()

            it.setMode(BottomNavigationBar.MODE_FIXED)

            it.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
                override fun onTabReselected(position: Int) {
                }

                override fun onTabUnselected(position: Int) {
                }

                override fun onTabSelected(position: Int) {
                    switchFragment(position)
                }
            })
        }
    }
    private fun switchFragment(position: Int) {
        var fragment: Fragment? = null
        when (position) {
            0 -> {
                fragment = MainFragmentManager.instance.getHomeFragment()
            }

            1 -> {
                fragment = MainFragmentManager.instance.getHomeFragment()
            }

            2 -> {
                fragment = MainFragmentManager.instance.getHomeFragment()
            }

            3 -> {
                fragment = MainFragmentManager.instance.getHomeFragment()
            }

            4 -> {
                fragment = MainFragmentManager.instance.getHomeFragment()
            }

        }

        fragment?.let {
            switchFragment(it)
        }
    }
    /**
     * 切换fragment
     */
    private fun switchFragment(fragment: Fragment) {

        if (fragment == mTempFragment) {
            return
        }
        if (fragment.isAdded) {
            supportFragmentManager.beginTransaction().hide(mTempFragment).show(fragment).commit()
        } else {
            supportFragmentManager.beginTransaction().hide(mTempFragment).add(R.id.f_container, fragment).commit()
        }
        mTempFragment = fragment
    }
}