package io.github.ravindragv.nasapictures.interfaces

/**
 * Get callbacks whenever there is a change in the network status
 */
interface NetworkObserver {
    /**
     * Called when the network is available
     */
    fun onNetworkAvailable()

    /**
     * Called when the network is unavailable
     */
    fun onNetworkUnavailable()
}