package com.shzlabs.mamopay

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.shzlabs.mamopay.ui.base.BaseActivity
import com.shzlabs.mamopay.ui.main.dashboard.DashboardFragment
import com.shzlabs.mamopay.util.navigation.NavMgr

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getDiComponent().inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        // Login check
        if (!viewModel.isUserLoggedIn()) {
            startActivity(Intent(this, OnBoardingActivity::class.java))
            finish()
        }

        NavMgr().pushFragment(this, DashboardFragment.newInstance())
    }
}
