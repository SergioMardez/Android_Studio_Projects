package com.example.sergio.drawerrecyclercardview.listener

import com.example.sergio.drawerrecyclercardview.models.Flights

interface RecyclerFlightListener {
    fun onClick(flight: Flights, position: Int)
    fun onDelete(flight: Flights, position: Int)
}