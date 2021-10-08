package io.github.ravindragv.nasapictures.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import io.github.ravindragv.nasapictures.adapters.ImageDetailAdapter
import io.github.ravindragv.nasapictures.databinding.FragmentImageDetailBinding
import io.github.ravindragv.nasapictures.model.ImageMetaData
import io.github.ravindragv.nasapictures.model.ImageMetaDataViewModel

class ImageDetailFragment: Fragment() {
    private var binding: FragmentImageDetailBinding? = null
    private lateinit var imageMetaDataList: List<ImageMetaData>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val model: ImageMetaDataViewModel by activityViewModels()
        imageMetaDataList = model.getList()

        binding = FragmentImageDetailBinding.inflate(inflater, container, false)
        binding!!.vp2ImageDetail.adapter = ImageDetailAdapter(requireContext(), imageMetaDataList)
        binding!!.vp2ImageDetail.setCurrentItem(model.currentPosition, false)

        return binding!!.root
    }
}