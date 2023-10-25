package com.example.wordcraftsmen.ui.theme.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.wordcraftsmen.ui.theme.components.AppLogo
import com.example.wordcraftsmen.ui.theme.components.WordItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val newWordsGenerated = remember {
        mutableStateOf(false)
    }
    val generatedItems = remember {
        mutableStateOf<List<Word>?>(emptyList())
    }

    LaunchedEffect(Unit) {
        generatedItems.value =
            viewModel.wordRepository.getAllWords()?.first()?.filter { !it.isLearned }?.shuffled()
                ?.take(5)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { AppBottomBar(navController = navController) },
        containerColor = MaterialTheme.colorScheme.secondary
    ) { paddingVal ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingVal)
        ) {
            AppLogo(50, 20)

            Button(
                onClick = {
                    coroutineScope.launch {
                        generatedItems.value =
                            viewModel.wordRepository.getAllWords()?.first()
                                ?.filter { !it.isLearned }
                                ?.shuffled()?.take(5)
                    }
                    newWordsGenerated.value = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = stringResource(R.string.generate_new_words),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 24.sp,
                    fontFamily = FontFamily.Cursive
                )
            }

            generatedItems.value?.let { data ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    userScrollEnabled = true
                ) {
                    items(data) { word ->
                        val checkedState = remember { mutableStateOf(false) }
                        WordItem(word, Modifier, true, checkedState, newWordsGenerated) {
                            coroutineScope.launch {
                                it.copy(isLearned = true).let { word ->
                                    viewModel.wordRepository.addWord(word)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}