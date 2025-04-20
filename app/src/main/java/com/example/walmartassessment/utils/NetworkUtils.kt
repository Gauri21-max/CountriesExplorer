package com.example.walmartassessment.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * A utility object that provides network-related helper functions.
 * This particular function checks whether the device has an active internet connection.
 */
object NetworkUtils {

    /**
     * Checks whether the device has an active network connection.
     * This method uses the `ConnectivityManager` to retrieve the active network state.
     *
     * @param context The context of the application, used to access system services.
     * @return True if the device is connected to a network or is in the process of connecting.
     *         False otherwise.
     */
    fun isNetworkAvailable(context: Context): Boolean {
        // Get the ConnectivityManager system service to manage network connections
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // Check if there is an active network connection and it is either connected or connecting
        return cm.activeNetworkInfo?.isConnectedOrConnecting == true
    }
}
