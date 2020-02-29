package com.shzlabs.mamopay.util.logger

import android.util.Log

object Logger {

    fun d(tag: String, data: String) = Log.d(tag, data)

    fun e(tag: String, data: String) = Log.e(tag, data)

}