package com.example.wordcraftsmen.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wordcraftsmen.utils.EMPTY_STRING
import com.example.wordcraftsmen.utils.INVALID_ID
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "word_table")
data class Word(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "word_id")
    val id: Long = INVALID_ID,
    val name: String = EMPTY_STRING,
    val translation: String = EMPTY_STRING,
    val isLearned: Boolean = false
)