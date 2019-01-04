package com.example.sergio.activitieslistviewsharedprefences.app

import android.app.Application
import com.example.sergio.activitieslistviewsharedprefences.others.MySharedPreferences

//by lazy -> nunca se crea esta instancia hasta que esta variable sea llamada
val preferences: MySharedPreferences by lazy { MyApp.prefs!! }

class MyApp: Application() {
    //Todas las apps lanzan Application antes de que el primer Activity sea ejecutado. Si se quiere hacer algo antes de eso
    //se escribe de esta forma

    companion object {
        var prefs: MySharedPreferences? = null
    }

    override fun onCreate() {
        super.onCreate()
        prefs = MySharedPreferences(applicationContext)
    }
}