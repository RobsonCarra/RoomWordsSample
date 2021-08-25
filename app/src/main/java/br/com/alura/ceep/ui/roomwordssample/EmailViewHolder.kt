package br.com.alura.ceep.ui.roomwordssample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class EmailViewHolder(
    itemView: View,
    private val onItemClicked: (position: Int) -> Unit
) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private val emailItemView: TextView = itemView.findViewById(R.id.textView)
    fun bind(text: String?) {
        emailItemView.text = text
    }

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val position = adapterPosition
        onItemClicked(position)
    }

//    companion object {
//        fun create(parent: ViewGroup): EmailViewHolder {
//            val view: View = LayoutInflater.from(parent.context)
//                .inflate(R.layout.recyclerview_item, parent, false)
//            return EmailViewHolder(view, onItemClicked)
//        }
//    }
}