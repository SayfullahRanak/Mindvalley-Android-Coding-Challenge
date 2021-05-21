package com.mindvalley.android.assignment.utils

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.LibraryGlideModule
import com.mindvalley.android.assignment.utils.NetworkUtil.Companion.getUnsafeOkHttpClient
import okhttp3.OkHttpClient
import java.io.InputStream


/**
 * Created by Md Sayfullah Al Noman Ranak on 21/05/2021.
 */
@GlideModule
class UnsafeOkHttpGlideModule : LibraryGlideModule(){

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val client: OkHttpClient = getUnsafeOkHttpClient().build()
        registry.replace(
            GlideUrl::class.java, InputStream::class.java,
            OkHttpUrlLoader.Factory(client)
        )
    }



}