package br.com.alura.ceep.ui.roomwordssample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class EmailListAdapter(
    val onRemoveButtonClick: (user: User) -> Unit,
    val onItemClick: (user: User, position: Int) -> Unit,
    val onStatusButtonClick: (user: User) -> Unit
) :
    RecyclerView.Adapter<EmailViewHolder>() {
    var list = ArrayList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return EmailViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        holder.bind(list.get(position))
        holder.itemView.setOnClickListener {
            onItemClick(list.get(position), position)
        }

        holder.remove.setOnClickListener {
            onRemoveButtonClick(list.get(position))
        }

        holder.status.setOnClickListener {
            onStatusButtonClick(list.get(position))
        }
    }

    override fun getItemCount(): Int {
        return list.count()
    }

}
