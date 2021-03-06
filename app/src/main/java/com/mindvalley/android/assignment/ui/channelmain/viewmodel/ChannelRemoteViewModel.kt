package com.mindvalley.android.assignment.ui.newepisode.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mindvalley.android.assignment.db.AppDatabase
import com.mindvalley.android.assignment.di.MyApplication
import com.mindvalley.android.assignment.entities.ErrorIn
import com.mindvalley.android.assignment.entities.Loading
import com.mindvalley.android.assignment.entities.Response
import com.mindvalley.android.assignment.entities.Success
import com.mindvalley.android.assignment.repo.MainRepo
import com.mindvalley.android.assignment.utils.NetworkUtil.Companion.TYPE_NOT_CONNECTED
import com.mindvalley.android.assignment.utils.NetworkUtil.Companion.getConnectivityStatus
import retrofit2.HttpException
import java.io.IOException


/**
 * Created by Md Sayfullah Al Noman Ranak
 */

class ChannelRemoteViewModel @ViewModelInject constructor(private val mainRepo: MainRepo, private val appDatabase: AppDatabase) : ViewModel() {


    val newEpisodeList = MutableLiveData<Response>()
    val channelList = MutableLiveData<Response>()
    val categoryList = MutableLiveData<Response>()
    val networkStatus = MutableLiveData<Boolean>()

    suspend fun fetchNewEpisodeList(){
        var netWorkStatus = true
        if (appDatabase.getNewEpisodeDao().getAllNewEpisodeModel().isEmpty()) newEpisodeList.postValue(Loading("Loading car List..."))
        else newEpisodeList.postValue(Success(appDatabase.getNewEpisodeDao().getAllNewEpisodeModel()))

        try {
            val apiResponse = mainRepo.getNewEpisode()
            if(apiResponse.code() == 200){
                appDatabase.getNewEpisodeDao().clearAllNewEpisode()
                /*Keep in mind the JSON provided might have some
                data missing in some instances, so these cases
                should be handled gracefully to provide a good UX

                        ...... So check null safety*/
                newEpisodeList.postValue(Success(apiResponse.body()?.data?.media))
                appDatabase.getNewEpisodeDao().insertAll(apiResponse.body()?.data?.media!!)
            }else{
                newEpisodeList.postValue(ErrorIn(0, apiResponse.message()))
            }
        }catch (exception: IOException){
            newEpisodeList.postValue(ErrorIn(1, exception.message))
            netWorkStatus = getConnectivityStatus(MyApplication.appInstance) != TYPE_NOT_CONNECTED
        }catch (exception: HttpException){
            newEpisodeList.postValue(ErrorIn(2, exception.message))
            netWorkStatus = getConnectivityStatus(MyApplication.appInstance) != TYPE_NOT_CONNECTED
        }
        networkStatus.postValue(netWorkStatus)
    }

    suspend fun fetchChannelList(){
        var netWorkStatus = true
        if (appDatabase.getChannelDao().getAllChannel().isEmpty()) newEpisodeList.postValue(Loading("Loading channel List..."))
        else channelList.postValue(Success(appDatabase.getChannelDao().getAllChannel()))

        try {
            val apiResponse = mainRepo.getChannels()
            if(apiResponse.code() == 200){
                appDatabase.getChannelDao().clearAllChannel()
                /*Keep in mind the JSON provided might have some
                data missing in some instances, so these cases
                should be handled gracefully to provide a good UX

                        ...... So check null safety*/
                channelList.postValue(Success(apiResponse.body()?.data?.channels))
                appDatabase.getChannelDao().insertAll(apiResponse.body()?.data?.channels!!)
            }else{
                channelList.postValue(ErrorIn(0, apiResponse.message()))
            }
        }catch (exception: IOException){
            channelList.postValue(ErrorIn(1, exception.message))
            netWorkStatus = getConnectivityStatus(MyApplication.appInstance) != TYPE_NOT_CONNECTED
        }catch (exception: HttpException){
            channelList.postValue(ErrorIn(2, exception.message))
            netWorkStatus = getConnectivityStatus(MyApplication.appInstance) != TYPE_NOT_CONNECTED
        }
        networkStatus.postValue(netWorkStatus)
    }

    suspend fun fetchCategoryList(){
        var netWorkStatus = true
        if (appDatabase.getCategoryDao().getAllCategory().isEmpty()) categoryList.postValue(Loading("Loading category List..."))
        else categoryList.postValue(Success(appDatabase.getCategoryDao().getAllCategory()))

        try {
            val apiResponse = mainRepo.getCategories()
            if(apiResponse.code() == 200){
                appDatabase.getCategoryDao().clearAllCategories()
                /*Keep in mind the JSON provided might have some
                data missing in some instances, so these cases
                should be handled gracefully to provide a good UX

                        ...... So check null safety*/
                categoryList.postValue(Success(apiResponse.body()?.data?.categories))
                appDatabase.getCategoryDao().insertAll(apiResponse.body()?.data?.categories!!)
            }else{
                categoryList.postValue(ErrorIn(0, apiResponse.message()))
            }
        }catch (exception: IOException){
            categoryList.postValue(ErrorIn(1, exception.message))
            netWorkStatus = getConnectivityStatus(MyApplication.appInstance) != TYPE_NOT_CONNECTED
        }catch (exception: HttpException){
            channelList.postValue(ErrorIn(2, exception.message))
            netWorkStatus = getConnectivityStatus(MyApplication.appInstance) != TYPE_NOT_CONNECTED
        }
        networkStatus.postValue(netWorkStatus)
    }


}