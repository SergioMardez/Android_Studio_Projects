package com.example.sergio.drawerrecyclercardview.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.sergio.drawerrecyclercardview.R
import com.example.sergio.drawerrecyclercardview.inflate
import com.example.sergio.drawerrecyclercardview.listener.RecyclerFlightListener
import com.example.sergio.drawerrecyclercardview.loadByResource
import com.example.sergio.drawerrecyclercardview.models.Flights
import kotlinx.android.synthetic.main.recycler_flight.view.*

class FlightAdapter(private val flights: List<Flights>, private val listener: RecyclerFlightListener)
    : RecyclerView.Adapter<FlightAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.recycler_flight))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(flights[position], listener)

    override fun getItemCount() = flights.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        // = with(itemView) sirve para poner el contexto y no tener que poner itemView.textViewCityName o los otros.
        fun bind(flight: Flights, listener: RecyclerFlightListener) = with(itemView){
            //Aquí es donde va la lógica de la del adaptador. Cargar textView e imageview
            textViewCityName.text = flight.city
            imageViewBackground.loadByResource(flight.imageResource)

            //Clicks eventes
            setOnClickListener { listener.onClick(flight, adapterPosition) }
            buttonDelete.setOnClickListener { listener.onDelete(flight, adapterPosition) }
        }
    }
}