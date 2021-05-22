package com.blogspot.soyamr.domain.usecases

interface UpdateCacheFromServer {
    suspend operator fun invoke()
}