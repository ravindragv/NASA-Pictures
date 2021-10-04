package io.github.ravindragv.nasapictures.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class ImageMetaData(
    @Json(name = "copyright")
    val copyright: String?,
    @Json(name = "date")
    val date: Date,
    @Json(name = "explanation")
    val explanation: String,
    @Json(name = "hdurl")
    val hdurl: String,
    @Json(name = "media_type")
    val media_type: String,
    @Json(name = "service_version")
    val service_version: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "url")
    val url: String
)
