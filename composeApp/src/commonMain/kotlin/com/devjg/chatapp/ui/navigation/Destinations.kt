package com.devjg.chatapp.ui.navigation

sealed class Destinations(val route: String) {

    //SPLASHSCREEN
    data object SplashScreen : Destinations("splashScreen")

    //AUTH SCREEN
    data object AuthScreen : Destinations("authScreen")

    //HOME SCREEN
    data object HomeScreen :Destinations("homeScreen")

}