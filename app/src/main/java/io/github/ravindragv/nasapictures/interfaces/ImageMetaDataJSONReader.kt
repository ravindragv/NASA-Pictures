package io.github.ravindragv.nasapictures.interfaces

import io.github.ravindragv.nasapictures.model.ImageMetaData

interface ImageMetaDataJSONReader {
    fun getObjectsOrderedByLatestDate(): List<ImageMetaData>
}