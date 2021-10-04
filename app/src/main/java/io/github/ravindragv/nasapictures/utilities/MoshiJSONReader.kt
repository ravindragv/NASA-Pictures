package io.github.ravindragv.nasapictures.utilities

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.github.ravindragv.nasapictures.interfaces.ImageMetaDataJSONReader
import io.github.ravindragv.nasapictures.model.ImageMetaData
import java.util.*

class MoshiJSONReader (jsonString: String): ImageMetaDataJSONReader {
    private var moshi: Moshi
    private var jsonAdapter: JsonAdapter<List<ImageMetaData>>
    private var imageMetaDataList: List<ImageMetaData>? = null

    init  {
        if (jsonString.isEmpty())
            throw IllegalArgumentException("JSON String cannot be empty")

        moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Date::class.java, Rfc3339DateJsonAdapter())
            .build()

        val listImageMetaData = Types.newParameterizedType(List::class.java,
            ImageMetaData::class.java)

        jsonAdapter = moshi.adapter(listImageMetaData)

        imageMetaDataList = jsonAdapter.fromJson(jsonString)
        if (imageMetaDataList == null) {
            throw IllegalArgumentException("Failed to read ImageMetaData object from JSON String")
        }
    }

    override fun getObjectsOrderedByLatestDate(): List<ImageMetaData> {
        return imageMetaDataList!!.sortedByDescending { it.date }
    }
}