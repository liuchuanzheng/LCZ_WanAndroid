package com.lengjiye.code.main.manager

import androidx.fragment.app.Fragment
import com.lengjiye.code.home.fragment.HomeFragment
import java.lang.ref.WeakReference

class MainFragmentManager private constructor() {
    companion object {
        var instance = Singleton.singleton
    }

    private object Singleton {
        val singleton = MainFragmentManager()
    }

    private var map: MutableMap<String, WeakReference<Fragment>>? = null

    private fun getFragmentMap(): MutableMap<String, WeakReference<Fragment>> {
        if (map == null) {
            map = hashMapOf()
        }
        return map as MutableMap<String, WeakReference<Fragment>>
    }

    fun getHomeFragment(): HomeFragment {
        if (getFragmentMap()[HomeFragment::class.java.simpleName] == null) {
            val fragment = HomeFragment()
            getFragmentMap().put(HomeFragment::class.java.simpleName, WeakReference(fragment))
        }
        return getFragmentMap()[HomeFragment::class.java.simpleName]?.get() as HomeFragment
    }



    fun destroy() {
        map?.clear()
    }

    fun destroy(fragment: Fragment?) {
        if (map == null || fragment == null) {
            return
        }
        getFragmentMap().remove(fragment.javaClass.simpleName)
    }
}