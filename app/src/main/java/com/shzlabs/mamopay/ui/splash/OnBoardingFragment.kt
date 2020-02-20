package com.shzlabs.mamopay.ui.splash

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.shzlabs.mamopay.R
import com.shzlabs.mamopay.di.components.AppComponent
import com.shzlabs.mamopay.ui.base.BaseFragment
import com.shzlabs.mamopay.ui.home.OnBoardingViewModel
import kotlinx.android.synthetic.main.home_fragment.*

class OnBoardingFragment : BaseFragment() {

    companion object {
        fun newInstance() = OnBoardingFragment()
    }


    private lateinit var viewModel: OnBoardingViewModel

    override fun injectDependencies(diComponent: AppComponent) = diComponent.inject(this)

    override fun provideLayoutId(): Int = R.layout.on_boarding_fragment

    override fun initView(view: View) {}

    override fun setupObservers() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(OnBoardingViewModel::class.java)

        viewModel.sampleData.observe(viewLifecycleOwner, Observer { welcome_text.text = it.title })

        viewModel.onError.observe(viewLifecycleOwner, Observer { showError(rootView, it) })

        viewModel.getSampleData()
    }

}
