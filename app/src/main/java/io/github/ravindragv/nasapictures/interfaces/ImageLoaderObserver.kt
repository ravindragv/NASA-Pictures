package io.github.ravindragv.nasapictures.interfaces

/**
 * Should be used along with ImageLoader. Create an object which implements this interface and add
 * that object as an observer to ImageLoader. The ImageLoader will then call the below functions
 */
interface ImageLoaderObserver {
    /**
     * Is called when image load is completed into the view
     */
    fun loadCompleted()

    /**
     * Is called when the ImageLoader failed to load the image into the view
     */
    fun loadFailed()
}