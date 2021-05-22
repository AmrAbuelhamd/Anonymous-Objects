package com.blogspot.soyamr.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.blogspot.soyamr.data.db.DatabaseConfig.Tables.OBJECT_TABLE

@Entity(tableName = OBJECT_TABLE)
data class ObjectEntity(
    val name: String,
    val title: String,
    val tags: List<Int>?,
    @PrimaryKey
    val id: Int = 0,
)