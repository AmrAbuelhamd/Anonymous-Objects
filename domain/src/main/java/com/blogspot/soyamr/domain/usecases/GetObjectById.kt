package com.blogspot.soyamr.domain.usecases

import com.blogspot.soyamr.domain.models.Object

interface GetObjectById {
    suspend operator fun invoke(id: Int): Object
}