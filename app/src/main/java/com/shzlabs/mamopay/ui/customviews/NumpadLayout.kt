package com.shzlabs.mamopay.ui.customviews

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.shzlabs.mamopay.R
import kotlinx.android.synthetic.main.numpad_layout.view.*

class NumpadLayout(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private var onNumberPress: ((num: Int)->Unit)? = null
    private var onDeletePress: (()->Unit)? = null
    private var onFingerprintPress: (()->Unit)? = null

    init {
        View.inflate(context, R.layout.numpad_layout, this)

        num1.setOnClickListener { buttonPress(it) }
        num2.setOnClickListener { buttonPress(it) }
        num3.setOnClickListener { buttonPress(it) }
        num4.setOnClickListener { buttonPress(it) }
        num5.setOnClickListener { buttonPress(it) }
        num6.setOnClickListener { buttonPress(it) }
        num7.setOnClickListener { buttonPress(it) }
        num8.setOnClickListener { buttonPress(it) }
        num9.setOnClickListener { buttonPress(it) }
        num0.setOnClickListener { buttonPress(it) }
        backspace.setOnClickListener { onDeletePress?.invoke() }
        fingerprint.setOnClickListener { onFingerprintPress?.invoke() }

        backspace.visibility = View.GONE
    }

    private fun buttonPress(view: View) {
        val value = when (view.id) {
            R.id.num1 -> 1
            R.id.num2 -> 2
            R.id.num3 -> 3
            R.id.num4 -> 4
            R.id.num5 -> 5
            R.id.num6 -> 6
            R.id.num7 -> 7
            R.id.num8 -> 8
            R.id.num9 -> 9
            R.id.num0 -> 0
            else -> 0
        }

        onNumberPress?.invoke(value)
    }

    fun showDeleteButton(show: Boolean) {
        backspace.visibility = if (show) View.VISIBLE else View.GONE
    }

    fun showFingerprintButton(show: Boolean) {
        fingerprint.visibility = if (show) View.VISIBLE else View.GONE
    }


    fun setOnNumberPressListener(onPress: (num: Int)->Unit) {
        onNumberPress = onPress
    }

    fun setOnDeletePressListener(onDelete: ()->Unit) {
        onDeletePress = onDelete
    }

    fun setOnFingerprintPressListener(onFingerprint: ()->Unit) {
        onFingerprintPress = onFingerprint
    }



}