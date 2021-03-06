package io.github.ravindragv.nasapictures.factory

import io.github.ravindragv.nasapictures.interfaces.ImageMetaDataJSONReader
import io.github.ravindragv.nasapictures.utilities.MoshiJSONReader

enum class JSONReader {
    MOSHI
}

class ImageMetaDataJSONReaderFactory {
    companion object {
        fun getReader(type: JSONReader, jsonString: String): ImageMetaDataJSONReader {
            return when (type) {
                JSONReader.MOSHI -> MoshiJSONReader(jsonString)
            }
        }
    }
}