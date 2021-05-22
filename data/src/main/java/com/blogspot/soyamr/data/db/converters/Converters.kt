package com.blogspot.soyamr.data.db.converters

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    @TypeConverter
    fun fromList(tags: List<Int>?) =
        if (tags.isNullOrEmpty()) ""
        else Json.encodeToString(tags)

    @TypeConverter
    fun toList(tags: String?) =
        if (tags.isNullOrBlank()) null
        else Json.decodeFromString<List<Int>>(tags)
}