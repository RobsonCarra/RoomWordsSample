package br.com.alura.ceep.ui.roomwordssample

class UserRepository(private val userDao: UserDao) {

    fun get() = userDao.getAllUsers()

    fun getByUser(adress: String, key: String) =
        userDao.getUser(adress, key)

    fun add(user: User): Boolean {
        val id = userDao.save(user)
        return id > 0
    }

    fun delete(user: User): Boolean {
        val id = userDao.deleteUser(user)
        return id > 0
    }

    fun save(user: User): Boolean {
        val id = userDao.save(user)
        return id > 0
    }
}