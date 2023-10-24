package com.example.wordcraftsmen.data

import android.content.Context
import androidx.compose.runtime.currentRecomposeScope
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class PrepopulateRoomCallback @Inject constructor(
    private val context: Context,
    private val provider: Provider<WordDao>
) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        CoroutineScope(Dispatchers.IO).launch {
            val jsonString: String =
                context.assets.open("words.json").bufferedReader().use { it.readText() }

            val data: List<Word> = Gson().fromJson(jsonString, Array<Word>::class.java).toList()

            data.forEach {
                provider.get().addWord(it)
            }
        }
    }
}