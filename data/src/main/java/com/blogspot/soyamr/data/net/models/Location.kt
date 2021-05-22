package com.blogspot.soyamr.data.net.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    @SerialName("gps_lat")
    val gpsLat: Double,
    @SerialName("gps_lng")
    val gpsLng: Double
)