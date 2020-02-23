package com.shzlabs.mamopay.ui.signin.setup

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.shzlabs.mamopay.NavMgr
import com.shzlabs.mamopay.R
import com.shzlabs.mamopay.di.components.AppComponent
import com.shzlabs.mamopay.ui.base.BaseActivity
import com.shzlabs.mamopay.ui.base.BaseFragment
import kotlinx.android.synthetic.main.sign_in_setup_fragment.*

class SignInSetupFragment : BaseFragment() {

    companion object {
        const val KEY_CONFIRM_CODE = "KEY_CONFIRM_CODE"

        fun newInstance(confirmCode: String? = null) = SignInSetupFragment().apply {
            confirmCode?.let {
                val bundle = Bundle()
                bundle.putString(KEY_CONFIRM_CODE, confirmCode)
                arguments = bundle
            }
        }
    }


    private lateinit var viewModel: SignInSetupViewModel

    override fun injectDependencies(diComponent: AppComponent) = diComponent.inject(this)

    override fun provideLayoutId(): Int = R.layout.sign_in_setup_fragment

    override fun initView(view: View) {

        numpad.setOnNumberPressListener {
            Log.d("SignInSetup", "Number pressed $it")
            viewModel.onNumberPress(it)
        }

        numpad.setOnDeletePressListener {
            viewModel.onDeletePress()
        }

        stepper.onStepCompleteListener {
            viewModel.onNumberPressComplete()
        }
    }

    override fun setupObservers() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SignInSetupViewModel::class.java)

        viewModel.onCodeUpdate.observe(viewLifecycleOwner, Observer {
            stepper.setActiveCount(it.length)
            numpad.showDeleteButton(it.isNotEmpty())
        })

        viewModel.onPinSet.observe(viewLifecycleOwner, Observer {
            NavMgr().pushFragment(activity as BaseActivity, SignInSetupFragment.newInstance(it))
        })

        viewModel.onLoginSuccess.observe(viewLifecycleOwner, Observer { stepper.setSuccess() })
        viewModel.onLoginFailed.observe(viewLifecycleOwner, Observer { stepper.setError() })

        viewModel.onError.observe(viewLifecycleOwner, Observer { showError(rootView, it) })

        arguments?.getString(KEY_CONFIRM_CODE)?.let {
            if (it.isNotEmpty()) {
                setConfirmScreen(it)
            }
        }

    }

    fun setConfirmScreen(pinCode: String) {
        viewModel.setConfirmCode(pinCode)
        msg_label.text = resources.getString(R.string.msg_confirm_pin)
        // TODO update title
        // setTitle()
    }

}
