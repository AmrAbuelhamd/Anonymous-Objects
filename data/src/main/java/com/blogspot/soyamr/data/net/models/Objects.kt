package com.blogspot.soyamr.data.net.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Objects(
    @SerialName("object")
    val objectX: List<Object>
)