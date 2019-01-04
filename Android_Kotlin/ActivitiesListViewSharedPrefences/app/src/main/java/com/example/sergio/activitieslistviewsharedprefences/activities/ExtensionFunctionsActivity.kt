package com.example.sergio.activitieslistviewsharedprefences.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.sergio.activitieslistviewsharedprefences.MainActivity
import com.example.sergio.activitieslistviewsharedprefences.R
import com.example.sergio.activitieslistviewsharedprefences.others.goToActivity
import com.example.sergio.activitieslistviewsharedprefences.others.loadByURL
import com.example.sergio.activitieslistviewsharedprefences.others.snackBar
import com.example.sergio.activitieslistviewsharedprefences.others.toast
import kotlinx.android.synthetic.main.activity_extension_functions.*

class ExtensionFunctionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extension_functions)

        buttonToast.setOnClickListener { toast("I love extension functions!") }
        buttonSnackBar.setOnClickListener { snackBar("I love extension functions!", action = "Undo") { toast("Undo!!") } }
        buttonLoadByURL.setOnClickListener { imageViewLoadedByURL.loadByURL("https://cdn.pixabay.com/photo/2016/03/22/04/08/pepe-the-frog-1272162_960_720.jpg") }
        buttonGoToActivity.setOnClickListener { goToActivity<MainActivity> { putExtra("id", 1 ) } } //Sin extras---> goToActivity<MainActivity>()
    }
}
