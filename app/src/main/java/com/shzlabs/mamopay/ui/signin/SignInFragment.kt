package com.shzlabs.mamopay.ui.signin

import android.content.Context
import android.view.View
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.shzlabs.mamopay.NavMgr
import com.shzlabs.mamopay.R
import com.shzlabs.mamopay.di.components.AppComponent
import com.shzlabs.mamopay.ui.base.BaseActivity
import com.shzlabs.mamopay.ui.base.BaseFragment
import com.shzlabs.mamopay.ui.home.HomeFragment
import com.shzlabs.mamopay.util.biometric.BiometricHelper
import com.shzlabs.mamopay.util.display.Toaster
import kotlinx.android.synthetic.main.sign_in_fragment.*
import javax.inject.Inject

class SignInFragment : BaseFragment() {

    companion object {
        fun newInstance() = SignInFragment()
    }


    @Inject
    lateinit var applicationContext: Context

    private lateinit var viewModel: SignInViewModel

    override fun injectDependencies(diComponent: AppComponent) = diComponent.inject(this)

    override fun provideLayoutId(): Int = R.layout.sign_in_fragment

    override fun initView(view: View) {

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

    }

    override fun setupObservers() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SignInViewModel::class.java)

        viewModel.firstName.observe(viewLifecycleOwner, Observer {
            greetings.text = getString(R.string.msg_greetings, it)
        })

        viewModel.onCodeUpdate.observe(viewLifecycleOwner, Observer {
            stepper.setActiveCount(it.length)
            numpad.showDeleteButton(it.isNotEmpty())
        })

        viewModel.onLoginSuccess.observe(viewLifecycleOwner, Observer {
            if (it) {
                stepper.setSuccess()
                navigateToDashboard()
            } else {
                stepper.setError()
            }
        })

        viewModel.onError.observe(viewLifecycleOwner, Observer { showError(rootView, it) })

        viewModel.onCreate()

    }

    private fun promptBiometric() {

        val biometricManager = BiometricManager.from(applicationContext)

        if (biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS) {

            val prompt = BiometricHelper.instanceOfBiometricPrompt(this,
                onCancel = {
                    biometricAuthSuccess(false)
                },
                onSuccess = {
                    biometricAuthSuccess(true)
                })

            prompt.authenticate(getPromptInfo())

            return
        }

        Toaster.show(applicationContext, getString(R.string.err_fingerprint_setup_system))
        //biometricAuthSuccess(false)
    }

    private fun biometricAuthSuccess(success: Boolean) {
        viewModel.biometricAuthSuccess(success)

        if (!success) {
            //promptBiometric()
            return
        }

        navigateToDashboard()
    }

    private fun navigateToDashboard() {
        NavMgr().pushFragment(activity as BaseActivity, HomeFragment.newInstance())
    }

    private fun getPromptInfo(): BiometricPrompt.PromptInfo {
        return BiometricPrompt.PromptInfo.Builder()
            .setTitle(getString(R.string.msg_fingerprint_prompt_title))
            .setSubtitle(getString(R.string.msg_fingerprint_prompt_subtitle))
            .setDescription(getString(R.string.msg_fingerprint_prompt_description))
            .setNegativeButtonText(getString(R.string.msg_fingerprint_prompt_neg_button))
            .build()
    }

}
