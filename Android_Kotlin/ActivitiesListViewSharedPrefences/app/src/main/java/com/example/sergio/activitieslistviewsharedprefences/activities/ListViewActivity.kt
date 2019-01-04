package com.example.sergio.activitieslistviewsharedprefences.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.sergio.activitieslistviewsharedprefences.R
import com.example.sergio.activitieslistviewsharedprefences.adapters.PersonAdapter
import com.example.sergio.activitieslistviewsharedprefences.models.Person
import kotlinx.android.synthetic.main.activity_list_view.*

class ListViewActivity : AppCompatActivity() {

    private lateinit var adapter: PersonAdapter
    private lateinit var personList: List<Person>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        personList = getPersons()
        adapter = PersonAdapter(this,R.layout.list_view_person, personList)

        listView.adapter = adapter
    }

    private fun getPersons(): List<Person>{
        return listOf(
            Person("Sergio","Martin",32),
            Person("Alejandro","Lora",27),
            Person("ALicia","Gomez",25),
            Person("Paula","Escobar",22),
            Person("Cristian","Ramajo",45),
            Person("Jason","Momoa",39),
            Person("Naomi","Costi",27),
            Person("Ataulfo","Sanquintin",27),
            Person("Yaiza","Mamerto",27)
        )
    }
}
