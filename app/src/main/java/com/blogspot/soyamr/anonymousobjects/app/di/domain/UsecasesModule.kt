package com.blogspot.soyamr.anonymousobjects.app.di.domain

import com.blogspot.soyamr.domain.usecases.GetGeolocationImpl
import com.blogspot.soyamr.domain.usecases.GetObjectByIdImpl
import com.blogspot.soyamr.domain.usecases.GetObjectsListImpl
import com.blogspot.soyamr.domain.usecases.UpdateCacheFromServerImpl
import org.koin.dsl.module

val useCasesModule = module {
    factory { GetGeolocationImpl(get()) }
    factory { GetObjectsListImpl(get()) }
    factory { GetObjectByIdImpl(get()) }
    factory { UpdateCacheFromServerImpl(get()) }
}