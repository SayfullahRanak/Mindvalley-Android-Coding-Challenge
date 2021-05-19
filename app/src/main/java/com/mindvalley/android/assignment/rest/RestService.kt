package com.mindvalley.android.assignment.rest

import com.mindvalley.android.assignment.entities.Categories
import com.mindvalley.android.assignment.entities.Channels
import com.mindvalley.android.assignment.entities.NewEpisodes
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Md Sayfullah Al Noman Ranak
 */

interface RestService {



    @GET("raw/A0CgArX3")
    suspend fun getCategories(): Response<Categories>


    @GET("raw/z5AExTtw")
    suspend fun getNewEpisodes(): Response<NewEpisodes>


    @GET("raw/z5AExTtw")
    suspend fun getChannels(): Response<Channels>




}