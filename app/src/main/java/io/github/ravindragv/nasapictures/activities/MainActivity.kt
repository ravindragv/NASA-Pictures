package io.github.ravindragv.nasapictures.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import io.github.ravindragv.nasapictures.R
import io.github.ravindragv.nasapictures.databinding.ActivityMainBinding
import io.github.ravindragv.nasapictures.factory.ImageFragmentFactory
import io.github.ravindragv.nasapictures.fragments.ImageGridFragment
import io.github.ravindragv.nasapictures.interfaces.NetworkObserver
import io.github.ravindragv.nasapictures.model.ImageMetaDataViewModel
import io.github.ravindragv.nasapictures.utilities.NetworkMonitor

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var model: ImageMetaDataViewModel
    private lateinit var networkMonitor: NetworkMonitor
    private lateinit var noNetworkSnackBar: Snackbar

    private val networkObserver = object: NetworkObserver {
        override fun onNetworkAvailable() {
            noNetworkSnackBar.dismiss()
        }

        override fun onNetworkUnavailable() {
            noNetworkSnackBar.show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        model = ViewModelProvider(this).get(ImageMetaDataViewModel::class.java)
        supportFragmentManager.fragmentFactory = ImageFragmentFactory(model)

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupFragment()
        setupNetworkMonitoring()
    }

    override fun onDestroy() {
        teardownNetworkMonitoring()
        super.onDestroy()
    }

    private fun setupNetworkMonitoring() {
        noNetworkSnackBar = Snackbar.make(findViewById(R.id.fragment_container),
            resources.getText(R.string.network_unavailable), Snackbar.LENGTH_INDEFINITE)

        networkMonitor = NetworkMonitor()
        networkMonitor.registerNetworkObserver(this, networkObserver)
    }

    private fun teardownNetworkMonitoring() {
        networkMonitor.unRegisterNetworkObserver()
    }

    private fun setupFragment() {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, ImageGridFragment::class.java, null)
        }
    }
}