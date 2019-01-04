package com.example.sergio.activitieslistviewsharedprefences.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.sergio.activitieslistviewsharedprefences.R
import kotlinx.android.synthetic.main.activity_kotlin_android_extension.*

class KotlinAndroidExtensionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_android_extension)

        val btn = findViewById<Button>(R.id.buttonById)
        btn.setOnClickListener { Toast.makeText(this, "Click By ID", Toast.LENGTH_SHORT).show() }

        //Llamar al boton mediante android kotlin extension. Se llama a cualquier cosa declarada en el layout escribiendo el import:
        //import kotlinx.android.synthetic.main.activity_kotlin_android_extension.*
        //Hay que cambiar el activity por el que se necesite.
        buttonByKAT.setOnClickListener { Toast.makeText(this, "Click By KAT", Toast.LENGTH_SHORT).show() }
    }
}
