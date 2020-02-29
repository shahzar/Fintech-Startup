package com.shzlabs.mamopay.ui.main.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.shzlabs.mamopay.R
import com.shzlabs.mamopay.data.model.TransactionModel

class TransactionsAdapter : RecyclerView.Adapter<TransactionsAdapter.CustomViewHolder>() {

    private var items: MutableList<TransactionModel> = mutableListOf()

    fun updateItems(items: List<TransactionModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transaction_history, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount() =
        items.count()

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val item = items[position]
        val nameslug = item.name.replace(" ", "")


        val transactionStatus = if (item.transactionState == TransactionModel.TYPE_RECEIVED) {
            holder.itemView.context.getString(R.string.label_received)
        } else {
            holder.itemView.context.getString(R.string.label_sent)
        }

        holder.name.text = item.name
        holder.status.text = transactionStatus
        holder.currency.text = item.currency
        holder.amount.text = items[position].amount.toString()

        Glide.with(holder.itemView.context)
            .load("https://i.pravatar.cc/100?u=$nameslug")
            .apply(RequestOptions.circleCropTransform())
            .into(holder.profileImage)
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val status: TextView = itemView.findViewById(R.id.status)
        val currency: TextView = itemView.findViewById(R.id.currency)
        val amount: TextView = itemView.findViewById(R.id.amount)
        val profileImage: ImageView = itemView.findViewById(R.id.profile_image)
    }
}