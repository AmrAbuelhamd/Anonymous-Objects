package com.blogspot.soyamr.data

import com.blogspot.soyamr.data.converters.toDomain
import com.blogspot.soyamr.data.converters.toLocal
import com.blogspot.soyamr.data.db.dao.GeolocationDao
import com.blogspot.soyamr.data.db.dao.ObjectDao
import com.blogspot.soyamr.data.db.models.ObjectEntity
import com.blogspot.soyamr.data.local.LastUpdateHolder
import com.blogspot.soyamr.data.net.ObjectApi
import com.blogspot.soyamr.domain.ObjectDatasource
import com.blogspot.soyamr.domain.models.Geolocation
import com.blogspot.soyamr.domain.models.Object
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

private val TAG = "AMR::ObjectDatasourceImpl.baby "

class ObjectDatasourceImpl(
    private val objectDao: ObjectDao,
    private val geolocationDao: GeolocationDao,
    private val api: ObjectApi,
    private val lastUpdateHolder: LastUpdateHolder,
) : ObjectDatasource {

    override fun getObjectsList(): Flow<List<Object>> {
        return objectDao.getAll().map { list ->
            list.map { it.toDomain() }
        }.onStart {
            if (lastUpdateHolder.shouldWeUpdateCache())
                updateCacheFromServer()
        }
    }

    override suspend fun getObjectById(id: Int): Object {
        return objectDao.getById(id).toDomain()
    }

    override suspend fun getGeolocation(): Geolocation {
        return geolocationDao.getGeolocation().toDomain()
    }

    override suspend fun updateCacheFromServer() {
        api.getObjects().let { objectResponse ->
            geolocationDao.insert(objectResponse.location.toLocal())
            api.getTags().let { tagsResponse ->
                val objects = objectResponse.objects.objectX.map { objectItem ->
                    val tagsList =
                        tagsResponse.filter { objectItem.objectId == it.status.objectId }
                            .map { it.status.tag }
                    ObjectEntity(objectItem.name, objectItem.title, tagsList, objectItem.objectId)
                }
                objectDao.insertAll(objects)
                lastUpdateHolder.setNowAsLastUpdate()
            }
        }
    }
}
