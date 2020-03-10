package com.shzlabs.mamopay.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.shzlabs.mamopay.R
import com.shzlabs.mamopay.di.ViewModelFactory
import com.shzlabs.mamopay.di.components.AppComponent
import javax.inject.Inject

abstract class BaseFragment: Fragment() {

    lateinit var rootView:View

    @Inject
    lateinit var appContext: Context

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(getDiComponent())
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(provideLayoutId(), container, false)
        setupObservers()
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //setupObservers()
    }

    fun setToolbarTitle(title: String) {
        val titleTv = rootView.findViewById<TextView>(R.id.toolbar_title)
        titleTv.text = title
        val back = rootView.findViewById<View>(R.id.back)
        back.setOnClickListener { goBack() }
    }

    fun goBack() {
        (activity as BaseActivity).onBackPressed()
    }

    protected open fun setupObservers() {}

    fun showError(view: View, msg: String) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show()
    }

    private fun getDiComponent() : AppComponent {
        return (activity as BaseActivity).getDiComponent()
    }

    protected abstract fun injectDependencies(diComponent: AppComponent)

    @LayoutRes
    protected abstract fun provideLayoutId(): Int

    protected abstract fun initView(view:View)

}