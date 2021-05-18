package com.mindvalley.android.assignment.repo


import com.mindvalley.android.assignment.entities.Categories
import com.mindvalley.android.assignment.entities.Category
import com.mindvalley.android.assignment.entities.Channels
import com.mindvalley.android.assignment.entities.NewEpisodes
import com.sevenpeakssoftware.sayfullah.rest.RestService
import retrofit2.Response
import java.util.*
import javax.inject.Inject

class MainRepo @Inject
constructor(private val repoService: RestService) {


    suspend fun getCategories(): Response<Categories> {

        return repoService.getCategories()
    }

    suspend fun getNewEpisode(): Response<NewEpisodes> {

        return repoService.getNewEpisodes()
    }

    suspend fun getChannel(): Response<Channels> {

        return repoService.getChannels()
    }
}