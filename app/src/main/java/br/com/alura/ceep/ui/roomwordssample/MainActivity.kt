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
        WordViewModelFactory(
            (application as WordsApplication).wordRepository,
            (application as WordsApplication).userRepository
        )
    }

    private lateinit var editSearch: TextInputEditText
    private lateinit var putPassword: TextInputEditText
    private lateinit var putEmail: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editSearch = findViewById(R.id.texto)
        putPassword = findViewById(R.id.password_input)
        putEmail = findViewById(R.id.email_input)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        wordViewModel.wordList.observe(this) { words ->
            adapter.wordList.addAll(words)
            adapter.notifyDataSetChanged()
        }

        wordViewModel.filteredByUser.observe(this) { users ->
            if (users.isNotEmpty()) {
                Toast.makeText(this, "User already exist", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "User saved", Toast.LENGTH_SHORT).show()
                val email = putEmail.text.toString()
                val password = putPassword.text.toString()
                val user = User(email, password)
                wordViewModel.addUser(user)
            }
        }
        wordViewModel.filteredByName.observe(this) { words ->
            if (words.isNotEmpty()) {
//                val firstWord = words.first()
                Toast.makeText(this, "Sucess", Toast.LENGTH_SHORT).show()
            } else {
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
            wordViewModel.addUser(User("fleury", "robson"))
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
            val email = putEmail.text.toString()
            val password = putPassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                wordViewModel.getByUser(email, password)
            } else {
                Toast.makeText(this, "Invalid login", Toast.LENGTH_SHORT).show()
            }
        }
        fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
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
}


