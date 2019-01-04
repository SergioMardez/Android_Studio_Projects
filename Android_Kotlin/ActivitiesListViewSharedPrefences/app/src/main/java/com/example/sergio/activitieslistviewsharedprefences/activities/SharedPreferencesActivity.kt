package com.example.sergio.activitieslistviewsharedprefences.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sergio.activitieslistviewsharedprefences.R
import com.example.sergio.activitieslistviewsharedprefences.app.preferences
import kotlinx.android.synthetic.main.activity_shared_preferences.*

class SharedPreferencesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences)

        buttonSave.setOnClickListener { setValuesSharedPreferences(); cleanEditTexts(); getValuesSharedPreferences(); }
        getValuesSharedPreferences()

        /*
        * Para usar preferences de forma global, crear MysharedPreferences y MyApp. Añadir nombre
        * al application en el manifest. Despues se puede usar como una variable mas.
        * */
        //preferences.name
    }

    private fun getValuesSharedPreferences() {
        if (preferences.name.isNotEmpty() && preferences.age >= 0) {
            textViewSharedPreferences.text = "Welcome ${preferences.name}, your age is ${preferences.age}"
        } else {
            textViewSharedPreferences.text = "Welcome, please save your name and your age."
        }
    }

    private fun setValuesSharedPreferences() {
        if (editTextName.text.toString().isNotEmpty() && editTextAge.text.toString().isNotEmpty()) {
            preferences.name = editTextName.text.toString()
            preferences.age = editTextAge.text.toString().toInt()

            Toast.makeText(this, "Values have been save successfully", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Please, fil the name and the age before saving", Toast.LENGTH_SHORT).show()
        }
    }

    private fun cleanEditTexts() {
        //Cualquiera de estas formas para limpiar los editText
        editTextName.text.clear()
        editTextAge.setText("")
    }
}
