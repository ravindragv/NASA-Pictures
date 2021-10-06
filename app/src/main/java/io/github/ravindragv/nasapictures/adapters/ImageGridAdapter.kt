package io.github.ravindragv.nasapictures.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.ravindragv.nasapictures.databinding.ThumbnailViewBinding
import io.github.ravindragv.nasapictures.interfaces.ImageLoaderFactory
import io.github.ravindragv.nasapictures.interfaces.ImageLoaders
import io.github.ravindragv.nasapictures.model.ImageMetaData

class ImageGridAdapter(private val context: Context,
                       private val imageMetaDataList: List<ImageMetaData>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var onClickListener: OnClickListener

    private val imageLoader = ImageLoaderFactory.getImageLoader(ImageLoaders.GLIDE)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageGridAdapterViewHolder(ThumbnailViewBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ImageGridAdapterViewHolder) {
            Log.e("Scratch", "Trying to load "+ imageMetaDataList[position].url)
            Log.e("Scratch", "Date is "+ imageMetaDataList[position].date)

            imageLoader.loadImage(context, holder.binding.ivThumbnail, imageMetaDataList[position].url)

            holder.binding.ivThumbnail.setOnClickListener {
                onClickListener.onClick(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return imageMetaDataList.size
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder is ImageGridAdapterViewHolder) {
            imageLoader.clearImage(context, holder.binding.ivThumbnail)
        }
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener{
        fun onClick(imagePosition: Int)
    }

    inner class ImageGridAdapterViewHolder(val binding: ThumbnailViewBinding)
        : RecyclerView.ViewHolder(binding.root)
}