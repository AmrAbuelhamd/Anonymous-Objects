package com.blogspot.soyamr.data.db.dao

import android.media.MediaPlayer
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.blogspot.soyamr.data.db.DatabaseConfig.Tables.GEOLOCATION_TABLE
import com.blogspot.soyamr.data.db.models.GeolocationEntity

@Dao
interface GeolocationDao {

    @Query("select * from $GEOLOCATION_TABLE limit 1")
    suspend fun getGeolocation():GeolocationEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(geolocationEntity: GeolocationEntity)
}