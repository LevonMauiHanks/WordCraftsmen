package com.example.wordcraftsmen.ui.theme.learned_notes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.wordcraftsmen.R
import com.example.wordcraftsmen.data.Word
import com.example.wordcraftsmen.ui.theme.components.AppBottomBar
import com.example.wordcraftsmen.ui.theme.components.AppSearchBar
import com.example.wordcraftsmen.ui.theme.components.WordItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun LearnedWordsScreen(
    navController: NavHostController,
    viewModel: LearnedNotesViewModel = hiltViewModel()
) {

    val words = remember {
        mutableStateOf<List<Word>?>(emptyList())
    }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        words.value = viewModel.wordRepository.getAllWords()?.first()?.filter { it.isLearned }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { AppBottomBar(navController = navController) },
        containerColor = MaterialTheme.colorScheme.secondary
    ) { paddingVal ->
        Column(modifier = Modifier.padding(paddingVal)) {
            Text(
                text = stringResource(R.string.learned_words),
                color = MaterialTheme.colorScheme.primary,
                fontFamily = FontFamily.Cursive,
                fontSize = 50.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            words.value?.let { learnedWords ->
                AppSearchBar(viewModel = viewModel, learnedWords = learnedWords) {
                    coroutineScope.launch {
                        listState.animateScrollToItem(index = it)
                    }
                }

                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxSize()
                ) {
                    itemsIndexed(learnedWords) { index, word ->
                        WordItem(
                            word = word
                        )
                    }
                }
            }
        }
    }
}