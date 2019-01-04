package com.example.sergio.activitieslistviewsharedprefences.others

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.example.sergio.activitieslistviewsharedprefences.models.IToolbar

open class ToolbarActivity : AppCompatActivity(), IToolbar {

    protected var _toolbar: Toolbar? = null

    override fun toolbarToLoad(toolbar: Toolbar?) {
        _toolbar = toolbar
        //Solo si el toolbar no es nulo
        _toolbar?.let { setSupportActionBar(_toolbar) }
    }

    override fun enabledHomeDisplay(value: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(value)
    }

}
