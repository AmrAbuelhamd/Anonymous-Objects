package com.blogspot.soyamr.domain.interactors

import com.blogspot.soyamr.domain.models.Object
import kotlinx.coroutines.flow.Flow

interface GetObjectsList {
    operator fun invoke(): Flow<List<Object>>
}