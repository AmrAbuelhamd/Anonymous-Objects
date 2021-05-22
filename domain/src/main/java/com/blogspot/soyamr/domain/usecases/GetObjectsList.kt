package com.blogspot.soyamr.domain.usecases

import com.blogspot.soyamr.domain.models.Object
import kotlinx.coroutines.flow.Flow

interface GetObjectsList {
    operator fun invoke(): Flow<List<Object>>
}