package com.shzlabs.mamopay

import android.os.Bundle
import com.shzlabs.mamopay.ui.base.BaseActivity
import com.shzlabs.mamopay.ui.onboarding.OnBoardingFragment
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var injected: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NavMgr().pushFragment(this, OnBoardingFragment.newInstance())

        getDiComponent().inject(this)

        // if (injected != null) Toast.makeText(this, injected, Toast.LENGTH_LONG).show()
    }


}
