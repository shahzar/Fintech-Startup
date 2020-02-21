package com.shzlabs.mamopay.ui.signin

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.shzlabs.mamopay.R
import com.shzlabs.mamopay.di.components.AppComponent
import com.shzlabs.mamopay.ui.base.BaseFragment

class SignInFragment : BaseFragment() {

    companion object {
        fun newInstance() = SignInFragment()
    }


    private lateinit var viewModel: SignInViewModel

    override fun injectDependencies(diComponent: AppComponent) = diComponent.inject(this)

    override fun provideLayoutId(): Int = R.layout.sign_in_fragment

    override fun initView(view: View) {}

    override fun setupObservers() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SignInViewModel::class.java)

        viewModel.onError.observe(viewLifecycleOwner, Observer { showError(rootView, it) })

    }

}
