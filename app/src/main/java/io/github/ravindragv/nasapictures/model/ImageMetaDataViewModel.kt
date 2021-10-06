package io.github.ravindragv.nasapictures.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.github.ravindragv.nasapictures.R
import io.github.ravindragv.nasapictures.interfaces.ImageMetaDataJSONReaderFactory
import io.github.ravindragv.nasapictures.interfaces.JSONReader

class ImageMetaDataViewModel(application: Application): AndroidViewModel(
    application
) {

    private var imageMetaData: List<ImageMetaData>
    var currentPosition: Int = 0

    init {
        val jsonString = application.resources.openRawResource(R.raw.data)
            .bufferedReader().use { it.readText() }
        val jsonReader = ImageMetaDataJSONReaderFactory
            .getReader(JSONReader.MOSHI, jsonString)

        imageMetaData = jsonReader.getObjectsOrderedByLatestDate()
    }

    fun getList(): List<ImageMetaData> {
        return imageMetaData
    }
}