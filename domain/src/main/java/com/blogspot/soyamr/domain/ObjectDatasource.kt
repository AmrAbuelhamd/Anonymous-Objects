package com.blogspot.soyamr.domain

import com.blogspot.soyamr.domain.models.Geolocation
import com.blogspot.soyamr.domain.models.Object
import kotlinx.coroutines.flow.Flow

interface ObjectDatasource {
    fun getObjectsList(): Flow<List<Object>>
    suspend fun getObjectById(id: Int): Object
    suspend fun getGeolocation(): Geolocation
    suspend fun updateCacheFromServer()
}