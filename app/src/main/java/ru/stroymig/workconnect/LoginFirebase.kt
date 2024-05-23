package ru.stroymig.workconnect

import android.app.Application
import com.google.firebase.FirebaseApp

class LoginFirebase :Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
    }
}