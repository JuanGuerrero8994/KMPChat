package com.devjg.chatapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.devjg.chatapp.ui.components.scaffold.BottomNavScreen
import com.devjg.chatapp.ui.screen.HomeScreen
import com.devjg.chatapp.ui.screen.auth.AuthScreen
import com.devjg.chatapp.ui.screen.auth.AuthViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun NavGraph(navController: NavHostController) {
    val authViewModel: AuthViewModel = koinViewModel()

    NavHost(navController = navController, startDestination = Destinations.AuthScreen.route) {
        // Rutas simples
        addRoute(navController, Destinations.AuthScreen.route) { AuthScreen(authViewModel, it) }
        addBottomNavRoute(navController, BottomNavScreen.Home.route) { HomeScreen(it) }
    }

}
