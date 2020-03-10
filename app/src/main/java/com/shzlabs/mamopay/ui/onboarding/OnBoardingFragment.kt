package com.shzlabs.mamopay.ui.onboarding

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.shzlabs.mamopay.R
import com.shzlabs.mamopay.data.local.prefs.UserPreferences
import com.shzlabs.mamopay.di.components.AppComponent
import com.shzlabs.mamopay.util.helper.GoogleAuthHelper
import com.shzlabs.mamopay.ui.base.BaseActivity
import com.shzlabs.mamopay.ui.base.BaseFragment
import com.shzlabs.mamopay.ui.home.OnBoardingViewModel
import com.shzlabs.mamopay.ui.onboarding.details.OnBoardingDetailsFragment
import com.shzlabs.mamopay.util.navigation.NavHelper
import kotlinx.android.synthetic.main.on_boarding_fragment.*
import javax.inject.Inject

class OnBoardingFragment : BaseFragment() {

    companion object {
        fun newInstance() = OnBoardingFragment()
        private const val RC_AUTH = 1337
    }

    val sliderAdapter = SliderAdapter()

    @Inject
    lateinit var userPreferences: UserPreferences

    private lateinit var viewModel: OnBoardingViewModel

    override fun injectDependencies(diComponent: AppComponent) = diComponent.inject(this)

    override fun provideLayoutId(): Int = R.layout.on_boarding_fragment

    override fun initView(view: View) {


        //slider_viewpager.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        sliderAdapter.precacheImages(view.context)
        slider_viewpager.adapter = sliderAdapter
        indicator.setViewPager(slider_viewpager)

        login_google.setOnClickListener {
            val intent = GoogleAuthHelper.initAuth(view.context)
            startActivityForResult(intent, Companion.RC_AUTH)
        }

    }

    override fun onStart() {
        super.onStart()
        //enablePostAuthorizationFlows()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enablePostAuthorizationFlows()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Companion.RC_AUTH && resultCode == Activity.RESULT_OK && data != null && context != null) {

            GoogleAuthHelper.processResponse(context!!, data) { authState ->
                userPreferences.setAuthState(authState)
                enablePostAuthorizationFlows(true)
            }
        } else {
            showError(rootView, getString(R.string.err_login_failed))
        }
    }

    override fun setupObservers() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(OnBoardingViewModel::class.java)
        viewModel.onError.observe(viewLifecycleOwner, Observer { showError(rootView, it) })
    }

    private fun enablePostAuthorizationFlows(firstLogin: Boolean = false) {
        if (GoogleAuthHelper.isLoggedIn(userPreferences.getAuthState())) {
            NavHelper
                .pushFragment(activity as BaseActivity, OnBoardingDetailsFragment.newInstance())
        } else if (firstLogin) {
            showError(rootView, getString(R.string.err_login_failed))
        }

    }


}
