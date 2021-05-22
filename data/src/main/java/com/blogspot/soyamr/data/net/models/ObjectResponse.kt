package com.blogspot.soyamr.data.net.models


import kotlinx.serialization.Serializable

@Serializable
data class ObjectResponse(
    val name: String,
    val location: Location,
    val objects: Objects
)