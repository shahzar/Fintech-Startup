package com.shzlabs.mamopay

import android.util.Log
import com.shzlabs.mamopay.ui.base.BaseActivity
import com.shzlabs.mamopay.ui.base.BaseFragment


class NavMgr() {

    private val TAG = "NavMgr"

    fun pushFragment(baseActivity: BaseActivity, baseFragment: BaseFragment) {
        Log.d(TAG, "Pushed ${baseFragment.javaClass.simpleName}")
        baseActivity
            .supportFragmentManager
            .beginTransaction()
            .addToBackStack("")
            .replace(R.id.content, baseFragment)
            .commit()
    }


    fun pop(baseActivity: BaseActivity): Boolean {

        if (baseActivity.supportFragmentManager.backStackEntryCount <= 1) {
            return false
        }

        baseActivity
            .supportFragmentManager
            .popBackStack()

        return true
    }
}