package com.shzlabs.mamopay.ui.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.shzlabs.mamopay.R
import com.shzlabs.mamopay.util.common.HapticUtil

class StepLayout(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private var stepCompleted: (() -> Unit)? = null
    private val colorActive = R.color.black100
    private val colorStateDefault = R.color.black20
    private val colorStateSuccess = R.color.success
    private val colorStateError = R.color.failure

    private var isInitialized = false

    private var viewList: List<View> = listOf()


    init {
        View.inflate(context, R.layout.signin_progress_layout, this)

        val line1: View = findViewById<View>(R.id.line1)
        val line2: View = findViewById<View>(R.id.line2)
        val line3: View = findViewById<View>(R.id.line3)
        val line4: View = findViewById<View>(R.id.line4)

        viewList = mutableListOf(line1, line2, line3, line4)

    }

    fun onStepCompleteListener(onComplete: ()->Unit) {
        stepCompleted = onComplete
    }

    fun resetSteps() {
        for (v:View in viewList) {
            v.setBackgroundColor(context.resources.getColor(colorStateDefault))
        }
    }

    fun setSuccess() {
        for (v:View in viewList) {
            v.setBackgroundColor(context.resources.getColor(colorStateSuccess))
        }
        HapticUtil.numCodeSuccess(context)
    }

    fun setError() {
        for (v:View in viewList) {
            v.setBackgroundColor(context.resources.getColor(colorStateError))
        }
        HapticUtil.numCodeFailure(context)
    }

    fun setActiveCount(count: Int) {
        for ((index, v) in viewList.withIndex()) {
            if (index < count) {
                v.setBackgroundColor(context.resources.getColor(colorActive))
            } else {
                v.setBackgroundColor(context.resources.getColor(colorStateDefault))
            }
        }

        if(!isInitialized) {
            isInitialized = true
            return
        }

        if (count >= viewList.size) {
            stepCompleted?.invoke()
        }
    }


}