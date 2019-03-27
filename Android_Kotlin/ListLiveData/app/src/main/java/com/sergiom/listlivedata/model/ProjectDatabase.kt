package com.sergiom.listlivedata.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [(Project::class)], version = 1)
abstract class ProjectDatabase: RoomDatabase() {
    abstract fun projectDao(): ProjectDao
}