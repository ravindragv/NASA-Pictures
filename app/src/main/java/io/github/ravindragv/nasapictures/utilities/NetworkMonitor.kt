package io.github.ravindragv.nasapictures.utilities

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import io.github.ravindragv.nasapictures.interfaces.NetworkObserver

class NetworkMonitor: ConnectivityManager.NetworkCallback() {
    private lateinit var connectivityManager: ConnectivityManager
    private var observer: NetworkObserver? = null

    fun registerNetworkObserver(context: Context, observer: NetworkObserver) {
        // Register for network callback, this accounts for changes to the network in the future
        connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkBuilder = NetworkRequest.Builder()
        networkBuilder.addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        networkBuilder.addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        connectivityManager.registerNetworkCallback(networkBuilder.build(), this)

        this.observer = observer

        // Check if the network is available now, if it is not
        // inform the observer that network is unavailable
        checkIfNetworkAvailableNow()
    }

    private fun checkIfNetworkAvailableNow() {
        var networkAvailable = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
                networkAvailable = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                networkAvailable = when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    else -> false
                }
            }
        }

        if (!networkAvailable) {
            observer?.onNetworkUnavailable()
        }
    }

    fun unRegisterNetworkObserver() {
        connectivityManager.unregisterNetworkCallback(this)
        observer = null
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        observer?.onNetworkAvailable()
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        observer?.onNetworkUnavailable()
    }
}