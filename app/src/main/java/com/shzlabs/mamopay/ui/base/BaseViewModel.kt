package com.shzlabs.mamopay.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext = job + Dispatchers.Main

    protected val _onError = MutableLiveData<String>()

    val onError: LiveData<String>
        get() = _onError

    fun <T> ioLaunch(block: suspend () -> T, onSuccess: (T) -> Unit = {}, onFailure: (t:Throwable) -> Unit = {}) = launch {

        runCatching {
            block.invoke()
        }
        .onFailure {
            _onError.value = it.message
            onFailure.invoke(it)
        }
        .onSuccess {
            onSuccess.invoke(it)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}