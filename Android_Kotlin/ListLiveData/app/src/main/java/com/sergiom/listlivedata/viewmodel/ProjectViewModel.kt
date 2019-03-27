package com.sergiom.listlivedata.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.sergiom.listlivedata.model.Project
import com.sergiom.listlivedata.model.ProjectRepository
import com.sergiom.listlivedata.model.RoomProjectRepository

class ProjectViewModel(private val repository: ProjectRepository = RoomProjectRepository()) : ViewModel() {
    private val allProjectsLiveData = repository.getAllProjects()

    fun getAllProjectsLiveData() = allProjectsLiveData

    fun clearProject(project: Project) {
        repository.deleteProject(project)
    }

    fun saveProject(project: Project) {
        repository.saveProject(project)
    }
}