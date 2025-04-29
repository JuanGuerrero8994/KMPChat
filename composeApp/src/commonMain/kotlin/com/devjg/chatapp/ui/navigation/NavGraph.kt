package com.devjg.chatapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.devjg.chatapp.ui.components.scaffold.BottomNavScreen
import com.devjg.chatapp.ui.screen.AccountScreen
import com.devjg.chatapp.ui.screen.FavouriteScreen
import com.devjg.chatapp.ui.screen.HomeScreen
import com.devjg.chatapp.ui.screen.auth.AuthScreen
import com.devjg.chatapp.ui.screen.auth.AuthViewModel
import com.devjg.chatapp.ui.screen.auth.ChangePasswordScreen
import com.devjg.chatapp.ui.screen.auth.RegisterScreen
import com.devjg.chatapp.ui.screen.chat.ChatViewModel
import com.devjg.chatapp.ui.screen.message.MessageViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun NavGraph(navController: NavHostController) {
    val authViewModel: AuthViewModel = koinViewModel()
    val chatViewModel: ChatViewModel = koinViewModel()
    val messageViewModel: MessageViewModel = koinViewModel()


    NavHost(navController = navController, startDestination = Destinations.AuthScreen.route) {

        // Rutas simples
        addRoute(navController, Destinations.AuthScreen.route) { AuthScreen(authViewModel, it) }
        addRoute(navController, Destinations.RegisterScreen.route) { RegisterScreen(authViewModel, it) }
        addRoute(navController, Destinations.ChangePasswordScreen.route) { ChangePasswordScreen(authViewModel, it) }

        /*RUTAS NAV BAR*/
        addBottomNavRoute(navController, BottomNavScreen.Home.route) { HomeScreen(chatViewModel,messageViewModel,it) }
        addBottomNavRoute(navController, BottomNavScreen.Favourite.route) { FavouriteScreen(it) }
        addBottomNavRoute(navController, BottomNavScreen.Account.route) { AccountScreen(it) }

    }

}
