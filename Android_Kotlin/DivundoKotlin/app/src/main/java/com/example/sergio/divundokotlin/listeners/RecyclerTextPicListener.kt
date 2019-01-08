package com.example.sergio.divundokotlin.listeners

import com.example.sergio.divundokotlin.models.TextPic

interface RecyclerTexPictListener {
    fun onClick(textPic: TextPic, position: Int)
}