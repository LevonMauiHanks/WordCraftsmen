package com.example.wordcraftsmen.ui.theme.learned_notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wordcraftsmen.data.Word
import com.example.wordcraftsmen.domain.WordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LearnedNotesViewModel @Inject constructor(
    val wordRepository: WordRepository
) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _words = MutableStateFlow<List<Word>?>(emptyList())

    val words = searchText
        .combine(_words) { text, words ->
            if (text.isBlank()) {
                getAllWords()
            }
            words?.filter { word ->
                word.name.uppercase().contains(text.trim().uppercase())
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _words.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    private fun getAllWords() {
        viewModelScope.launch {
            _words.value = wordRepository.getAllWords()?.first()?.filter { it.isLearned }
        }
    }
}