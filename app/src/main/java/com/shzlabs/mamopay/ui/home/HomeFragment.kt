package com.shzlabs.mamopay.ui.home

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.shzlabs.mamopay.R
import com.shzlabs.mamopay.di.components.AppComponent
import com.shzlabs.mamopay.ui.base.BaseFragment
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : BaseFragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun injectDependencies(diComponent: AppComponent) = diComponent.inject(this)

    override fun provideLayoutId(): Int = R.layout.home_fragment

    override fun initView(view: View) {}

    override fun setupObservers() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)

        viewModel.sampleData.observe(viewLifecycleOwner, Observer { welcome_text.text = it.title })

        viewModel.onError.observe(viewLifecycleOwner, Observer { showError(rootView, it) })

        viewModel.getSampleData()
    }



}