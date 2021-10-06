package io.github.ravindragv.nasapictures.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import io.github.ravindragv.nasapictures.R
import io.github.ravindragv.nasapictures.databinding.ActivityMainBinding
import io.github.ravindragv.nasapictures.fragments.ImageGridFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupFragment()
    }

    private fun setupFragment() {
        supportFragmentManager.commit {
            add<ImageGridFragment>(R.id.fragment_container)
        }
    }
}