package br.com.alura.ceep.ui.roomwordssample

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM User WHERE email = :adress ORDER BY email ASC")
    fun getEmail(adress: String): List<User>

    @Query("SELECT * FROM User WHERE password = :key ORDER BY password ASC")
    fun getPassword(key: String): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun put(user: User): Long
}