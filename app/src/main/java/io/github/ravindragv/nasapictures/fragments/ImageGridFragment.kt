package io.github.ravindragv.nasapictures.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import io.github.ravindragv.nasapictures.adapters.ImageGridAdapter
import io.github.ravindragv.nasapictures.databinding.FragmentImageGridBinding
import io.github.ravindragv.nasapictures.model.ImageMetaData
import io.github.ravindragv.nasapictures.model.ImageMetaDataViewModel

class ImageGridFragment: Fragment() {
    private var binding: FragmentImageGridBinding? = null
    private lateinit var imageMetaDataList: List<ImageMetaData>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val model: ImageMetaDataViewModel by activityViewModels()
        imageMetaDataList = model.getList()

        binding = FragmentImageGridBinding.inflate(inflater, container, false)
        binding!!.rvImageGrid.layoutManager = GridLayoutManager(requireContext(), 2)
        val adapter = ImageGridAdapter(requireContext(), imageMetaDataList)
        adapter.setOnClickListener(object: ImageGridAdapter.OnClickListener{
            override fun onClick(imagePosition: Int) {
                model.currentPosition = imagePosition
            }
        })

        binding!!.rvImageGrid.adapter = adapter

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}