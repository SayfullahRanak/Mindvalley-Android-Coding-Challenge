package com.sevenpeakssoftware.sayfullah.ui.carlist.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mindvalley.android.assignment.db.AppDatabase
import com.mindvalley.android.assignment.repo.MainRepo
import com.sevenpeakssoftware.sayfullah.repo.MainRepo
import com.sevenpeakssoftware.sayfullah.data.*
import com.sevenpeakssoftware.sayfullah.db.AppDatabase
import com.sevenpeakssoftware.sayfullah.utils.MyApplication
import com.sevenpeakssoftware.sayfullah.utils.NetworkUtil.Companion.TYPE_NOT_CONNECTED
import com.sevenpeakssoftware.sayfullah.utils.NetworkUtil.Companion.getConnectivityStatus
import okhttp3.Response
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by Md Sayfullah Al Noman Ranak
 */

class ChannelRemoteViewModel @ViewModelInject constructor(private val mainRepo: MainRepo, private val appDatabase: AppDatabase) : ViewModel() {


    val carListlv = MutableLiveData<Response>()
    val networkStatus = MutableLiveData<Boolean>()

    suspend fun fetchCarList(){
        var netWorkStatus = true

        if (appDatabase.getCarModelDao().getAllCarModel().isEmpty()) carListlv.postValue(Loading("Loading car List..."))
        else carListlv.postValue(Success(appDatabase.getCarModelDao().getAllCarModel()))

        try {
            val apiResponse = mainRepo.getCars()
            if(apiResponse.code() == 200){
                appDatabase.getCarModelDao().clearAllCars()
                carListlv.postValue(Success(apiResponse.body()?.content))
                appDatabase.getCarModelDao().insertAll(apiResponse.body()?.content!!)
            }else{
                carListlv.postValue(ErrorIn(0, apiResponse.message()))
            }
        }catch (exception: IOException){
            carListlv.postValue(ErrorIn(1, exception.message))
            netWorkStatus = getConnectivityStatus(MyApplication.appInstance) != TYPE_NOT_CONNECTED
        }catch (exception: HttpException){
            carListlv.postValue(ErrorIn(2, exception.message))
            netWorkStatus = getConnectivityStatus(MyApplication.appInstance) != TYPE_NOT_CONNECTED
        }
        networkStatus.postValue(netWorkStatus)
    }


}