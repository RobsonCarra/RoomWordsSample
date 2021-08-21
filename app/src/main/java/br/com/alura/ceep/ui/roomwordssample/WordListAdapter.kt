package br.com.alura.ceep.ui.roomwordssample

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class WordListAdapter : RecyclerView.Adapter<WordViewHolder>() {

    var list = ArrayList<Word>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.word)
    }

    fun getItem(position: Int): Word {
        return list.get(position)
    }

    fun getItemFirst(): Word {
        return list.first()
    }

    fun getItemLast(): Word {
        return list.last()
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<Word>() {
            override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
                return oldItem.word == newItem.word
            }
        }
    }
}
