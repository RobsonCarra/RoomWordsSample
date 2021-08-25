package br.com.alura.ceep.ui.roomwordssample

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
    private val viewModel: WordViewModel by viewModels {
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
        val adapter = EmailListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.emailList.observe(this) { emails ->
            adapter.emailList.addAll(emails)
            adapter.notifyDataSetChanged()
        }
        viewModel.added.observe(this) { saved ->
            if (saved) {
                Toast.makeText(this, "User saved", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.filteredByUser.observe(this) { users ->
            if (users.isNotEmpty()) {
                Toast.makeText(this, "User already exist", Toast.LENGTH_SHORT).show()
            } else {
                val email = putEmail.text.toString()
                val password = putPassword.text.toString()
                val user = User(email, password)
                viewModel.addUser(user)
            }
        }
        viewModel.filteredByName.observe(this) { words ->
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
            viewModel.add(Word("Banana", 6, "Verde", 2.0))
            viewModel.add(Word("Uva", 3, "Big", 3.89))
            viewModel.getAll()
            //wordViewModel.getByName("Banana")
            viewModel.getBySize(6)
            viewModel.getByDescription("Verde")
            viewModel.getByPrice(2.0)
            viewModel.addUser(User("fleury", "robson"))
        }
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
        val searchBtn = findViewById<Button>(R.id.searchBtn)
        searchBtn.setOnClickListener {
            val word = editSearch.text.toString()
            viewModel.getByName(word)
        }

        val saveBtn = findViewById<Button>(R.id.saveBtn)
        saveBtn.setOnClickListener {
            val email = putEmail.text.toString()
            val password = putPassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.getByUser(email, password)
            } else {
                Toast.makeText(this, "Invalid login", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


