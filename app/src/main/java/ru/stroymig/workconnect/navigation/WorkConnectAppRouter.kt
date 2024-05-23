package ru.stroymig.workconnect.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen {

    data object SignUpScreen : Screen()
    data object LoginScreen : Screen()
    data object HomeScreen : Screen()
}

object WorkConnectAppRouter{
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.LoginScreen)

    fun navigateTo(destination : Screen){
        currentScreen.value = destination
    }
}