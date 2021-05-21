package com.blogspot.soyamr.domain.interactors

import com.blogspot.soyamr.domain.models.Geolocation

interface GetGeolocation {
    suspend operator fun invoke(): Geolocation
}