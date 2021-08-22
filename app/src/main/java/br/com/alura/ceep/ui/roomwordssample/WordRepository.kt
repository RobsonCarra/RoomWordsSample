package br.com.alura.ceep.ui.roomwordssample

class WordRepository(private val wordDao: WordDao) {

    fun get() = wordDao.getAlphabetizedWords()

    fun getByName(name: String) = wordDao.getAlphabetizedWordsByName(name)

    fun getBySize(number: Int) = wordDao.getSizeFromWord(number)

    fun getByDescription(information: String) = wordDao.getDescriptionFromWord(information)

    fun getByPrice(cost: Double) = wordDao.getPrice(cost)

    fun add(word: Word): Boolean {
        wordDao.insert(word)
        return true
    }

}