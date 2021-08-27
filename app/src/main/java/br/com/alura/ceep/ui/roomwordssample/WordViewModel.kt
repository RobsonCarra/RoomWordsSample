package br.com.alura.ceep.ui.roomwordssample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class WordViewModel(
    private val userRepository: UserRepository

) : ViewModel() {
    val filteredByUser = MutableLiveData<List<User>>()
    val added = MutableLiveData<Boolean>()
    val deleted = MutableLiveData<User>()
    val list = MutableLiveData<List<User>>()

    fun getAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            val users = userRepository.get()
            list.postValue(users)
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

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            val excluded = userRepository.delete(user)
            if (excluded) {
                deleted.postValue(user)
            }
        }
    }

    class WordViewModelFactory(
        private val userRepository: UserRepository
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return WordViewModel(userRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}