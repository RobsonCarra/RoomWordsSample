package br.com.alura.ceep.ui.roomwordssample

import android.util.Size
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WordDao {

    @Query("SELECT * FROM Word ORDER BY word ASC")
    fun getAlphabetizedWords(): List<Word>

    @Query("SELECT * FROM Word WHERE word = :name ORDER BY word ASC")
    fun getAlphabetizedWordsByName(name: String): List<Word>

    @Query("SELECT * FROM Word WHERE size = :number ORDER BY size ASC")
    fun getSizeFromWord(number: Int): List<Word>

    @Query("SELECT * FROM Word WHERE description = :information ORDER BY description ASC")
    fun getDescriptionFromWord(information: String): List<Word>

    @Query("SELECT * FROM Word WHERE price = :cost ORDER BY price ASC")
    fun getPrice(cost: Double): List<Word>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: Word): Long
}