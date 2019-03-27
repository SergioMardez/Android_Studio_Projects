package com.sergiom.listlivedata.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.sergiom.listlivedata.R
import com.sergiom.listlivedata.app.inflate
import com.sergiom.listlivedata.model.Project
import kotlinx.android.synthetic.main.list_item_project.view.*

class ProjectAdapter(private val projects: MutableList<Project>)
    : RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_item_project))
    }

    override fun onBindViewHolder(holder: ProjectAdapter.ViewHolder, position: Int) {
        holder.bind(projects[position])
    }

    override fun getItemCount() = projects.size

    fun updateProjects(projects: List<Project>) {
        this.projects.clear()
        this.projects.addAll(projects)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var project: Project

        fun bind(project: Project) {
            this.project = project
            // Se añaden los parametros del project
            itemView.textViewIcon.text = project.icon
            itemView.textViewProjectTitle.text = project.title
        }
    }
}