package br.com.alura.ceep.ui.roomwordssample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class EmailListAdapter(private val onItemClicked: (user: User, position: Int) -> Unit) :
    RecyclerView.Adapter<EmailViewHolder>() {

    var list = ArrayList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return EmailViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        holder.bind(list.get(position).email)
        holder.itemView.setOnClickListener {
            onItemClicked(list.get(position), position)
        }
    }

    override fun getItemCount(): Int {
        return list.count()
    }
}
