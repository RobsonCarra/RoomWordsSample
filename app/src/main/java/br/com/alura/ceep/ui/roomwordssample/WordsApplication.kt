package br.com.alura.ceep.ui.roomwordssample

import android.app.Application

class WordsApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    private val database by lazy { WordRoomDatabase.getDatabase(this) }
    private val database2 by lazy { WordRoomDatabase.getDatabase(this) }
    val repository by lazy {
        WordRepository(database.wordDao())
    }
    val repository2 by lazy {
        UserRepository(database2.userDao())
    }
}
