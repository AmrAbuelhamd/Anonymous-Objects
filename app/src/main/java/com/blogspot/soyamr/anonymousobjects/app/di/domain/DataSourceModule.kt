package com.blogspot.soyamr.anonymousobjects.app.di.domain

import com.blogspot.soyamr.data.ObjectDatasourceImpl
import com.blogspot.soyamr.domain.ObjectDatasource
import org.koin.dsl.module

val dataSourceModule = module {
    factory { ObjectDatasourceImpl(get(), get(), get(), get()) as ObjectDatasource }
}