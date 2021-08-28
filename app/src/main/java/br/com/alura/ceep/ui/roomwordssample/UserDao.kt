package br.com.alura.ceep.ui.roomwordssample

import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM User ORDER BY email ASC")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM User WHERE email = :adress AND password = :key ORDER BY email ASC")
    fun getUser(adress: String, key: String): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(user: User): Long

    @Delete
    fun deleteUser(user: User): Int

}
