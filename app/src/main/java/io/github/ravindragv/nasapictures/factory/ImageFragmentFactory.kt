package io.github.ravindragv.nasapictures.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import io.github.ravindragv.nasapictures.fragments.ImageDetailFragment
import io.github.ravindragv.nasapictures.fragments.ImageGridFragment
import io.github.ravindragv.nasapictures.model.ImageMetaDataViewModel

class ImageFragmentFactory(private val imageModel: ImageMetaDataViewModel): FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            ImageGridFragment::class.java.name -> ImageGridFragment(imageModel)
            ImageDetailFragment::class.java.name -> ImageDetailFragment(imageModel)
            else -> super.instantiate(classLoader, className)
        }
    }
}