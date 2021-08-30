package br.com.alura.ceep.ui.roomwordssample

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val emailItemView: TextView = itemView.findViewById(R.id.textView)
    val remove: ImageView = itemView.findViewById(R.id.delete_button)
    var status: Button = itemView.findViewById(R.id.active_inactive_button)

    fun bind(user: User) {
        emailItemView.text = user.email

        if (!user.status) {
            status.text = "Ativar";
        } else {
            status.text = "Desativar";
        }
    }
}