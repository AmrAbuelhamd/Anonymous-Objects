package com.blogspot.soyamr.anonymousobjects.app.di.data

import androidx.room.Room
import com.blogspot.soyamr.data.db.DatabaseConfig
import com.blogspot.soyamr.data.db.ObjectDataBase
import org.koin.dsl.module

val objectDataBaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            ObjectDataBase::class.java,
            DatabaseConfig.Parameters.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<ObjectDataBase>().geolocationDao() }
    single { get<ObjectDataBase>().objectDao() }
}