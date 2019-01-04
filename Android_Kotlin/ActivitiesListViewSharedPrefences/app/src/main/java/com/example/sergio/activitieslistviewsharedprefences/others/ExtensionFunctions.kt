package com.example.sergio.activitieslistviewsharedprefences.others

import android.app.Activity
import android.content.Intent
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.sergio.activitieslistviewsharedprefences.R
import com.squareup.picasso.Picasso

//Devuelve verdadero si es un numero natural, mayor que cero
fun Int.isNatural() = this >= 0


//Toast con extension function, por defecto en duracion corta a no ser que se le pase otra
fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, message, duration).show()
//Toast con un R a el archivo strings, para cargar un string preconfigurado
fun Activity.toast(resourceId: Int, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, resourceId, duration).show()


fun Activity.snackBar(message: CharSequence, view: View? = findViewById(R.id.container), duration: Int = Snackbar.LENGTH_SHORT,
                      action: String? = null,
                      actionEvt: (v:View) -> Unit = {}) {

    if (view != null) {
        val snackbar = Snackbar.make(view, message, duration)
        if(!action.isNullOrEmpty()) {
            snackbar.setAction(action, actionEvt)
        }
        snackbar.show()
    }

}


fun ViewGroup.inflate(layoutId: Int) = LayoutInflater.from(context).inflate(layoutId, this, false)!!


//Para imagenes con url mediante picasso
fun ImageView.loadByURL(url: String) = Picasso.with(context).load(url).into(this)


//Para hacer intents. "T" no se sabe lo que es pero hereda de activity, necesita utilizar el reified y la
// funcion ser "inline" para poder pasar un tipo y hacer uso de él.
//Intent.() -> Unit callback que restringe a metodos de intent unicamente
inline fun <reified T : Activity>Activity.goToActivity(noinline init: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    intent.init()
    startActivity(intent)
}

//Para los activities result, como el usado en la peticion de la camara
fun Activity.goToActivityResult(action: String, requestCode: Int, init: Intent.() -> Unit = {}) {
    val intent = Intent(action)
    intent.init()
    startActivityForResult(intent, requestCode)
}