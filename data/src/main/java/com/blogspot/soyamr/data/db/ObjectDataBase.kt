package com.blogspot.soyamr.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.blogspot.soyamr.data.db.DatabaseConfig.Parameters.DATABASE_VERSION
import com.blogspot.soyamr.data.db.converters.Converters
import com.blogspot.soyamr.data.db.dao.GeolocationDao
import com.blogspot.soyamr.data.db.dao.ObjectDao
import com.blogspot.soyamr.data.db.models.GeolocationEntity
import com.blogspot.soyamr.data.db.models.ObjectEntity


@Database(entities = [ObjectEntity::class, GeolocationEntity::class], version = DATABASE_VERSION)
@TypeConverters(Converters::class)
abstract class ObjectDataBase : RoomDatabase() {
    abstract fun objectDao(): ObjectDao
    abstract fun geolocationDao(): GeolocationDao
}