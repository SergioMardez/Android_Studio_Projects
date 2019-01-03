package com.example.sergio.activitieslistviewsharedprefences

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.widget.Button
import android.widget.Toast
import com.example.sergio.activitieslistviewsharedprefences.activities.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLifeCycle = findViewById<Button>(R.id.button_cycle)
        val btnClickEvents = findViewById<Button>(R.id.button_click)
        val btnAndroidExt = findViewById<Button>(R.id.button_android_extensions)
        val btnPicasso = findViewById<Button>(R.id.button_picasso)
        val btnListView = findViewById<Button>(R.id.button_list_view)
        val btnIntents = findViewById<Button>(R.id.button_intents)
        val btnPermission = findViewById<Button>(R.id.button_permissions)
        val btnSharedPreferences = findViewById<Button>(R.id.button_shared_preferences)
        val btnExtensionFunctions = findViewById<Button>(R.id.button_extensions_functions)

        //Click listeners de los botones
        btnLifeCycle.setOnClickListener { goToLifeCycleActivity() }
        btnClickEvents.setOnClickListener { goToClickEventsActivity() }
        btnAndroidExt.setOnClickListener { goToKotlinAdnroidExtActivity() }
        btnPicasso.setOnClickListener { goToPicassoActivity() }
        btnListView.setOnClickListener { goToListViewActivity() }
        btnIntents.setOnClickListener { goToIntentsActivity() }
        btnPermission.setOnClickListener { goToPermissionActivity() }
        btnSharedPreferences.setOnClickListener { goToSharedPreferencesActivity() }
        btnExtensionFunctions.setOnClickListener { goToExtensionFunctionsActivity() }
    }

    fun showToast() {
        Toast.makeText(this, "Hello from the Toast!", Toast.LENGTH_LONG)
    }

    fun showSnackbar() {
        //Para hacerlo con snackBar en vez de Toast agregar al gradle -> implementation 'com.android.support:design:27.0.0'
        //Hay que pasarle un view al snackBar para ser renderizado
        val layout = findViewById<ConstraintLayout>(R.id.constrait);
        //Snackbar.make(layout, "Hello from the snackBar!", Snackbar.LENGTH_LONG).show()

        //Pasando funciones mediante callback
        Snackbar.make(layout, "Hello from the snackBar!", Snackbar.LENGTH_LONG).setAction("UNDO") {
            Snackbar.make(layout, "Email has been restored", Snackbar.LENGTH_LONG).show()
        }.show()
    }

    //Declaracion normal de una funcion
    /*private fun goToLifeCycleActivity() {
        //Para ir de un activity a otro
        val intent = Intent(this, LifeCycleActivity::class.java)
        startActivity(intent)
    }*/
    //Cuando solo esta haciendo uso de un metodo se puede llamar de la siguiente forma con un metodo en linea
    private fun goToLifeCycleActivity() = startActivity(Intent(this, LifeCycleActivity::class.java))
    private fun goToClickEventsActivity() = startActivity(Intent(this, ClickEventsActivity::class.java))
    private fun goToKotlinAdnroidExtActivity() = startActivity(Intent(this, KotlinAndroidExtensionActivity::class.java))
    private fun goToPicassoActivity() = startActivity(Intent(this, PicassoActivity::class.java))
    private fun goToListViewActivity() = startActivity(Intent(this, ListViewActivity::class.java))
    private fun goToIntentsActivity() = startActivity(Intent(this, IntentsActivity::class.java))
    private fun goToPermissionActivity() = startActivity(Intent(this, PermissionsActivity::class.java))
    private fun goToSharedPreferencesActivity() = startActivity(Intent(this, SharedPreferencesActivity::class.java))
    private fun goToExtensionFunctionsActivity() = startActivity(Intent(this, ExtensionFunctionsActivity::class.java))


}
