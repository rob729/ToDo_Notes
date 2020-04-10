package com.robin729.todonotes.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesData")
data class Notes(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    val title: String,
    @ColumnInfo(name = "description")val desp: String,
    val imagePath: String,
    var isCompleted: Boolean = false
)