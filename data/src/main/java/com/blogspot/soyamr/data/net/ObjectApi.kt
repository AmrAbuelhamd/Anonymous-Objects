package com.blogspot.soyamr.data.net

import com.blogspot.soyamr.data.net.models.ObjectResponse
import com.blogspot.soyamr.data.net.models.TagsResponseItem
import retrofit2.http.GET

interface ObjectApi {
    @GET("ufwuccum01rchdl/entity.json")
    suspend fun getObjects(): ObjectResponse

    @GET("c9o1x8i45q5872k/statuses.json")
    suspend fun getTags(): List<TagsResponseItem>
}