package io.github.ravindragv.nasapictures.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.ravindragv.nasapictures.databinding.ImageDetailBinding
import io.github.ravindragv.nasapictures.interfaces.ImageLoaderFactory
import io.github.ravindragv.nasapictures.interfaces.ImageLoaders
import io.github.ravindragv.nasapictures.model.ImageMetaData
import java.text.SimpleDateFormat

class ImageDetailAdapter(private val context: Context,
                         private val imageMetaDataList: List<ImageMetaData>) :
    RecyclerView.Adapter<ImageDetailAdapter.ImageDetailViewAdapterViewHolder>() {
    private val imageLoader = ImageLoaderFactory.getImageLoader(ImageLoaders.GLIDE)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageDetailAdapter.ImageDetailViewAdapterViewHolder {
        return ImageDetailViewAdapterViewHolder(ImageDetailBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))
    }

    /**
     * Load the image and the meta data onto the view.
     */
    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ImageDetailAdapter.ImageDetailViewAdapterViewHolder,
                                  position: Int) {
        imageLoader.loadImage(context, holder.binding.ivFullImage, imageMetaDataList[position].url)

        if (imageMetaDataList[position].copyright != null) {
            holder.binding.tvCopyright.visibility = View.VISIBLE
            holder.binding.tvCopyright.text = imageMetaDataList[position].copyright.toString()
        } else {
            holder.binding.tvCopyright.visibility = View.GONE
        }

        holder.binding.tvDate.text = SimpleDateFormat("yyyy-MM-dd").format(imageMetaDataList[position].date)
        holder.binding.tvTitle.text = imageMetaDataList[position].title
        holder.binding.tvExplanation.text = imageMetaDataList[position].explanation
    }

    override fun getItemCount(): Int {
        return imageMetaDataList.size
    }

    /**
     * CLear the image from the image view, since view is about to get reused
     */
    override fun onViewRecycled(holder: ImageDetailViewAdapterViewHolder) {
        imageLoader.clearImage(context, holder.binding.ivFullImage)
    }

    inner class ImageDetailViewAdapterViewHolder(val binding: ImageDetailBinding)
        : RecyclerView.ViewHolder(binding.root)
}