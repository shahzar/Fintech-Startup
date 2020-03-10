package com.shzlabs.mamopay.util.common

import com.shzlabs.mamopay.data.model.IntroSliderItemModel

object OnBoardingData {

    fun getSliderData() : List<IntroSliderItemModel> {
        val items : MutableList<IntroSliderItemModel> = mutableListOf()
        items.add(IntroSliderItemModel(0, "https://i.ibb.co/Sdgnfgp/Onboarding-1.png", "Connect your bank account"))
        items.add(IntroSliderItemModel(0, "https://i.ibb.co/4sdHVD2/Onboarding-2.png", "Send money instantly"))
        items.add(IntroSliderItemModel(0, "https://i.ibb.co/k24jC2r/Onboarding-3.png", "No fees, No IBANs, No Cash"))
        return items
    }
}