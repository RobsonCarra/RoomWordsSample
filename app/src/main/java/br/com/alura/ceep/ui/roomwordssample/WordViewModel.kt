package br.com.alura.ceep.ui.roomwordssample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(
    private val repository: WordRepository,
    private val repository2: UserRepository

) : ViewModel() {
    val list = MutableLiveData<List<Word>>()
    val filteredByName = MutableLiveData<List<Word>>()
    val filteredBySize = MutableLiveData<List<Word>>()
    val filteredByDecription = MutableLiveData<List<Word>>()
    val filteredByPrice = MutableLiveData<List<Word>>()
    val filteredByEmail = MutableLiveData<List<User>>()
    val filteredByPassword = MutableLiveData<List<User>>()

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

    fun getByEmail(adress: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val adresses = repository2.getByEmail(adress)
            filteredByEmail.postValue(adresses)
        }
    }

    fun getByPassword(key: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val keys = repository2.getByPassword(key)
            filteredByPassword.postValue(keys)
        }
    }

    fun add2(user: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository2.add2(user)
        }
    }

    class WordViewModelFactory(
        private val repository: WordRepository,
        private val repository2: UserRepository
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return WordViewModel(repository, repository2) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}