package com.example.sergio.drawerrecyclercardview.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.sergio.drawerrecyclercardview.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ArrivalsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        //Poner el titulo en el toolbar
        activity?.setTitle(R.string.arrivals_fragment_title)

        return inflater.inflate(R.layout.fragment_arrivals, container, false)
    }


}
