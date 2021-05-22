package com.blogspot.soyamr.anonymousobjects.app.di.utils

import com.blogspot.soyamr.anonymousobjects.app.utils.ConnectivityImpl
import com.blogspot.soyamr.domain.utils.Connectivity
import org.koin.dsl.module


val utilsModule = module {
    factory { ConnectivityImpl(get()) as Connectivity }
}