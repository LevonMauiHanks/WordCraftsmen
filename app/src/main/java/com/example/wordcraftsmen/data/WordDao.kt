package com.example.wordcraftsmen.data

import android.provider.ContactsContract
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("SELECT * FROM word_table")
    fun getAllWords(): Flow<List<Word>>?

    @Query("SELECT * FROM word_table WHERE word_id = :wordId")
    suspend fun getWord(wordId: Long): Word?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWord(word: Word): Long

    @Query("DELETE FROM word_table")
    suspend fun clear()

    @Update
    suspend fun updateWord(newWord: Word)

    @Query("SELECT * FROM word_table ORDER BY word_id DESC LIMIT 1")
    suspend fun getCurrentWord(): Word?

    @Delete
    suspend fun deleteWord(word: Word)
}