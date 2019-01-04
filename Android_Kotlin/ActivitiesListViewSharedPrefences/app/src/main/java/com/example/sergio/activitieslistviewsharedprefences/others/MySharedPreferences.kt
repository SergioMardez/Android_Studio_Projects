package com.example.sergio.activitieslistviewsharedprefences.others

import android.content.Context

class MySharedPreferences(context: Context) {

    //Nombre del fichero de las shared preferences
    private val fileName = "mis-preferencias"

    //Instancia de ese fichero
    private val prefs = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)

    //Por cada una de las variables que vamos a guardar en nuestro fichero shared preferences, se declaran:
    var name: String
        get() = prefs.getString("name", "")
        set(value) = prefs.edit().putString("name", value).apply()

    var age: Int
        get() = prefs.getInt("age", -1)
        set(value) = prefs.edit().putInt("age", value).apply()

}