package com.shzlabs.mamopay.ui.onboarding.details

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.textfield.TextInputLayout
import com.shzlabs.mamopay.util.navigation.NavHelper
import com.shzlabs.mamopay.R
import com.shzlabs.mamopay.di.components.AppComponent
import com.shzlabs.mamopay.ui.base.BaseActivity
import com.shzlabs.mamopay.ui.base.BaseFragment
import com.shzlabs.mamopay.ui.signin.setup.SignInSetupFragment
import com.shzlabs.mamopay.util.extensions.afterTextChanged
import kotlinx.android.synthetic.main.on_boarding_details_fragment.*

class OnBoardingDetailsFragment : BaseFragment() {


    companion object {
        fun newInstance() = OnBoardingDetailsFragment()
    }

    private lateinit var viewModel: OnBoardingDetailsViewModel

    override fun injectDependencies(diComponent: AppComponent) = diComponent.inject(this)

    override fun provideLayoutId(): Int = R.layout.on_boarding_details_fragment

    override fun initView(view: View) {

        setToolbarTitle(getString(R.string.page_title_confirm_name))

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

    }

    private fun confirmChanges() {
        val firstName = first_name_con.editText?.text.toString().trim()
        val lastName = last_name_con.editText?.text.toString().trim()

        if (!validateName(firstName, first_name_con) || !validateName(lastName, last_name_con)) {
            return
        }

        viewModel.setName(firstName, lastName)

        NavHelper
            .pushFragment(activity as BaseActivity, SignInSetupFragment.newInstance())
    }

    private fun validateName(name: String, textInputLayout: TextInputLayout): Boolean{
        var error: String? = null

        if (name.isEmpty()) {
            error = getString(R.string.err_no_empty)
        }

        if (name.contains(" ") || name.contains(Regex("[0123456789]"))) {
            error = getString(R.string.err_name_constraint_mismatch)
        }

        error?.let {
            textInputLayout.error = error
            confirm_button.isEnabled = false
            return false
        }

        textInputLayout.error = null
        textInputLayout.isErrorEnabled = false
        confirm_button.isEnabled = true
        return true
    }


}
