package io.github.ravindragv.nasapictures.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import io.github.ravindragv.nasapictures.R
import io.github.ravindragv.nasapictures.adapters.ImageGridAdapter
import io.github.ravindragv.nasapictures.databinding.FragmentImageGridBinding
import io.github.ravindragv.nasapictures.model.ImageMetaData
import io.github.ravindragv.nasapictures.model.ImageMetaDataViewModel

class ImageGridFragment
    constructor(private val model: ImageMetaDataViewModel)
    : Fragment() {
    private var binding: FragmentImageGridBinding? = null
    private lateinit var imageMetaDataList: List<ImageMetaData>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        imageMetaDataList = model.getList()

        binding = FragmentImageGridBinding.inflate(inflater, container, false)
        binding!!.rvImageGrid.layoutManager = GridLayoutManager(requireContext(), 2)
        val adapter = ImageGridAdapter(requireContext(), imageMetaDataList)
        adapter.setOnClickListener(object: ImageGridAdapter.OnClickListener{
            override fun onClick(imagePosition: Int) {
                model.currentPosition = imagePosition

                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, ImageDetailFragment(model))
                    .addToBackStack(null)
                    .commit()
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