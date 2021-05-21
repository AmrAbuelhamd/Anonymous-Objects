package com.blogspot.soyamr.domain.interactors

import com.blogspot.soyamr.domain.models.Object

interface GetObjectById {
    suspend operator fun invoke(id: Int): Object
}