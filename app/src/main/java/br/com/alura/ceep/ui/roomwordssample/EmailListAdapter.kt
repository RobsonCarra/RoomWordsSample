package br.com.alura.ceep.ui.roomwordssample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class EmailListAdapter(private val onItemClicked: (position: Int) -> Unit) :
    RecyclerView.Adapter<EmailViewHolder>() {

    var emailList = ArrayList<String>()
    var passwordList = ArrayList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return EmailViewHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        holder.bind(emailList.get(position))
    }

//    fun getItem(position: Int): User {
//        return emailList.get(position)
//    }
//
//    fun getItemFirst(): User {
//        return emailList.first()
//    }
//
//    fun getItemLast(): User {
//        return emailList.last()
//    }

    override fun getItemCount(): Int {
        return emailList.count()
    }


}
