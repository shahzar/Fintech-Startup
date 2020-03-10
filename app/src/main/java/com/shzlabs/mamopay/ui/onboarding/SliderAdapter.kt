package com.shzlabs.mamopay.ui.onboarding

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.shzlabs.mamopay.R
import com.shzlabs.mamopay.data.model.IntroSliderItemModel

class SliderAdapter : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    var items : MutableList<IntroSliderItemModel> = mutableListOf()

    init {
        items.add(IntroSliderItemModel(0, "https://i.ibb.co/Sdgnfgp/Onboarding-1.png", "Connect your bank account"))
        items.add(IntroSliderItemModel(0, "https://i.ibb.co/4sdHVD2/Onboarding-2.png", "Send money instantly"))
        items.add(IntroSliderItemModel(0, "https://i.ibb.co/k24jC2r/Onboarding-3.png", "No fees, No IBANs, No Cash"))
    }

    fun precacheImages(context: Context) {
        //TODO: interviewer review
        items.forEach {
            Glide.with(context)
                .load(it.imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .preload()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_slider, parent, false)
        return SliderViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bind(items[position])
    }


    class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(model: IntroSliderItemModel) {
            itemView.findViewById<TextView>(R.id.text).text = model.introText
            Glide.with(itemView.context)
                .load(model.imageUrl)
                .placeholder(R.drawable.ic_onboarding_1)
                .into(itemView.findViewById(R.id.image))
        }
    }
}