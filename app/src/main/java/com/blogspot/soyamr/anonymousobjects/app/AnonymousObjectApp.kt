package com.blogspot.soyamr.anonymousobjects.app

import android.app.Application
import com.blogspot.soyamr.anonymousobjects.app.di.data.holderModule
import com.blogspot.soyamr.anonymousobjects.app.di.data.objectApiModule
import com.blogspot.soyamr.anonymousobjects.app.di.data.objectDataBaseModule
import com.blogspot.soyamr.anonymousobjects.app.di.domain.dataSourceModule
import com.blogspot.soyamr.anonymousobjects.app.di.domain.useCasesModule
import com.blogspot.soyamr.anonymousobjects.app.di.presentation.viewModelModule
import com.blogspot.soyamr.anonymousobjects.app.di.utils.utilsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AnonymousObjectApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AnonymousObjectApp)
            modules(
                listOf(
                    viewModelModule,
                    dataSourceModule,
                    objectDataBaseModule,
                    holderModule,
                    useCasesModule,
                    utilsModule,
                    objectApiModule,
                )
            )
        }
    }
}