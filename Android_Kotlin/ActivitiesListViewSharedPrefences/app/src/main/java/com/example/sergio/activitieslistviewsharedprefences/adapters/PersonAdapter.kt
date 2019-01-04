package com.example.sergio.activitieslistviewsharedprefences.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.sergio.activitieslistviewsharedprefences.models.Person
import kotlinx.android.synthetic.main.list_view_person.view.*

class PersonAdapter(val context: Context, val layout: Int, val list: List<Person>) : BaseAdapter() {

    private val mInflator: LayoutInflater = LayoutInflater.from(context)

    override fun getItem(position: Int): Any {
        return list[position]
    }

    //Es la posicion pasada como long. Casi no se usa
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

    //La logica del codigo va en este metodo
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val vh: PersonViewHolder

        //ConvertView representa los items de la listView para poder hacer scroll guardando los valores que hayan sido llamados
        //Si son nulos, se llama al converView al no haber sido creados antes
        if(convertView == null) {
            view = mInflator.inflate(layout, parent, false)
            vh = PersonViewHolder(view)

            //tag para inyectar objetos al view
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as PersonViewHolder
        }

        val fullName = "${list[position].firstname}, ${list[position].lastname}"
        vh.fullName.text = fullName
        vh.age.text = list[position].age.toString()

        return view
    }

}

private class PersonViewHolder(view: View) {
    val fullName: TextView = view.textViewName
    val age: TextView = view.textViewAge
}