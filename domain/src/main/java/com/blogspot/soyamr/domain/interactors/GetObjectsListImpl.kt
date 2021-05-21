package com.blogspot.soyamr.domain.interactors

import com.blogspot.soyamr.domain.ObjectDatasource
import com.blogspot.soyamr.domain.models.Object
import kotlinx.coroutines.flow.Flow

class GetObjectsListImpl(private val objectDatasource: ObjectDatasource) : GetObjectsList {
    override fun invoke(): Flow<List<Object>> = objectDatasource.getObjectsList()
}