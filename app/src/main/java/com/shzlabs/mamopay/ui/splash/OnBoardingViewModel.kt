package com.shzlabs.mamopay.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shzlabs.mamopay.data.DataManager
import com.shzlabs.mamopay.data.model.SampleDataModel
import com.shzlabs.mamopay.di.components.AppComponent
import com.shzlabs.mamopay.ui.base.BaseViewModel
import javax.inject.Inject

class OnBoardingViewModel @Inject constructor (val dataManager: DataManager) : BaseViewModel(){

    private val _sampleData = MutableLiveData<SampleDataModel>()

    val sampleData: LiveData<SampleDataModel>
        get() = _sampleData

    fun getSampleData() {

        ioLaunch(
            block = {
                dataManager.getSampleData()
            },
            onSuccess = {
                _sampleData.value = it
            }
        )

    }

}
