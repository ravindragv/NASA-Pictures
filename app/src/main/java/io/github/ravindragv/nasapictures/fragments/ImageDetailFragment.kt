package io.github.ravindragv.nasapictures.fragments

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import io.github.ravindragv.nasapictures.R
import io.github.ravindragv.nasapictures.activities.MainActivity
import io.github.ravindragv.nasapictures.adapters.ImageDetailAdapter
import io.github.ravindragv.nasapictures.databinding.FragmentImageDetailBinding
import io.github.ravindragv.nasapictures.factory.ImageLoaderFactory
import io.github.ravindragv.nasapictures.factory.ImageLoaders
import io.github.ravindragv.nasapictures.interfaces.ImageLoaderObserver
import io.github.ravindragv.nasapictures.model.ImageMetaData
import io.github.ravindragv.nasapictures.model.ImageMetaDataViewModel

class ImageDetailFragment(private val model: ImageMetaDataViewModel): Fragment(), ImageLoaderObserver {
    private var binding: FragmentImageDetailBinding? = null
    private lateinit var imageMetaDataList: List<ImageMetaData>
    private val imageLoader = ImageLoaderFactory.getImageLoader(ImageLoaders.GLIDE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.shared_image)
        imageLoader.addObserver(this)
    }

    override fun onDestroy() {
        imageLoader.removeObserver()
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentFragment?.postponeEnterTransition()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if(requireActivity() is MainActivity) {
            val app = activity as AppCompatActivity
            app.supportActionBar?.hide()
        }

        imageMetaDataList = model.getList()

        binding = FragmentImageDetailBinding.inflate(inflater, container, false)
        binding!!.vp2ImageDetail.adapter = ImageDetailAdapter(requireContext(), imageMetaDataList, imageLoader)
        binding!!.vp2ImageDetail.setCurrentItem(model.currentPosition, false)

        return binding!!.root
    }

    override fun loadCompleted() {
        parentFragment?.startPostponedEnterTransition()
    }

    override fun loadFailed() {
        parentFragment?.startPostponedEnterTransition()
    }
}
