package com.shzlabs.mamopay.ui.onboarding.details

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.shzlabs.mamopay.NavMgr
import com.shzlabs.mamopay.R
import com.shzlabs.mamopay.di.components.AppComponent
import com.shzlabs.mamopay.ui.base.BaseActivity
import com.shzlabs.mamopay.ui.base.BaseFragment
import com.shzlabs.mamopay.ui.signin.setup.SignInSetupFragment
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.on_boarding_details_fragment.*

class OnBoardingDetailsFragment : BaseFragment() {

    companion object {
        fun newInstance() = OnBoardingDetailsFragment()
    }

    private lateinit var viewModel: OnBoardingDetailsViewModel

    override fun injectDependencies(diComponent: AppComponent) = diComponent.inject(this)

    override fun provideLayoutId(): Int = R.layout.on_boarding_details_fragment

    override fun initView(view: View) {
        confirm_button.setOnClickListener {
            NavMgr().pushFragment(activity as BaseActivity, SignInSetupFragment.newInstance())
        }
    }

    override fun setupObservers() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(OnBoardingDetailsViewModel::class.java)

        viewModel.onError.observe(viewLifecycleOwner, Observer { showError(rootView, it) })

    }


}
