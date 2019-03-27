package com.sergiom.listlivedata.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ProjectDao {
    @Insert
    fun insertProject(project: Project)

    @Delete
    fun deleteProject(vararg porject: Project)

    @Query("SELECT * FROM project_table")
    fun getAllProjects(): LiveData<List<Project>>
}