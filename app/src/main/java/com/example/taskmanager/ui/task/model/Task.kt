package com.example.taskmanager.ui.task.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task (
    @PrimaryKey(autoGenerate = true)
    val uid : Int? = null,
    val title: String? = null,
    val desc: String? = null
) :java.io.Serializable