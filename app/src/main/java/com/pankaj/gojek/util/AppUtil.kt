package com.pankaj.gojek.util

import android.content.Context
import android.net.ConnectivityManager

object AppUtil {

    fun isNetworkAvailable(context: Context): Boolean {
        try {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        } catch (e: Exception) {
            // Exception: saying no permission, but we are asking for permission.
        }
        // internet is available, which is a buggy state.
        return true
    }
}