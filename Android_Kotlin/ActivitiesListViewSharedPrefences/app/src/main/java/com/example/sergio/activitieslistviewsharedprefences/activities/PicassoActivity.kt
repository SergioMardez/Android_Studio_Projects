package com.example.sergio.activitieslistviewsharedprefences.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sergio.activitieslistviewsharedprefences.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_picasso.*

class PicassoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picasso)

        buttomLoader.setOnClickListener { loadImages() }

        //fetch para el precacheo de las imagenes
        Picasso.with(this).load("https://static.pexels.com/photos/288264/pexels-photo-288264.jpeg").fetch()

    }

    fun loadImages() {
        //Al estar precacheada la primera, se carga antes

        Picasso.with(this)
            .load("https://static.pexels.com/photos/288264/pexels-photo-288264.jpeg")
            .resize(400, 400)
            .centerCrop()
            .into(imageViewTop)

        /*
        * Picasso.with(this)
            .load("https://static.pexels.com/photos/288264/pexels-photo-288264.jpeg")
            .fit() //Para llenar el image view
            .into(imageViewTop)
        * */

        /*Picasso.with(this)
            .load("https://static.pexels.com/photos/288929/pexels-photo-288929.jpeg")
            .fit()
            .transform(CircleTransform()) //Renderizar como un circulo
            .into(imageViewBottom)
            */

        //Para hacer una pequeña animacion. Hace falta una variable para pasar el contexto
        val context:Context = this

        Picasso.with(this)
            .load("https://static.pexels.com/photos/288929/pexels-photo-288929.jpeg")
            .fetch(object: Callback {
                override fun onSuccess() {
                    imageViewBottom.alpha = 0f

                    Picasso.with(context)
                        .load("https://static.pexels.com/photos/288929/pexels-photo-288929.jpeg")
                        .fit()
                        .into(imageViewBottom)

                    imageViewBottom.animate().setDuration(700).alpha(1f).start()
                }

                override fun onError() {
                    Toast.makeText(context, "Error al cargar imagen", Toast.LENGTH_SHORT).show()
                }

            })

    }
}
