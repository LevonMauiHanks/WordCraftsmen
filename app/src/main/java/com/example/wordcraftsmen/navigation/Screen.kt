package com.example.wordcraftsmen.navigation

import com.example.wordcraftsmen.utils.HOME_SCREEN_ROUTE
import com.example.wordcraftsmen.utils.LEARNED_WORDS_SCREEN_ROUTE

sealed class Screen(val route: String) {
    object Learned_Words_Screen : Screen(LEARNED_WORDS_SCREEN_ROUTE)
    object HomeScreen : Screen(HOME_SCREEN_ROUTE)

    fun withArgs(vararg args: String) = buildString {
        append(route)
        args.forEach { arg ->
            append("/$arg")
        }
    }
}