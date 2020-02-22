package com.shzlabs.mamopay.ui.signin.setup

import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.shzlabs.mamopay.R
import com.shzlabs.mamopay.di.components.AppComponent
import com.shzlabs.mamopay.ui.base.BaseFragment
import kotlinx.android.synthetic.main.sign_in_setup_fragment.*

class SignInSetupFragment : BaseFragment() {

    companion object {
        fun newInstance() = SignInSetupFragment()
    }


    private lateinit var viewModel: SignInSetupViewModel

    override fun injectDependencies(diComponent: AppComponent) = diComponent.inject(this)

    override fun provideLayoutId(): Int = R.layout.sign_in_setup_fragment

    override fun initView(view: View) {
//        numpad.setOnClickListener {
//            codeProcessLayout.incrementStep()
//        }

        numpad.setOnNumberPressListener {
            Log.d("SignInSetup", "Number pressed $it")
            viewModel.onNumberPress(it)
            //stepper.incrementStep()
        }

        numpad.setOnDeletePressListener {
            viewModel.onDeletePress()
            //stepper.decrementStep()
        }

        stepper.onStepCompleteListener {
            viewModel.onNumberPressComplete()
            //stepper.setSuccess()
        }
    }

    override fun setupObservers() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SignInSetupViewModel::class.java)

        viewModel.onCodeUpdate.observe(viewLifecycleOwner, Observer {
            stepper.setActiveCount(it.length)
            numpad.showDeleteButton(it.isNotEmpty())
        })

        viewModel.onLoginSuccess.observe(viewLifecycleOwner, Observer { stepper.setSuccess() })
        viewModel.onLoginFailed.observe(viewLifecycleOwner, Observer { stepper.setError() })

        viewModel.onError.observe(viewLifecycleOwner, Observer { showError(rootView, it) })

    }

}
