package com.blogspot.soyamr.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.blogspot.soyamr.data.db.DatabaseConfig.Tables.OBJECT_TABLE
import com.blogspot.soyamr.data.db.models.ObjectEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ObjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(objectsList: List<ObjectEntity>)

    @Query("select * from $OBJECT_TABLE order by tags, name asc, title asc")
    fun getAll(): Flow<List<ObjectEntity>>

    @Query("select * from $OBJECT_TABLE where id like :id")
    suspend fun getById(id: Int): ObjectEntity
}