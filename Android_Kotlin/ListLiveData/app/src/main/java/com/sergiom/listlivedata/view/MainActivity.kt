package com.sergiom.listlivedata.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
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
            viewModel.saveProject(Project("1","Pamplina","xD",""))
        }
    }

    private fun configureLiveDataObservers() {
        viewModel.getAllProjectsLiveData().observe(this, Observer { projects ->
            projects?.let {
                adapter.updateProjects(projects)
            }
        })
    }
}
