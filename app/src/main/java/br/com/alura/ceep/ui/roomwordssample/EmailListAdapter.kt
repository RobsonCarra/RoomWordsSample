package br.com.alura.ceep.ui.roomwordssample

import android.content.Intent.getIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class EmailListAdapter(
    private val onRemoveButtonClick: (user: User, position: Int) -> Unit,
    private val onItemClick: (user: User, position: Int) -> Unit
) :
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
            onItemClick(list.get(position), position)
        }

        holder.remove.setOnClickListener {
            onRemoveButtonClick(list.get(position), position)
//            list.removeAt(position)
//            notifyItemRemoved(position)
//            notifyItemRangeChanged(position, list.size)
        }
    }

    override fun getItemCount(): Int {
        return list.count()
    }


//    fun deleteItem(position: Int) {
//        list.removeAt(position)
//        notifyDataSetChanged()
//    }

}
