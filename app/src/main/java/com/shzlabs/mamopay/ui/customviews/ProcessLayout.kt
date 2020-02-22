package com.shzlabs.mamopay.ui.customviews

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.shzlabs.mamopay.R

class ProcessLayout(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    // Init all colors refs here

    private var viewList: List<View> = listOf()
    private var stepCounter: Int = 0

    init {
        View.inflate(context, R.layout.signin_progress_layout, this)

        val line1: View = findViewById<View>(R.id.line1)
        val line2: View = findViewById<View>(R.id.line2)
        val line3: View = findViewById<View>(R.id.line3)
        val line4: View = findViewById<View>(R.id.line4)

        viewList = mutableListOf(line1, line2, line3, line4)

    }

    fun incrementStep() {
        if (stepCounter < viewList.size) {
            viewList[stepCounter++].setBackgroundColor(context.resources.getColor(R.color.black100))
        }
    }

    fun resetProgress() {
        for (v:View in viewList) {
            v.setBackgroundColor(context.resources.getColor(R.color.black20))
            stepCounter = 0
        }
    }


}