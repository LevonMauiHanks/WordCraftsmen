package com.example.wordcraftsmen.ui.theme.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.wordcraftsmen.R
import com.example.wordcraftsmen.utils.HOME_SCREEN_ROUTE
import com.example.wordcraftsmen.utils.LEARNED_WORDS_SCREEN_ROUTE

@Composable
fun AppBottomBar(navController: NavHostController) {
    val homeSelectedState = remember { mutableStateOf(false) }

    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.primary
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.home_icon),
                    contentDescription = stringResource(R.string.home),
                    tint = MaterialTheme.colorScheme.secondary
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.home),
                    color = MaterialTheme.colorScheme.secondary
                )
            },
            onClick = {
                navController.navigate(HOME_SCREEN_ROUTE)
                homeSelectedState.value = true
            },
            selected = homeSelectedState.value,
            modifier = Modifier.padding(8.dp),
            selectedContentColor = MaterialTheme.colorScheme.surface
        )

        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.book_icon),
                    contentDescription = stringResource(R.string.dictionary),
                    tint = MaterialTheme.colorScheme.secondary
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.dictionary),
                    color = MaterialTheme.colorScheme.secondary
                )
            },
            onClick = {
                navController.navigate(LEARNED_WORDS_SCREEN_ROUTE)
                homeSelectedState.value = false
            },
            selected = homeSelectedState.value,
            modifier = Modifier.padding(8.dp),
            selectedContentColor = MaterialTheme.colorScheme.secondary
        )
    }
}