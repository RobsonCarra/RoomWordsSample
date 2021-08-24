package br.com.alura.ceep.ui.roomwordssample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [Word::class, User::class], version = 1, exportSchema = false)

abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao
    abstract fun userDao(): UserDao

    private class WordDatabaseCallback() : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                GlobalScope.launch(Dispatchers.IO) {
                    val wordDao = database.wordDao()
                    database.clearAllTables()
                    wordDao.insert(Word("Hello", 5, "guys", 2.50))
                    wordDao.insert(Word("World!", 6, "War", 3.00))
                    wordDao.insert(Word("Robson!", 7, "Car", 2.35))
                }
            }
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(
            context: Context
        ): WordRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "Word_database5"
                ).addCallback(WordDatabaseCallback()).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}