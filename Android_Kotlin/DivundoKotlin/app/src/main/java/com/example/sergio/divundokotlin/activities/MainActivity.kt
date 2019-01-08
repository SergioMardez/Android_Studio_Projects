package com.example.sergio.divundokotlin.activities

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.sergio.divundokotlin.R
import com.example.sergio.divundokotlin.fragments.EntrySuccessFragment
import com.example.sergio.divundokotlin.fragments.LoginFragment

class MainActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences
    private var token: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //shared preferences
        val fileName = "mis-preferencias"
        prefs = getSharedPreferences(fileName, Context.MODE_PRIVATE)
        token = prefs.getString("token", "")

        if (!token.isEmpty()) {
            fragmentTransaction(EntrySuccessFragment())
        } else {
            fragmentTransaction(LoginFragment())
        }

    }

    private fun fragmentTransaction(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}
