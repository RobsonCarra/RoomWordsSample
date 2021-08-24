package br.com.alura.ceep.ui.roomwordssample

class UserRepository(private val userDao: UserDao) {

    fun getByUser(adress: String, key: String) = userDao.getUser(adress, key)

    fun add(user: User): Boolean {
        userDao.putUser(user)
        return true
    }

}