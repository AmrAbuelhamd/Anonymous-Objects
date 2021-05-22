package com.blogspot.soyamr.data.local

interface LastUpdateHolder {
    suspend fun shouldWeUpdateCache(): Boolean
    suspend fun setNowAsLastUpdate()
}