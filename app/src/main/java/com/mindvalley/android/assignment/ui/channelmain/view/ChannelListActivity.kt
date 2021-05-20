package com.mindvalley.android.assignment.ui.channelmain.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mindvalley.android.assignment.R
import com.mindvalley.android.assignment.bases.BaseActivity
import com.mindvalley.android.assignment.entities.ErrorIn
import com.mindvalley.android.assignment.entities.Loading
import com.mindvalley.android.assignment.entities.Success
import com.mindvalley.android.assignment.ui.newepisode.adapter.NewEpisodeAdapter


import com.mindvalley.android.assignment.ui.newepisode.viewmodel.ChannelRemoteViewModel
import com.mindvalley.android.assignment.utils.HorizontalListViewDecoration
import com.mindvalley.android.assignment.utils.ProjectUtils.Companion.showAlert

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_channel_list.*
import kotlinx.coroutines.launch
import androidx.recyclerview.widget.LinearLayoutManager as LinearLayoutManager


/**
 * Created by Md Sayfullah Al Noman Ranak
 */

@AndroidEntryPoint
class ChannelListActivity : BaseActivity(false), SwipeRefreshLayout.OnRefreshListener {

    private  val channelListViewModel: ChannelRemoteViewModel by viewModels()

    private lateinit var newEpisodeViewHolder: NewEpisodeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_channel_list)

        holderSL.setOnRefreshListener(this)

        initAdapter()

        observeViewModel()
    }

    private fun initAdapter() {

        newEpisodeViewHolder = NewEpisodeAdapter()

        newFeatureListLV.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)

        newFeatureListLV.addItemDecoration(HorizontalListViewDecoration())

        newFeatureListLV.adapter = newEpisodeViewHolder

        fetchNewEpisodeList()

    }

    private fun fetchNewEpisodeList() {
        lifecycleScope.launch {
            channelListViewModel.fetchNewEpisodeList()
        }
    }

    private fun observeViewModel(){
        channelListViewModel.newEpisodeList.observe(this, Observer {
            when(it){
                is Success ->{
                    val carListResponse = it.data as List<*>
                    newEpisodeViewHolder.updateList(carListResponse)
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
        holderSL.isRefreshing = false
        fetchNewEpisodeList()
    }

    override fun onBackPressed() {
        val layoutManager = newFeatureListLV
            .layoutManager as LinearLayoutManager
        if (layoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
            super.onBackPressed()
        } else {
            newFeatureListLV.smoothScrollToPosition(0)
        }
    }

    private fun loading(show: Boolean) {
//        progressBar.visibility = if(show) View.VISIBLE else View.INVISIBLE
    }
    
    

}