package br.com.alura.ceep.ui.roomwordssample

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class EmailListAdapter : RecyclerView.Adapter<EmailViewHolder>() {

    var emailList = ArrayList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        return EmailViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.email)
    }

    fun getItem(position: Int): User {
        return emailList.get(position)
    }

    fun getItemFirst(): User {
        return emailList.first()
    }

    fun getItemLast(): User {
        return emailList.last()
    }

    override fun getItemCount(): Int {
        return emailList.count()
    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.email == newItem.email
            }
        }
    }
}
