package com.devjg.chatapp.ui.navigation

sealed class Destinations(val route: String) {

    //SPLASHSCREEN
    data object SplashScreen : Destinations("splashScreen")

    //AUTH SCREEN
    data object AuthScreen : Destinations("authScreen")

    //REGISTER SCREEN
    data object RegisterScreen : Destinations("registerScreen")

    //CHANGE PASSWORD SCREEN
    data object ChangePasswordScreen :Destinations("changePasswordScreen")

    //CHAT SCREEN
    data object ChatScreen : Destinations("chatScreen")
}