package br.com.alura.ceep.ui.roomwordssample

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val emailItemView: TextView = itemView.findViewById(R.id.textView)

    fun bind(text: String?) {
        emailItemView.text = text
    }
}