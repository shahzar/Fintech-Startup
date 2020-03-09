package com.shzlabs.mamopay.util.common

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator

object HapticUtil {

    private const val DURATION_BUTTON_PRESS = 20L
    private const val DURATION_BUTTON_SUCCESS = 40L
    private const val DURATION_BUTTON_ERROR = 300L

    private fun vibrate(context: Context, duration: Long) {
        val v = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        v.vibrate(duration)
    }

    fun numCodePress(context: Context) {
        vibrate(context, DURATION_BUTTON_PRESS)
    }

    fun numCodeSuccess(context: Context) {
        vibrate(context, DURATION_BUTTON_SUCCESS)
    }

    fun numCodeFailure(context: Context) {
        vibrate(context, DURATION_BUTTON_ERROR)
    }



}