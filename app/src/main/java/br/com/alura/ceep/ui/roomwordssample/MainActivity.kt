package br.com.alura.ceep.ui.roomwordssample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.ceep.ui.roomwordssample.WordViewModel.WordViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private val newWordActivityRequestCode = 1
    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as WordsApplication).repository)

    }
    private val wordViewModel2: WordViewModel by viewModels {
        WordViewModelFactory((application as WordsApplication).repository2)

    }

    private lateinit var editSearch: TextInputEditText
    private lateinit var putpassword: TextInputEditText
    private lateinit var putemails: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editSearch = findViewById(R.id.texto)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        wordViewModel.list.observe(this) { words ->
            adapter.list.addAll(words)
            adapter.notifyDataSetChanged()
        }

        wordViewModel.filteredByName.observe(this) { words ->
            if (words.isNotEmpty()) {
                Toast.makeText(this, "Sucess", Toast.LENGTH_SHORT).show()

            } else {
                val firstWord = words.first()
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()

            }
            // escreve min. toast e aperta TAB
//                Toast.makeText(this, firstWord.word, Toast.LENGTH_SHORT).show()
        }
//        wordViewModel.filteredBySize.observe(this) { numbers ->
//            val firstNumbers = numbers.first()
//            // escreve min. toast e aperta TAB
//            Toast.makeText(this, firstNumbers.word, Toast.LENGTH_SHORT).show()
//        }
//        wordViewModel.filteredByPrice.observe(this) { Prices ->
//            val firstPrice = Prices.first()
//            // escreve min. toast e aperta TAB
//            Toast.makeText(this, firstPrice.price.toString(), Toast.LENGTH_SHORT).show()
//        }
//        wordViewModel.filteredByName.observe(this) { words ->
//            val indentifySearch = words.first()}

        lifecycleScope.launch {
            wordViewModel.add(Word("Banana", 6, "Verde", 2.0))
            wordViewModel.add(Word("Uva", 3, "Big", 3.89))
            wordViewModel.getAll()
            //wordViewModel.getByName("Banana")
            wordViewModel.getBySize(6)
            wordViewModel.getByDescription("Verde")
            wordViewModel.getByPrice(2.0)
        }
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
        val searchBtn = findViewById<Button>(R.id.searchBtn)
        searchBtn.setOnClickListener {
            val word = editSearch.text.toString()
            wordViewModel.getByName(word)
        }

        val saveBtn = findViewById<Button>(R.id.saveBtn)
        saveBtn.setOnClickListener {
            val email = putemails.text.toString()
            val password = putpassword.text.toString()
            wordViewModel.add2(email)
            wordViewModel.add2(password)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)
        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let { reply ->
                // val word = Word(reply.)
                // wordViewModel.insert(word)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}


