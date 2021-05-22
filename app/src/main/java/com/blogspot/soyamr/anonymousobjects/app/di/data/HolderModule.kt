package com.blogspot.soyamr.anonymousobjects.app.di.data

import com.blogspot.soyamr.data.local.LastUpdateHolder
import com.blogspot.soyamr.data.local.LastUpdateHolderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val holderModule = module {
    single { LastUpdateHolderImpl(androidContext()) as LastUpdateHolder }
}