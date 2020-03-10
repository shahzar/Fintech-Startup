package com.shzlabs.mamopay.ui.signin.setup

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.shzlabs.mamopay.R
import com.shzlabs.mamopay.di.components.AppComponent
import com.shzlabs.mamopay.ui.base.BaseActivity
import com.shzlabs.mamopay.ui.base.BaseFragment
import com.shzlabs.mamopay.ui.signin.SignInFragment
import com.shzlabs.mamopay.util.biometric.BiometricHelper
import com.shzlabs.mamopay.util.display.Toaster
import com.shzlabs.mamopay.util.navigation.NavHelper
import kotlinx.android.synthetic.main.sign_in_setup_fragment.*
import javax.inject.Inject

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


    @Inject
    lateinit var applicationContext: Context

    private lateinit var viewModel: SignInSetupViewModel

    override fun injectDependencies(diComponent: AppComponent) = diComponent.inject(this)

    override fun provideLayoutId(): Int = R.layout.sign_in_setup_fragment

    override fun initView(view: View) {

        setToolbarTitle(getString(R.string.page_title_pin_setup))

        numpad.setOnNumberPressListener {
            viewModel.onNumberPress(it)
        }

        numpad.setOnDeletePressListener {
            viewModel.onDeletePress()
        }

        numpad.setOnFingerprintPressListener {
            promptBiometric()
        }

        stepper.onStepCompleteListener {
            viewModel.onNumberPressComplete()
        }

        arguments?.getString(KEY_CONFIRM_CODE)?.let {
            if (it.isNotEmpty()) {
                setConfirmScreen(it)
            }
        }
    }

    override fun setupObservers() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SignInSetupViewModel::class.java)

        viewModel.onCodeUpdate.observe(viewLifecycleOwner, Observer {
            stepper.setActiveCount(it.length)
            numpad.showDeleteButton(it.isNotEmpty())
        })

        viewModel.onPinSet.observe(viewLifecycleOwner, Observer {
            NavHelper
                .pushFragment(activity as BaseActivity, SignInSetupFragment.newInstance(it))
        })

        viewModel.onLoginSuccess.observe(viewLifecycleOwner, Observer {
            if (it) {
                stepper.setSuccess()
                promptBiometric()
            } else {
                stepper.setError()
            }
        })

        viewModel.onError.observe(viewLifecycleOwner, Observer { showError(rootView, it) })


    }

    private fun setConfirmScreen(pinCode: String) {
        setToolbarTitle(getString(R.string.page_title_pin_setup_confirm))
        viewModel.setConfirmCode(pinCode)
        numpad.showFingerprintButton(false)
        msg_label.text = resources.getString(R.string.msg_confirm_pin)
        // TODO update title
        // setTitle()
    }

    private fun promptBiometric() {

        val biometricManager = BiometricManager.from(applicationContext)

        if (biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS) {

            val prompt = BiometricHelper.instanceOfBiometricPrompt(this,
                onCancel = {
                    //showError(rootView, it)
                    biometricAuthSuccess(false)
                },
                onSuccess = {
                    biometricAuthSuccess(true)
                })

            prompt.authenticate(getPromptInfo())

            return
        }

        Toaster.show(applicationContext, getString(R.string.err_fingerprint_setup_system))
        biometricAuthSuccess(false)
    }

    private fun biometricAuthSuccess(success: Boolean) {
        viewModel.biometricAuthSuccess(success)

        if (!success && !viewModel.isPinAuthenticated()) {
            // Moving back to pin setup
            return
        }

        // Move to dashboard
        NavHelper
            .pushFragment(activity as BaseActivity, SignInFragment.newInstance())
    }

    private fun getPromptInfo(): BiometricPrompt.PromptInfo {
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(getString(R.string.msg_fingerprint_prompt_title))
            .setSubtitle(getString(R.string.msg_fingerprint_prompt_subtitle))
            .setDescription(getString(R.string.msg_fingerprint_prompt_description))
            .setNegativeButtonText(getString(R.string.msg_fingerprint_prompt_neg_button))
            .build()
        return promptInfo
    }

}
