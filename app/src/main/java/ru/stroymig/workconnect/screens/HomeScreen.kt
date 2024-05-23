package ru.stroymig.workconnect.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import ru.stroymig.workconnect.R
import ru.stroymig.workconnect.data.home.HomeViewModel
import ru.stroymig.workconnect.navigation.Screen
import ru.stroymig.workconnect.navigation.WorkConnectAppRouter
import ru.stroymig.workconnect.ui.theme.GradientBackgroundColor
import ru.stroymig.workconnect.ui.theme.Primary02
import ru.stroymig.workconnect.ui.theme.Primary03
import ru.stroymig.workconnect.ui.theme.Secondary

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()){

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    // Состояние для управления видимостью поля поиска
    var isSearchVisible by remember{ mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    homeViewModel.getUserData()

    Scaffold (
        scaffoldState = scaffoldState,
        topBar = {
            AppToolbar(
                toolbarTitle = stringResource(id = R.string.home),

                isSearchVisible = isSearchVisible,
                searchText = searchText,
                onSearchTextChanged = { searchText = it },
                onSearchClicked = { isSearchVisible = !isSearchVisible },
                navigationIconClicked = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {

            Column (modifier = Modifier
                .fillMaxSize()
                .background(Primary03)
            ){
                NavigationDrawerHeader(homeViewModel.emailID.value)
                NavigationDrawerBody(
                    navigationDrawerItems = homeViewModel.navigationItemsList,
                    onNavigationItemClicked = {
                        Log.d("Item_Clicked", "${it.itemID} ${it.title}")
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                        }
                        // Переключение экрана при нажатии на элемент
                        when (it.itemID) {
                            "homeScreen" -> WorkConnectAppRouter.navigateTo(Screen.HomeScreen)
                            "settingsScreen" -> WorkConnectAppRouter.navigateTo(Screen.HomeScreen)
                            "logout" -> homeViewModel.logout()

                        }
                    }
                )
            }


        },
        floatingActionButton = {
            FloatingActionButton(
                // Ваше действие при нажатии на FAB
                onClick = { },
                backgroundColor = Secondary,
                contentColor = Color.White,
                modifier = Modifier
                    .border(2.dp, Primary02, CircleShape)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
        },
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = true
    ){paddingValues ->

        Surface (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)

        ){
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .background(GradientBackgroundColor)
            ){


            }
        }

    }
}


@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen(homeViewModel = HomeViewModel())
}