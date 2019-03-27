package com.sergiom.listlivedata.model

import android.arch.lifecycle.LiveData

interface ProjectRepository {
    fun saveProject(project: Project)
    fun deleteProject(vararg porject: Project)
    fun getAllProjects(): LiveData<List<Project>>
}