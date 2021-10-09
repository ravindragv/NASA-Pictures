package io.github.ravindragv.nasapictures

import io.github.ravindragv.nasapictures.interfaces.ImageLoader
import io.github.ravindragv.nasapictures.factory.ImageLoaderFactory
import io.github.ravindragv.nasapictures.factory.ImageLoaders
import io.github.ravindragv.nasapictures.utilities.GlideImageLoader
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ImageLoaderFactoryUnitTest {
    private lateinit var glideImageLoader: ImageLoader

    @Before
    fun init() {
        glideImageLoader = ImageLoaderFactory.getImageLoader(ImageLoaders.GLIDE)
    }

    @Test
    fun `Check if object is actually GlideImageLoader`() {
        Assert.assertTrue(glideImageLoader is GlideImageLoader)
    }
}