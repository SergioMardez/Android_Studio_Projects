package com.example.sergio.activitieslistviewsharedprefences.models

import android.support.v7.widget.Toolbar

interface IToolbar {
    fun toolbarToLoad(toolbar: Toolbar?)
    fun enabledHomeDisplay(value: Boolean)
}