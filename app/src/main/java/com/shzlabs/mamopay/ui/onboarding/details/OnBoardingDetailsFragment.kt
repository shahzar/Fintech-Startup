package com.shzlabs.mamopay.ui.onboarding.details

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.textfield.TextInputLayout
import com.shzlabs.mamopay.NavMgr
import com.shzlabs.mamopay.R
import com.shzlabs.mamopay.di.components.AppComponent
import com.shzlabs.mamopay.ui.base.BaseActivity
import com.shzlabs.mamopay.ui.base.BaseFragment
import com.shzlabs.mamopay.ui.signin.setup.SignInSetupFragment
import com.shzlabs.mamopay.util.display.Toaster
import com.shzlabs.mamopay.util.extensions.afterTextChanged
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.on_boarding_details_fragment.*
import javax.inject.Inject

class OnBoardingDetailsFragment : BaseFragment() {

    @Inject
    lateinit var appContext: Context

    companion object {
        fun newInstance() = OnBoardingDetailsFragment()
    }

    private lateinit var viewModel: OnBoardingDetailsViewModel

    override fun injectDependencies(diComponent: AppComponent) = diComponent.inject(this)

    override fun provideLayoutId(): Int = R.layout.on_boarding_details_fragment

    override fun initView(view: View) {
        confirm_button.setOnClickListener {
            confirmChanges()
        }

        first_name_con.editText?.afterTextChanged {
            validateName(it, first_name_con)
        }

        last_name_con.editText?.afterTextChanged {
            validateName(it, last_name_con)
        }

    }

    override fun setupObservers() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(OnBoardingDetailsViewModel::class.java)

        viewModel.firstname.observe(viewLifecycleOwner, Observer {
            first_name_con.editText?.setText(it)
        })

        viewModel.lastname.observe(viewLifecycleOwner, Observer {
            last_name_con.editText?.setText(it)
        })

        viewModel.onError.observe(viewLifecycleOwner, Observer { showError(rootView, it) })

        viewModel.onCreate()

    }

    private fun confirmChanges() {
        val firstName = first_name_con.editText?.text.toString().trim()
        val lastName = last_name_con.editText?.text.toString().trim()

        if (!validateName(firstName, first_name_con) || !validateName(lastName, last_name_con)) {
            return
        }

        viewModel.setName(firstName, lastName)

        NavMgr().pushFragment(activity as BaseActivity, SignInSetupFragment.newInstance())
    }

    private fun validateName(name: String, textInputLayout: TextInputLayout): Boolean{
        var error: String? = null

        if (name.isEmpty()) {
            error = "Cannot be empty"
        }

        if (name.contains(" ") || name.contains(Regex("[0123456789]"))) {
            error = "Use letters only without spaces"
        }

        error?.let {
            textInputLayout.error = error
            return false
        }

        textInputLayout.error = null
        textInputLayout.isErrorEnabled = false
        return true
    }


}
