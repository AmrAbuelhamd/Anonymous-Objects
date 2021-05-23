package com.blogspot.soyamr.anonymousobjects.app.di.presentation

import com.blogspot.soyamr.anonymousobjects.presentation.geolocation.GeolocationViewModel
import com.blogspot.soyamr.anonymousobjects.presentation.object_list.ObjectListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { GeolocationViewModel(get(),get(),) }
    viewModel { ObjectListViewModel(get(),get(),) }
}