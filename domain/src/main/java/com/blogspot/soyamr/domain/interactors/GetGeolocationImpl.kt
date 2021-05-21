package com.blogspot.soyamr.domain.interactors

import com.blogspot.soyamr.domain.ObjectDatasource
import com.blogspot.soyamr.domain.models.Geolocation

class GetGeolocationImpl(private val objectDatasource: ObjectDatasource) : GetGeolocation {
    override suspend fun invoke(): Geolocation = objectDatasource.getGeolocation()
}