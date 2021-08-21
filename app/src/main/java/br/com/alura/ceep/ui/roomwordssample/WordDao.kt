package br.com.alura.ceep.ui.roomwordssample

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: Word): Long
}