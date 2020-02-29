package com.shzlabs.mamopay.ui.main.dashboard

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.shzlabs.mamopay.R
import com.shzlabs.mamopay.di.components.AppComponent
import com.shzlabs.mamopay.ui.base.BaseFragment
import kotlinx.android.synthetic.main.dashboard_bottomsheet_layout.*

class DashboardFragment : BaseFragment() {

    val transactionsAdapter = TransactionsAdapter()

    companion object {
        fun newInstance() = DashboardFragment()
    }

    private lateinit var viewModel: DashboardViewModel

    override fun injectDependencies(diComponent: AppComponent) = diComponent.inject(this)

    override fun provideLayoutId(): Int = R.layout.dashboard_fragment

    override fun initView(view: View) {

        transaction_list.layoutManager = LinearLayoutManager(rootView.context)
        transaction_list.adapter = transactionsAdapter

    }

    override fun setupObservers() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DashboardViewModel::class.java)

        //viewModel.sampleData.observe(viewLifecycleOwner, Observer { welcome_text.text = it.title })
        viewModel.transactionData.observe(viewLifecycleOwner, Observer {
            transactionsAdapter.updateItems(it)
        })

        viewModel.onError.observe(viewLifecycleOwner, Observer { showError(rootView, it) })

        viewModel.getTransactionData()
    }



}
