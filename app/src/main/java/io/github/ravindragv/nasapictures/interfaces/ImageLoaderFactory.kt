package io.github.ravindragv.nasapictures.interfaces

import io.github.ravindragv.nasapictures.utilities.GlideImageLoader

enum class ImageLoaders {
    GLIDE
}

class ImageLoaderFactory {
    companion object {
        fun getImageLoader(type: ImageLoaders): ImageLoader {
            return when (type) {
                ImageLoaders.GLIDE -> GlideImageLoader()
            }
        }
    }
}