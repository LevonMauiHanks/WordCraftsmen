package com.example.wordcraftsmen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wordcraftsmen.ui.theme.home.HomeScreen
import com.example.wordcraftsmen.navigation.Screen
import com.example.wordcraftsmen.ui.theme.learned_notes.LearnedWordsScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Learned_Words_Screen.route) {
            LearnedWordsScreen(navController = navController)
        }
    }
}