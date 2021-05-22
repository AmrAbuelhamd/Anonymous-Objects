package com.blogspot.soyamr.anonymousobjects.app.di.domain

import com.blogspot.soyamr.domain.usecases.*
import org.koin.dsl.module

val useCasesModule = module {
    factory { GetGeolocationImpl(get()) as GetGeolocation }
    factory { GetObjectsListImpl(get()) as GetObjectsList}
    factory { GetObjectByIdImpl(get()) as GetObjectById}
    factory { UpdateCacheFromServerImpl(get()) as UpdateCacheFromServer}
}