package com.blogspot.soyamr.data.net.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Status(
    @SerialName("object_id")
    val objectId: Int,
    val tag: Int
)