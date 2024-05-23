package ru.stroymig.workconnect

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.stroymig.workconnect.data.home.HomeViewModel
import ru.stroymig.workconnect.navigation.Screen
import ru.stroymig.workconnect.navigation.WorkConnectAppRouter
import ru.stroymig.workconnect.screens.HomeScreen
import ru.stroymig.workconnect.screens.LoginScreen
import ru.stroymig.workconnect.screens.SignUpScreen


@Composable
fun WorkConnectApp(homeViewModel: HomeViewModel = viewModel()){

        homeViewModel.checkForActiveSession()
        Surface (
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ){

            if(homeViewModel.isUserLoggedIn.value == true){
                WorkConnectAppRouter.navigateTo(Screen.HomeScreen)
            }
            Crossfade(targetState = WorkConnectAppRouter.currentScreen, label = "") { currentState ->
                when (currentState.value){
                    is Screen.SignUpScreen ->{
                        SignUpScreen()
                    }
                    is Screen.LoginScreen ->{
                        LoginScreen()
                    }
                    is Screen.HomeScreen ->{
                        HomeScreen(homeViewModel = HomeViewModel())
                    }
                }

            }
        }

}