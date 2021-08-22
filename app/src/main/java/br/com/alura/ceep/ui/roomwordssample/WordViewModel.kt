package br.com.alura.ceep.ui.roomwordssample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(private val repository: WordRepository) : ViewModel() {

    val list = MutableLiveData<List<Word>>()
    val filteredByName = MutableLiveData<List<Word>>()
    val filteredBySize = MutableLiveData<List<Word>>()
    val filteredByDecription = MutableLiveData<List<Word>>()
    val filteredByPrice = MutableLiveData<List<Word>>()

    fun getAll() {
        viewModelScope.launch(Dispatchers.IO) {
            val words = repository.get()
            list.postValue(words)
        }
    }

    fun getByName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val words = repository.getByName(name)
            filteredByName.postValue(words)
        }
    }

    fun getBySize(number: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val numbers = repository.getBySize(number)
            filteredBySize.postValue(numbers)
        }
    }

    fun getByDescription(information: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val informations = repository.getByDescription(information)
            filteredByDecription.postValue(informations)
        }

    }

    fun getByPrice(cost: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            val total = repository.getByPrice(cost)
            filteredByPrice.postValue(total)
        }

    }


    fun add(word: Word) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(word)
        }
    }

    class WordViewModelFactory(private val repository: WordRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return WordViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}