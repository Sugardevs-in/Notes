package com.example.notes.navigation

sealed class Screen(val route: String) {
    object StatusBar: Screen("statusBar_screen")
    object ScreenSetup: Screen("screenSetup_screen")
}