package io.github.ravindragv.nasapictures.utilities

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import io.github.ravindragv.nasapictures.interfaces.ImageLoader

class GlideImageLoader(): ImageLoader {
    override fun loadImage(context: Context, view: ImageView, fileURL: String) {
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(context)
            .load(fileURL)
            .centerCrop()
            .placeholder(circularProgressDrawable)
            .into(view)
    }

    override fun clearImage(context: Context, view: ImageView) {
        Glide.with(context).clear(view)
    }
}