package com.example.sergio.drawerrecyclercardview.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.sergio.drawerrecyclercardview.R
import com.example.sergio.drawerrecyclercardview.adapters.FlightAdapter
import com.example.sergio.drawerrecyclercardview.listener.RecyclerFlightListener
import com.example.sergio.drawerrecyclercardview.models.Flights
import com.example.sergio.drawerrecyclercardview.toast
import kotlinx.android.synthetic.main.fragment_departures.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class DeparturesFragment : Fragment() {

    //by lazy solo se llama si se llega a utilizar, si no, no se llama
    private val list: ArrayList<Flights> by lazy { getFlights() }

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: FlightAdapter
    private val layoutManager by lazy { LinearLayoutManager(context) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //Poner el titulo en el toolbar
        activity?.setTitle(R.string.departures_fragment_title)

        val rootView = inflater.inflate(R.layout.fragment_departures, container, false)

        recycler = rootView.recyclerView as RecyclerView
        setRecyclerView()

        return rootView
    }

    private fun setRecyclerView() {
        recycler.setHasFixedSize(true)
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.layoutManager = layoutManager
        adapter = (FlightAdapter(list, object: RecyclerFlightListener {
            override fun onClick(flight: Flights, position: Int) {
                activity?.toast("Let's go to ${flight.city}!")
            }

            override fun onDelete(flight: Flights, position: Int) {
                list.remove(flight)
                adapter.notifyItemRemoved(position)
                activity?.toast("${flight.city} has been remove!!")
            }
        }))
        recycler.adapter = adapter
    }

    private fun getFlights(): ArrayList<Flights> {
        return object: ArrayList<Flights>() {
            init {
                add(Flights(1,"Seattle", R.drawable.seattle))
                add(Flights(2,"New York", R.drawable.new_york))
                add(Flights(3,"London", R.drawable.london))
                add(Flights(4,"Seville", R.drawable.sevilla))
                add(Flights(5,"Venice", R.drawable.venice))
                add(Flights(6,"Athens", R.drawable.athens))
                add(Flights(7,"Toronto", R.drawable.toronto))
                add(Flights(8,"Dublin", R.drawable.dublin))
                add(Flights(9,"Caribbean", R.drawable.caribean_sea))
            }
        }
    }

}
