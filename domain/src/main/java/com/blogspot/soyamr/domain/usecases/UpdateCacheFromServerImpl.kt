package com.blogspot.soyamr.domain.usecases

import com.blogspot.soyamr.domain.ObjectDatasource

class UpdateCacheFromServerImpl(private val objectDatasource: ObjectDatasource) : UpdateCacheFromServer {
    override suspend fun invoke() = objectDatasource.updateCacheFromServer()
}