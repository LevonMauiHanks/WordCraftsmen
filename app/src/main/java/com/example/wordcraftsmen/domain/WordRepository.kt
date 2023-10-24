package com.example.wordcraftsmen.domain

import com.example.wordcraftsmen.data.Word
import com.example.wordcraftsmen.data.WordDao
import com.example.wordcraftsmen.data.WordRepository
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) : WordRepository {
    override fun getAllWords(): Flow<List<Word>>? = wordDao.getAllWords()
    override suspend fun getWord(wordId: Long): Word? = wordDao.getWord(wordId)
    override suspend fun addWord(word: Word): Long = wordDao.addWord(word)
    override suspend fun clear() = wordDao.clear()
    override suspend fun updateWord(newWord: Word) = wordDao.updateWord(newWord)
    override suspend fun getCurrentWord(): Word? = wordDao.getCurrentWord()
    override suspend fun deleteWord(word: Word) = wordDao.deleteWord(word)
}