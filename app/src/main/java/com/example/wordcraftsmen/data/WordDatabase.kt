package com.example.wordcraftsmen.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Word::class],
    version = 1,
)
abstract class WordDatabase : RoomDatabase() {
    abstract fun noteDao(): WordDao

    companion object {
        val DB_NAME = "words_db"
    }
}