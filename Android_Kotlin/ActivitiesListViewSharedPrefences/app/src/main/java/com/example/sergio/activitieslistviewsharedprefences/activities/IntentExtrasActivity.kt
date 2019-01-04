package com.example.sergio.activitieslistviewsharedprefences.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import com.example.sergio.activitieslistviewsharedprefences.R
import com.example.sergio.activitieslistviewsharedprefences.models.Student
import com.example.sergio.activitieslistviewsharedprefences.others.ToolbarActivity
import kotlinx.android.synthetic.main.activity_intent_extras.*

class IntentExtrasActivity : ToolbarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_extras)

        //Para poner un boton que te lleva al mainActivity (hay que editar el manifest especificando el intent deseado como meta-data)
        //Hecho con una interfaz (iToolbar) y la clase ToolbarActivity
        toolbarToLoad(toolbar as Toolbar)
        enabledHomeDisplay(true)

        buttonBack.setOnClickListener { startActivity(Intent(this, IntentsActivity::class.java)) }

        val isExtraSet = setIntentExtrasFromPreviousActivity()

        val isParcelableSet = setParcelableExtraFromPreviousActivity()

        if(!isExtraSet && !isParcelableSet) {
            checkBoxDeveloper.visibility = View.INVISIBLE
        }
    }

    private fun setParcelableExtraFromPreviousActivity(): Boolean {
        val student = intent.getParcelableExtra<Student>("student")
        //Si no es nulo entra, si es nulo, salta fuera y no pone los datos
        student?.let {
            textViewName.text = student.name
            textViewLastName.text = student.lastName
            textViewAge.text = "${student.age}"
            checkBoxDeveloper.isChecked = student.isDeveloper
            checkBoxDeveloper.text = "Developer"
            return true
        }
        return false
    }

    private fun setIntentExtrasFromPreviousActivity(): Boolean {
        val name: String? = intent.getStringExtra("name")
        val lastName: String? = intent.getStringExtra("lastName")
        val age = intent.getIntExtra("age", -1)
        val developer = intent.getBooleanExtra("developer", false)

        if(name != null && lastName != null && age >= 0) {
            textViewName.text = name
            textViewLastName.text = lastName
            textViewAge.text = "$age"
            checkBoxDeveloper.isChecked = developer
            checkBoxDeveloper.text = "Developer"
            return true
        }
        return false
    }
}
