package com.blogspot.soyamr.domain.models

data class Object(
    val id: Int,
    val name: String,
    val title: String,
    val tags: List<String>?,
)