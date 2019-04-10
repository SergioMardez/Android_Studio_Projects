package com.sergiom.listlivedata.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.sergiom.listlivedata.R
import com.sergiom.listlivedata.model.Project
import com.sergiom.listlivedata.viewmodel.ProjectViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ProjectViewModel
    private val adapter = ProjectAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ProjectViewModel::class.java)

        projectRecycleView.layoutManager = LinearLayoutManager(this)
        projectRecycleView.adapter = adapter

        configureLiveDataObservers()

        buttonAddProject.setOnClickListener {
            bookings(this)
        }
    }

    private fun configureLiveDataObservers() {
        viewModel.getAllProjectsLiveData().observe(this, Observer { projects ->
            projects?.let {
                adapter.updateProjects(projects)
            }
        })
    }

    fun bookings(context: Context) {
        val url = "https://www.axacoair.se/api/companion/Bookings?user=rafael@divundo.com&customer="

        val jsonRequest = object : JsonArrayRequest(Request.Method.GET,url,null, Response.Listener {
            for(i in 0..(it.length()-1)){
                val json = it.getJSONObject(i)
                viewModel.saveProject(Project(json.getString("bubbleId"),json.getString("subTitle"),
                    json.getString("iconImg"),json.getString("textColor")))
            }
        },Response.ErrorListener{
            Toast.makeText(context,"Error Receiving Projects", Toast.LENGTH_LONG).show()
        }){
            override fun getHeaders(): HashMap<String, String> {
                var header : HashMap<String, String> = HashMap()
                header.put("user", "rafael@divundo.com")
                header.put("customer","")
                header.put("Content-Type","application/json; charset=utf-8")
                header.put("Authorization", "Bearer K4C2w1eux9ADepn3-MhbswAnxlIS0b1xiY76RPZmmiwylBWLa59D7wOF5eW8x6D2j7pO2Iz33_gM6P6UHO5IhaO2NgoS9zWwH_9YPnNthfT1lFSHQ2EqenBwT0Nfz0stFheRWWJ6Yoqo4vp1ecK73Bs6detnrfmdQUDI-ZqxKu3dcU6sHv30BqXTyIgrn9Qd3MYKidmEN7_pRbAiIjftjEeAu92Hja50HTCnjVeZztmMnqTs7_UYPfDmsvxt9KV2S4W_qRN-8YmbkXzlykrkQ4rULY5KzVdTx77LhE40UYOsYdWYTUK6DPDA6wcLxFx_AShCqHucbXjbvA7OcRnTln6QWGtO57OQoB1vzdY5z8PeTtzP8ZrMYuRaOFgp48XnBm2nsA")
                return header
            }
        }
        //add to the queue and execute
        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(jsonRequest)
//        requestQueue.add<String>(stringRequest)
    }
}
