package com.mindvalley.android.assignment.di

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

/**
 *
 * This class makes the Retrofit client
 */

class RetrofitServiceGenerator @Inject constructor(
    private val converter: GsonConverterFactory,
    private val httpClient: OkHttpClient.Builder,
    private val baseURL: String
) {

    /**
     * Creates a Retrofit Client object
     */
    fun getClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(converter)
            .client(httpClient.build())
            .build()
    }
}