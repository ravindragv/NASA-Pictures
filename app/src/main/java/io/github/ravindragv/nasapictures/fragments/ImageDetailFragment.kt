package io.github.ravindragv.nasapictures.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.ravindragv.nasapictures.adapters.ImageDetailAdapter
import io.github.ravindragv.nasapictures.databinding.FragmentImageDetailBinding
import io.github.ravindragv.nasapictures.factory.ImageLoaderFactory
import io.github.ravindragv.nasapictures.factory.ImageLoaders
import io.github.ravindragv.nasapictures.model.ImageMetaData
import io.github.ravindragv.nasapictures.model.ImageMetaDataViewModel

class ImageDetailFragment(private val model: ImageMetaDataViewModel): Fragment() {
    private var binding: FragmentImageDetailBinding? = null
    private lateinit var imageMetaDataList: List<ImageMetaData>
    private val imageLoader = ImageLoaderFactory.getImageLoader(ImageLoaders.GLIDE)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        imageMetaDataList = model.getList()

        binding = FragmentImageDetailBinding.inflate(inflater, container, false)
        binding!!.vp2ImageDetail.adapter = ImageDetailAdapter(requireContext(), imageMetaDataList, imageLoader)
        binding!!.vp2ImageDetail.setCurrentItem(model.currentPosition, false)

        return binding!!.root
    }
}
