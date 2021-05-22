package com.blogspot.soyamr.anonymousobjects.app.di.data

import com.blogspot.soyamr.anonymousobjects.R
import com.blogspot.soyamr.data.net.ObjectApi
import com.blogspot.soyamr.data.net.interceptors.InternetExceptionInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

const val LOGGER_INTERCEPTOR = "logger_interceptor"
const val INTERNET_EXCEPTION_HANDLER_INTERCEPTOR = "internet_exception_handler_interceptor"

val objectApiModule = module {

    factory<Interceptor>(named(INTERNET_EXCEPTION_HANDLER_INTERCEPTOR)) {
        InternetExceptionInterceptor(get())
    }

    factory<Interceptor>(named(LOGGER_INTERCEPTOR)) {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>(named(INTERNET_EXCEPTION_HANDLER_INTERCEPTOR)))
            .addInterceptor(get<Interceptor>(named(LOGGER_INTERCEPTOR)))
            .build()
    }

    single {
        Json {
            ignoreUnknownKeys = true
        }.asConverterFactory("application/json".toMediaType())
    }

    single {
        Retrofit.Builder()
            .baseUrl(androidContext().getString(R.string.object_base_url))
            .client(get<OkHttpClient>())
            .addConverterFactory(get())
            .build()
    }

    single { get<Retrofit>().create(ObjectApi::class.java) }
}