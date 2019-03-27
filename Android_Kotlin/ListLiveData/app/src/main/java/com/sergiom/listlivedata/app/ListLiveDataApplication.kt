package com.sergiom.listlivedata.app

import android.app.Application
import android.arch.persistence.room.Room
import com.sergiom.listlivedata.model.ProjectDatabase

class ListLiveDataApplication : Application() {

    companion object {
        lateinit var database: ProjectDatabase
    }

    override fun onCreate() {
        super.onCreate()

        //Para acabar de iniciar el repositorio de criaturas, crear la base de datos
        database = Room.databaseBuilder(this, ProjectDatabase::class.java, "project_database")
            .build()
    }
}