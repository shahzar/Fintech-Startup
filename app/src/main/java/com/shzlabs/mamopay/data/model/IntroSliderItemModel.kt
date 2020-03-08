package com.shzlabs.mamopay.data.model

import com.google.gson.annotations.SerializedName

class IntroSliderItemModel(
    @SerializedName("image_ref")
    var imageRef: Int = 0,
    @SerializedName("intro_text")
    var introText: String = ""
)