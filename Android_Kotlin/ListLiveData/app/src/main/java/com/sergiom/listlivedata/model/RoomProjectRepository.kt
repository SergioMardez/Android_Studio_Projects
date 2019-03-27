package com.sergiom.listlivedata.model

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.sergiom.listlivedata.app.ListLiveDataApplication

class RoomProjectRepository: ProjectRepository {
    private val projectDao: ProjectDao = ListLiveDataApplication.database.projectDao()

    private val allProjects: LiveData<List<Project>>

    init {
        allProjects = projectDao.getAllProjects()
    }

    override fun saveProject(project: Project) {
        InsertAsyncTask(projectDao).execute(project)
    }

    override fun deleteProject(vararg porject: Project) {
        val creatureArray = allProjects.value?.toTypedArray()
        if(creatureArray != null) {
            DeleteAsyncTask(projectDao).execute(*creatureArray)
        }
    }

    override fun getAllProjects(): LiveData<List<Project>> = allProjects


    private class InsertAsyncTask internal constructor(private val dao: ProjectDao) : AsyncTask<Project, Void, Void>() {
        override fun doInBackground(vararg params: Project): Void? {
            dao.insertProject(params[0]) //Aqui se inserta la criatura usando el DAO
            return null
        }
    }

    private class DeleteAsyncTask internal constructor(private val dao: ProjectDao) : AsyncTask<Project, Void, Void>() {
        override fun doInBackground(vararg params: Project): Void? {
            dao.deleteProject(*params) //Aqui se borran todas las criatuas
            return null
        }
    }
}