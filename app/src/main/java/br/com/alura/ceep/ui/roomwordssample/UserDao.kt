package br.com.alura.ceep.ui.roomwordssample

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM User WHERE email = :adress AND password = :key ORDER BY email ASC")
    fun getUser(adress: String, key: String): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun putUser(user: User): Long
}
