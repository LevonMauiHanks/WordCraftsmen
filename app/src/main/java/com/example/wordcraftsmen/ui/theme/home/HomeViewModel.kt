package com.example.wordcraftsmen.ui.theme.home

import androidx.lifecycle.ViewModel
import com.example.wordcraftsmen.domain.WordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val wordRepository: WordRepository,
) : ViewModel()