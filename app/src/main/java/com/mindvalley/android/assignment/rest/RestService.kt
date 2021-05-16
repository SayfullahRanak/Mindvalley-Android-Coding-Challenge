package com.sevenpeakssoftware.sayfullah.rest

import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Md Sayfullah Al Noman Ranak
 */

interface RestService {

    @GET("application/")
     suspend fun getCars(): Response<Any>
}