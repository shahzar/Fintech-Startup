package com.shzlabs.mamopay.ui.onboarding

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shzlabs.mamopay.R
import com.shzlabs.mamopay.data.model.IntroSliderItemModel

class SliderAdapter : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    var items : MutableList<IntroSliderItemModel> = mutableListOf()

    init {
        items.add(IntroSliderItemModel(R.drawable.ic_onboarding_1, "Connect your bank account"))
        items.add(IntroSliderItemModel(R.drawable.ic_onboarding_2, "Send money instantly"))
        items.add(IntroSliderItemModel(R.drawable.ic_onboarding_3, "No fees, No IBANs, No Cash"))
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
            itemView.findViewById<ImageView>(R.id.image).setImageResource(model.imageRef)
        }
    }
}