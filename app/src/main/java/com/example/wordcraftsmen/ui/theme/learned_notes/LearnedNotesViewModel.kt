package com.example.wordcraftsmen.ui.theme.learned_notes

import androidx.lifecycle.ViewModel
import com.example.wordcraftsmen.domain.WordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LearnedNotesViewModel @Inject constructor(
    val wordRepository: WordRepository
) : ViewModel()