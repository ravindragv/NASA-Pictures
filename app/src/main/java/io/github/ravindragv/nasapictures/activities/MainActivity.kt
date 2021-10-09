package io.github.ravindragv.nasapictures.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import io.github.ravindragv.nasapictures.R
import io.github.ravindragv.nasapictures.databinding.ActivityMainBinding
import io.github.ravindragv.nasapictures.factory.ImageFragmentFactory
import io.github.ravindragv.nasapictures.fragments.ImageGridFragment
import io.github.ravindragv.nasapictures.model.ImageMetaDataViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var model: ImageMetaDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        model = ViewModelProvider(this).get(ImageMetaDataViewModel::class.java)
        setupFragment()
    }

    private fun setupFragment() {
        supportFragmentManager.fragmentFactory = ImageFragmentFactory(model)
        supportFragmentManager.commit {
            add(R.id.fragment_container, ImageGridFragment::class.java, null)
        }
    }
}