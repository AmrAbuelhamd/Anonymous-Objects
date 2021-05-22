package com.blogspot.soyamr.domain.usecases;

import com.blogspot.soyamr.domain.ObjectDatasource;
import com.blogspot.soyamr.domain.models.Object

class GetObjectByIdImpl(private val objectDatasource: ObjectDatasource) : GetObjectById {
    override suspend fun invoke(id: Int): Object = objectDatasource.getObjectById(id)
}
