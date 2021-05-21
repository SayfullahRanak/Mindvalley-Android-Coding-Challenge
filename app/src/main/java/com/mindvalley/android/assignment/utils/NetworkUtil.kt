package com.mindvalley.android.assignment.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.util.Log
import okhttp3.OkHttpClient
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class NetworkUtil {
    companion object{
        const val TYPE_WIFI = 1
        const val TYPE_MOBILE = 2
        const val TYPE_ETHERNET = 3
        const val TYPE_NOT_CONNECTED = 0
        const val NETWORK_STATUS_NOT_CONNECTED = 0
        const val NETWORK_STATUS_WIFI = 1
        const val NETWORK_STATUS_MOBILE = 2
        const val NETWORK_STATUS_ETHERNET = 3

        @Suppress("DEPRECATION")
        fun getConnectivityStatus(context: Context): Int {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val connectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    when {
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                            Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                            return TYPE_MOBILE
                        }
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                            Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                            return TYPE_WIFI
                        }
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                            Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                            return TYPE_ETHERNET
                        }
                    }
                }
                return TYPE_NOT_CONNECTED
            }else{
                val cm: ConnectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork: NetworkInfo = cm.getActiveNetworkInfo()!!
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) return TYPE_WIFI
                if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) return TYPE_MOBILE
                return TYPE_NOT_CONNECTED
            }
        }

        fun getConnectivityStatusString(context: Context): Int {
            val conn = getConnectivityStatus(context)
            var status = 0
            if (conn == TYPE_WIFI) {
                status = NETWORK_STATUS_WIFI
            } else if (conn == TYPE_MOBILE) {
                status = NETWORK_STATUS_MOBILE
            } else if (conn == TYPE_NOT_CONNECTED) {
                status = NETWORK_STATUS_NOT_CONNECTED
            }
            return status
        }

        fun getUnsafeOkHttpClient(): OkHttpClient.Builder =
            try {
                // Create a trust manager that does not validate certificate chains
                val trustAllCerts: Array<TrustManager> = arrayOf(
                    object : X509TrustManager {
                        @Throws(CertificateException::class)
                        override fun checkClientTrusted(chain: Array<X509Certificate?>?,
                                                        authType: String?) = Unit

                        @Throws(CertificateException::class)
                        override fun checkServerTrusted(chain: Array<X509Certificate?>?,
                                                        authType: String?) = Unit

                        override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
                    }
                )
                // Install the all-trusting trust manager
                val sslContext: SSLContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, SecureRandom())
                // Create an ssl socket factory with our all-trusting manager
                val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
                val builder = OkHttpClient.Builder()
                builder.sslSocketFactory(sslSocketFactory,
                    trustAllCerts[0] as X509TrustManager)
                builder.hostnameVerifier { _, _ -> true }
                builder
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
    }
        
}