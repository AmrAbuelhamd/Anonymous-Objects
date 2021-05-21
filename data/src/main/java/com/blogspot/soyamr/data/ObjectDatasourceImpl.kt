package com.blogspot.soyamr.data

import com.blogspot.soyamr.domain.ObjectDatasource
import com.blogspot.soyamr.domain.models.Geolocation
import com.blogspot.soyamr.domain.models.Object
import kotlinx.coroutines.flow.Flow

class ObjectDatasourceImpl : ObjectDatasource {

    override fun getObjectsList(): Flow<List<Object>> {
        TODO("Not yet implemented")
    }

    override suspend fun getObjectById(id: Int): Object {
        TODO("Not yet implemented")
    }

    override suspend fun getGeolocation(): Geolocation {
        TODO("Not yet implemented")
    }
}