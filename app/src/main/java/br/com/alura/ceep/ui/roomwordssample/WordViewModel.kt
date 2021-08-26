package br.com.alura.ceep.ui.roomwordssample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(
    private val wordRepository: WordRepository,
    private val userRepository: UserRepository

) : ViewModel() {
    val wordList = MutableLiveData<List<Word>>()
    val filteredByName = MutableLiveData<List<Word>>()
    val filteredBySize = MutableLiveData<List<Word>>()
    val filteredByDecription = MutableLiveData<List<Word>>()
    val filteredByPrice = MutableLiveData<List<Word>>()
    val filteredByUser = MutableLiveData<List<User>>()
    val added = MutableLiveData<Boolean>()
    val list = MutableLiveData<List<User>>()
    val position = MutableLiveData<List<Int>>()

    fun getAll() {
        viewModelScope.launch(Dispatchers.IO) {
            val words = wordRepository.get()
            wordList.postValue(words)
        }
    }

    fun getAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            val users = userRepository.get()
            list.postValue(users)
        }
    }

    fun getByName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val words = wordRepository.getByName(name)
            filteredByName.postValue(words)
        }
    }

    fun getBySize(number: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val numbers = wordRepository.getBySize(number)
            filteredBySize.postValue(numbers)
        }
    }

    fun getByDescription(information: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val informations = wordRepository.getByDescription(information)
            filteredByDecription.postValue(informations)
        }
    }

    fun getByPrice(cost: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            val total = wordRepository.getByPrice(cost)
            filteredByPrice.postValue(total)
        }
    }

    fun add(word: Word) {
        viewModelScope.launch(Dispatchers.IO) {
            wordRepository.add(word)
        }
    }

    fun getByUser(adress: String, key: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val users = userRepository.getByUser(adress, key)
            filteredByUser.postValue(users)
        }
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            val saved = userRepository.add(user)
            if (saved) {
                added.postValue(true)
            }
        }
    }

    class WordViewModelFactory(
        private val wordRepository: WordRepository,
        private val userRepository: UserRepository
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return WordViewModel(wordRepository, userRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}