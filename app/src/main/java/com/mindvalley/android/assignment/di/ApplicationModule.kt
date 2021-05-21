package com.mindvalley.android.assignment.di

import android.content.Context
import android.os.Build
import android.util.Log
import com.mindvalley.android.assignment.db.AppDatabase
import com.mindvalley.android.assignment.utils.Cons
import com.mindvalley.android.assignment.rest.RestService
import com.mindvalley.android.assignment.utils.NetworkUtil.Companion.getUnsafeOkHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Md Sayfullah Al Noman Ranak
 */

@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        converter: GsonConverterFactory,
        httpClient: OkHttpClient.Builder,
        @Named("baseURL") baseURL: String
    ): Retrofit {
        val retrofitClass =
                RetrofitServiceGenerator(
                        converter,
                        httpClient,
                        baseURL
                )
        return retrofitClass.getClient()
    }

    @Singleton
    @Provides
    fun provideOkHttp(interceptor: HttpLoggingInterceptor): OkHttpClient.Builder {

        val httpClient = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            OkHttpClient().newBuilder()
        } else {
            getUnsafeOkHttpClient()
        }

        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        httpClient.callTimeout(30, TimeUnit.SECONDS)
        httpClient.addInterceptor(interceptor)
        return httpClient
    }

    @Singleton
    @Provides
    fun provedHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("okhttp", message)
            }
        })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    @Named("baseURL")
    fun provideBaseURL(): String {
        return Cons.BASE_URL
    }

    @Singleton
    @Provides
    fun getMainInterface(retrofit: Retrofit): RestService {
        return retrofit.create(RestService::class.java)
    }


    @Singleton
    @Provides
    fun getDataBase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

}