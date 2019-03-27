package com.sergiom.listlivedata.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "project_table")
data class Project(@PrimaryKey var id_project: String, var title: String, var icon: String, var color: String)