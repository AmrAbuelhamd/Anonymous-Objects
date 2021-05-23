package com.blogspot.soyamr.data.converters

import com.blogspot.soyamr.data.db.models.GeolocationEntity
import com.blogspot.soyamr.data.db.models.ObjectEntity
import com.blogspot.soyamr.data.net.models.Location
import com.blogspot.soyamr.domain.models.Geolocation
import com.blogspot.soyamr.domain.models.Object

fun ObjectEntity.toDomain() = Object(id, name, title, tags)

fun GeolocationEntity.toDomain() = Geolocation(lat, lng)

fun Location.toLocal() = GeolocationEntity(gpsLat, gpsLng)