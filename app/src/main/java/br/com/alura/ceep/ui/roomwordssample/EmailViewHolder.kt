package br.com.alura.ceep.ui.roomwordssample

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val emailItemView: TextView = itemView.findViewById(R.id.textView)
    val remove: ImageView = itemView.findViewById(R.id.delete_button)
    fun bind(text: String?) {
        emailItemView.text = text
    }

}