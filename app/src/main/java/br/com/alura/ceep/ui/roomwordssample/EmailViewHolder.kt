package br.com.alura.ceep.ui.roomwordssample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val emailItemView: TextView = itemView.findViewById(R.id.textView)
    fun bind(text: String?) {
        emailItemView.text = text
    }

    companion object {
        fun create(parent: ViewGroup): EmailViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
            return EmailViewHolder(view)
        }
    }
}