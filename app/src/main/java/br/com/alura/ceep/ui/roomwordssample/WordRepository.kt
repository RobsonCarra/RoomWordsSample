package br.com.alura.ceep.ui.roomwordssample

class WordRepository(private val wordDao: WordDao) {

  fun get() = wordDao.getAlphabetizedWords()

  fun getByName(name: String) = wordDao.getAlphabetizedWordsByName(name)

  fun add(word: Word): Boolean {
    wordDao.insert(word)
    return true
  }

}