package com.shzlabs.mamopay.util.display

import android.content.res.Resources

object DisplaySizeUtil {

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().getDisplayMetrics().density) as Int
    }

    fun pxToDp(px: Int): Int {
        return (px / Resources.getSystem().getDisplayMetrics().density) as Int
    }

}