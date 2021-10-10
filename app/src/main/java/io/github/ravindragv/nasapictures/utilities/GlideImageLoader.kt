package io.github.ravindragv.nasapictures.utilities

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import io.github.ravindragv.nasapictures.interfaces.ImageLoader
import io.github.ravindragv.nasapictures.interfaces.ImageLoaderObserver

class GlideImageLoader: ImageLoader {
    private var observer: ImageLoaderObserver? = null

    override fun loadImage(context: Context,
                           view: ImageView,
                           fileURL: String,
                           cropToCenter: Boolean) {
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        if (cropToCenter) {
            Glide.with(context)
                .load(fileURL)
                .centerCrop()
                .placeholder(circularProgressDrawable)
                .listener(object: RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        observer?.loadFailed()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        observer?.loadCompleted()
                        return false
                    }
                })
                .into(view)
        } else {
            Glide.with(context)
                .load(fileURL)
                .placeholder(circularProgressDrawable)
                .listener(object: RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        observer?.loadFailed()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        observer?.loadCompleted()
                        return false
                    }
                })
                .into(view)
        }
    }

    override fun clearImage(context: Context, view: ImageView) {
        Glide.with(context).clear(view)
    }

    override fun addObserver(observer: ImageLoaderObserver) {
        this.observer = observer
    }

    override fun removeObserver() {
        observer = null
    }
}
