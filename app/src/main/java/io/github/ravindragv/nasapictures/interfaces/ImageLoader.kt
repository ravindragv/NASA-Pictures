package io.github.ravindragv.nasapictures.interfaces

import android.content.Context
import android.widget.ImageView

/**
 * The user of this interface will be able to load an image into an image view and clear the
 * the image from the image view. The user doesn't need to worry about how the underlying library
 * will load the image into the view
 *
 * The interface implementor should provide these functionalities to the user and these
 * functionalities will hide the implementation details on how the image is loaded onto the view
 * and how it is cleared from the view.
 */
interface ImageLoader {
    /**
     *  Loads an image into an imageview
     *
     *  @param context - Application context in which the ImageLoader will work
     *  @param view - Image view into which the image needs to be loaded into
     *  @param fileURL - URL of the image that needs to be loaded into the image view
     *  @param cropToCenter - Should the ImageLoader perform cropping to the center
     */
    fun loadImage(context: Context, view: ImageView, fileURL: String, cropToCenter: Boolean = false)

    /**
     * Clears an image from the image view
     *
     * @param context - Application context in which the ImageLoader will work
     * @param view - Image view into which the image needs to be loaded into
     */
    fun clearImage(context: Context, view: ImageView)

    /**
     * Add an observer object
     *
     * @param observer - An object of ImageLoaderObserver, the user of this interface is interested
     * in knowing when an image has been loaded successfully or if it failed.
     */
    fun addObserver(observer: ImageLoaderObserver)

    /**
     * Remove an observer object
     *
     * The user of this interface is not interested in listening for events related to image loading
     */
    fun removeObserver()
}