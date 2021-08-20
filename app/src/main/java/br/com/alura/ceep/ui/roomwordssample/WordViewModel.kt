package br.com.alura.ceep.ui.roomwordssample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(private val repository: WordRepository) : ViewModel() {

  val list = MutableLiveData<List<Word>>()
  val filtered = MutableLiveData<Word>()

  fun getAll() {
    viewModelScope.launch(Dispatchers.IO) {
      val words = repository.get()
      list.postValue(words)
    }
  }

  fun getEgg() {
    viewModelScope.launch(Dispatchers.IO) {
      val words = repository.get()
      val egg = words.first { it.word == "Egg" }
      filtered.postValue(egg)
    }
  }

  fun add(word: Word) {
    viewModelScope.launch(Dispatchers.IO) {
      repository.add(word)
    }
  }

  class WordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
      if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
        @Suppress("UNCHECKED_CAST")
        return WordViewModel(repository) as T
      }
      throw IllegalArgumentException("Unknown ViewModel class")
    }
  }
}