package com.example.wordcraftsmen.data

import kotlinx.coroutines.flow.Flow

interface WordRepository {
    fun getAllWords(): Flow<List<Word>>?
    suspend fun getWord(wordId: Long): Word?
    suspend fun addWord(word: Word): Long
    suspend fun clear()
    suspend fun updateWord(newWord: Word)
    suspend fun getCurrentWord(): Word?
    suspend fun deleteWord(word: Word)
}