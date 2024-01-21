package com.example.notes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notes.screen.CreateNewNoteScreen
import com.example.notes.screen.HomeScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.StatusBar.route){
        composable(route = Screen.StatusBar.route){
            HomeScreen(navController = navController)
        }

        composable(route = Screen.ScreenSetup.route){
            CreateNewNoteScreen(navController = navController)
        }


    }
}
