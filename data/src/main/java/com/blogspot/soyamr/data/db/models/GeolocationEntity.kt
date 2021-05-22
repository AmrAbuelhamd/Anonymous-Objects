package com.blogspot.soyamr.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.blogspot.soyamr.data.db.DatabaseConfig.Tables.GEOLOCATION_TABLE


@Entity(tableName = GEOLOCATION_TABLE)
data class GeolocationEntity(
    val lat: Long,
    val lng: Long,
    @PrimaryKey
    val id: Int = 1,
)