package com.example.sergio.activitieslistviewsharedprefences.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.example.sergio.activitieslistviewsharedprefences.R
import com.example.sergio.activitieslistviewsharedprefences.models.Student
import kotlinx.android.synthetic.main.activity_intents.*

class IntentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intents)

        buttonIntentExtras.setOnClickListener { goIntentExtras() }
        buttonIntentFlags.setOnClickListener { goIntentFlags() }
        buttonIntentObject.setOnClickListener { goIntentObject() }
    }

    private fun goIntentExtras() {
        val intent = Intent(this, IntentExtrasActivity::class.java)
        intent.putExtra("name", "Sergio")
        intent.putExtra("lastName", "Martin")
        intent.putExtra("age", 32)
        intent.putExtra("developer", true)
        startActivity(intent)
    }

    private fun goIntentFlags() {
        val intent = Intent(this, IntentExtrasActivity::class.java)
       // intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY //No guarda el activity en el BackStack

       // intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION //Sin animacion en la creacion del activity

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK  //Vacia el BackStack completo

        startActivity(intent)
       // finish() //Destruye el activity en vez de añadirlo al BackStack
    }

    private fun goIntentObject() {
        val intent = Intent(this, IntentExtrasActivity::class.java)
        val student = Student("Alberto", "Matos", 27, false)
        //Como es una clase parcelable, se puede mandar en un intent
        intent.putExtra("student", student)
        startActivity(intent)
    }
}
