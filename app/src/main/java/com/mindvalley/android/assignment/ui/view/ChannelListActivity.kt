package com.sevenpeakssoftware.sayfullah.ui.carlist.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mindvalley.android.assignment.bases.BaseActivity
import com.sevenpeakssoftware.sayfullah.ui.carlist.adapter.CarListAdapter
import com.sayfullah.assignment.R
import com.sevenpeakssoftware.sayfullah.data.ErrorIn
import com.sevenpeakssoftware.sayfullah.data.Loading
import com.sevenpeakssoftware.sayfullah.data.Success
import com.sevenpeakssoftware.sayfullah.ui.carlist.viewmodel.ChannelRemoteViewModel
import com.sevenpeakssoftware.sayfullah.utils.ProjectUtils.Companion.showAlert
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_carlist.*
import kotlinx.coroutines.launch
import androidx.recyclerview.widget.LinearLayoutManager as LinearLayoutManager


/**
 * Created by Md Sayfullah Al Noman Ranak
 */

@AndroidEntryPoint
class ChannelListActivity : BaseActivity(false), SwipeRefreshLayout.OnRefreshListener {

    private  val channelListViewModel: ChannelRemoteViewModel by viewModels()

    private lateinit var carListAdapter: CarListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_carlist)

        swipelayout.setOnRefreshListener(this)

        initAdapter()

        observeViewModel()

    }




    private fun initAdapter() {

        carListAdapter =
            CarListAdapter()

        carListrv.layoutManager = LinearLayoutManager(this) 

        carListrv.adapter = carListAdapter

        fetchCarList()

    }



    private fun fetchCarList() {
        lifecycleScope.launch {
            channelListViewModel.fetchCarList()
        }
    }

    private fun observeViewModel(){
        channelListViewModel.carListlv.observe(this, Observer {
            when(it){
                is Success ->{
                    val carListResponse = it.data as List<*>
                    carListAdapter.updateList(carListResponse)
                    loading(false)
                }
                is ErrorIn ->{
                    showAlert(this,"Error in network",it.message,true)
                }
                is Loading ->{
                    loading(true)
                }
            }
        })

        channelListViewModel.networkStatus.observe(this, Observer {
            noInternet.visibility = if(it) View.GONE else View.VISIBLE
        })
    }

    override fun onRefresh() {
        swipelayout.isRefreshing = false
        fetchCarList()
    }

    override fun onBackPressed() {
        val layoutManager = carListrv
            .layoutManager as LinearLayoutManager
        if (layoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
            super.onBackPressed()
        } else {
            carListrv.smoothScrollToPosition(0)
        }
    }

    private fun loading(show: Boolean) {
        progressBar.visibility = if(show) View.VISIBLE else View.INVISIBLE
    }
    
    

}