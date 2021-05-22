package com.blogspot.soyamr.domain.usecases

import com.blogspot.soyamr.domain.models.Geolocation

interface GetGeolocation {
    suspend operator fun invoke(): Geolocation
}