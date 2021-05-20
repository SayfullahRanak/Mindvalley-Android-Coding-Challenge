package com.mindvalley.android.assignment.repo


import com.mindvalley.android.assignment.entities.Categories
import com.mindvalley.android.assignment.entities.Channels
import com.mindvalley.android.assignment.entities.NewEpisodes
import com.mindvalley.android.assignment.rest.RestService
import retrofit2.Response
import javax.inject.Inject

class MainRepo @Inject
constructor(private val repoService: RestService) {


    suspend fun getCategories(): Response<Categories> {

        return repoService.getCategories()
    }

    suspend fun getNewEpisode(): Response<NewEpisodes> {

        return repoService.getNewEpisodes()
    }

    suspend fun getChannels(): Response<Channels> {

        return repoService.getChannels()
    }
}