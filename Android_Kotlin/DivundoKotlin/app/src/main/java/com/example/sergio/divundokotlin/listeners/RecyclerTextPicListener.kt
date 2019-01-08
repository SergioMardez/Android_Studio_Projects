package com.example.sergio.divundokotlin.listeners

import com.example.sergio.divundokotlin.models.TextPic

interface RecyclerTextPicListener {
    fun onClick(textPic: TextPic, position: Int)
}