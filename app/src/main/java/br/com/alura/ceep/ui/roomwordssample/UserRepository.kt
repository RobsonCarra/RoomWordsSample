package br.com.alura.ceep.ui.roomwordssample

class UserRepository(private val userDao: UserDao) {

    fun getByEmail(adress: String) = userDao.getEmail(adress)

    fun getByPassword(key: String) = userDao.getPassword(key)

    fun add2(user: String): Boolean {
        userDao.put(user)
        return true
    }

}