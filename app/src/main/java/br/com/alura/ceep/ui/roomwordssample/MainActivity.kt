package br.com.alura.ceep.ui.roomwordssample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.ceep.ui.roomwordssample.WordViewModel.WordViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

/*
1. criar no repositorio funcao para buscar por size
2. criar no viewmodel funcao para popular a lista filtered pelo tamanho
3. mostrar na acitivty num toast a palavra buscada pelo tamanho
 */
class MainActivity : AppCompatActivity() {

  private val newWordActivityRequestCode = 1
  private val wordViewModel: WordViewModel by viewModels {
    WordViewModelFactory((application as WordsApplication).repository)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
    val adapter = WordListAdapter()
    recyclerView.adapter = adapter
    recyclerView.layoutManager = LinearLayoutManager(this)
    wordViewModel.list.observe(this) { words ->
      adapter.list.addAll(words)
      adapter.notifyDataSetChanged()
    }
    wordViewModel.filtered.observe(this) { words ->
      val firstWord = words.first()
      // escreve min. toast e aperta TAB
      Toast.makeText(this, firstWord.word, Toast.LENGTH_SHORT).show()
    }
    wordViewModel.filtered.observe(this) { numbers ->
      val firstNumbers = numbers.first()
      // escreve min. toast e aperta TAB
      Toast.makeText(this, firstNumbers.word, Toast.LENGTH_SHORT).show()
    }
    lifecycleScope.launch {
      wordViewModel.add(Word("Banana", 6))
      wordViewModel.add(Word("Uva", 3))
      wordViewModel.getAll()
      wordViewModel.getByName("Banana")
      wordViewModel.getBySize(3)
    }
    val fab = findViewById<FloatingActionButton>(R.id.fab)
    fab.setOnClickListener {
      val intent = Intent(this@MainActivity, NewWordActivity::class.java)
      startActivityForResult(intent, newWordActivityRequestCode)
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

