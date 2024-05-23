package ru.stroymig.workconnect.data.home

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import ru.stroymig.workconnect.navigation.Screen
import ru.stroymig.workconnect.navigation.WorkConnectAppRouter
import ru.stroymig.workconnect.data.NavigationItem


class HomeViewModel ():ViewModel(){

    private val TAG = HomeViewModel::class.simpleName
    val navigationItemsList = listOf<NavigationItem>(
        NavigationItem(
            title = "Главная",
            icon = Icons.Default.Home,
            description = "Home Screen",
            itemID = "homeScreen"

        ),
        NavigationItem(
            title = "Настройки",
            icon = Icons.Default.Settings,
            description = "Settings Screen",
            itemID = "settingsScreen"

        ),
        NavigationItem(
            title = "Выйти",
            icon = Icons.AutoMirrored.Filled.Logout,
            description = "Logout",
            itemID = "logout"

        )
    )

    val isUserLoggedIn : MutableLiveData<Boolean> = MutableLiveData()

    fun logout(){

        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()

        val authStateListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                Log.d(TAG, "Inside sign out SUCCESS")
                WorkConnectAppRouter.navigateTo(Screen.LoginScreen)
            } else {
                Log.d(TAG, "Inside sign out is NOT Complete")
            }
        }

        firebaseAuth.addAuthStateListener(authStateListener)
    }

    fun checkForActiveSession(){
        if (FirebaseAuth.getInstance().currentUser != null){
            Log.d(TAG, "Valid_Session")
            isUserLoggedIn.value = true
        } else {
            Log.d(TAG, "USER isn't logged")
            isUserLoggedIn.value = false
        }
    }

    val emailID : MutableLiveData<String> = MutableLiveData()
    fun getUserData(){

            FirebaseAuth.getInstance().currentUser?.also {
                it.email?.also { email ->
                    emailID.value = email
                }
            }
    }
}
