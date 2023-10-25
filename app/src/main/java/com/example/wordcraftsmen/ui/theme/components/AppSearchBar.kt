package com.example.wordcraftsmen.ui.theme.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordcraftsmen.data.Word
import com.example.wordcraftsmen.ui.theme.learned_notes.LearnedNotesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSearchBar(
    viewModel: LearnedNotesViewModel,
    learnedWords: List<Word>,
    itemSelected: (Int) -> Unit
) {
    val searchText by viewModel.searchText.collectAsState()
    val words by viewModel.words.collectAsState()

    val isSearching = remember {
        mutableStateOf(false)
    }

    SearchBar(
        query = searchText,
        onQueryChange = viewModel::onSearchTextChange,
        onSearch = viewModel::onSearchTextChange,
        active = isSearching.value,
        onActiveChange = {
            isSearching.value = !isSearching.value
            viewModel.onSearchTextChange("")
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        enabled = true
    ) {
        LazyColumn(
            userScrollEnabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            words?.let { wordList ->
                items(wordList) { word ->
                    Text(
                        text = word.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(start = 20.dp)
                            .clickable {
                                println(word.name)
                                learnedWords.forEachIndexed { index, learnedWord ->
                                    if (learnedWord.id == word.id) {
                                        itemSelected.invoke(index)
                                        isSearching.value = false
                                    }
                                }
                            },
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}