package com.example.sergio.activitieslistviewsharedprefences.activities

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.sergio.activitieslistviewsharedprefences.R

class LifeCycleActivity : LifeCycleEventsActivity() {

    private var exitEnable = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle)
    }

    //Al hacer click en el boton back, volver atras
    override fun onBackPressed() {
        if(exitEnable) {
            super.onBackPressed()
        }
        exitEnable = true
        Toast.makeText(this, "Click back again to exit this screen", Toast.LENGTH_SHORT).show()

        //2 segundos para poder salir o se vuelve a poner a false con este handler para usar una accion
        Handler().postDelayed(Runnable { exitEnable = false }, 2000)
    }
}
