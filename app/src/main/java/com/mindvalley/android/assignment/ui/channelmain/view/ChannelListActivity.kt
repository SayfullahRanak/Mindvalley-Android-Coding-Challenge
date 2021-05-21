package com.mindvalley.android.assignment.ui.channelmain.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mindvalley.android.assignment.R
import com.mindvalley.android.assignment.bases.BaseActivity
import com.mindvalley.android.assignment.entities.ErrorIn
import com.mindvalley.android.assignment.entities.Loading
import com.mindvalley.android.assignment.entities.Success
import com.mindvalley.android.assignment.ui.channelmain.category.adapter.CategoryAdapter
import com.mindvalley.android.assignment.ui.channelmain.channel.adapter.ChannelAdapter
import com.mindvalley.android.assignment.ui.newepisode.adapter.NewEpisodeAdapter


import com.mindvalley.android.assignment.ui.newepisode.viewmodel.ChannelRemoteViewModel
import com.mindvalley.android.assignment.utils.GridListViewDecoration
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

    private lateinit var newEpisodeAdapter: NewEpisodeAdapter

    private lateinit var channelAdapter: ChannelAdapter

    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_channel_list)

        holderSL.setOnRefreshListener(this)

        initAdapter()

        observeViewModel()
    }

    private fun initAdapter() {

        newEpisodeAdapter = NewEpisodeAdapter()

        newFeatureListLV.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)

        newFeatureListLV.addItemDecoration(HorizontalListViewDecoration())

        newFeatureListLV.adapter = newEpisodeAdapter

        fetchNewEpisodeList()



        channelAdapter = ChannelAdapter()

        channelListLv.layoutManager = LinearLayoutManager(this)

        channelListLv.addItemDecoration(HorizontalListViewDecoration())

        channelListLv.adapter = channelAdapter

        fetchChannelList()



        categoryAdapter = CategoryAdapter()

        categoryListLV.layoutManager = GridLayoutManager(this, 2) as RecyclerView.LayoutManager?

//        categoryListLV.addItemDecoration(GridListViewDecoration())

        categoryListLV.adapter = categoryAdapter

        fetchCategoryList()


    }

    private fun fetchNewEpisodeList() {
        lifecycleScope.launch {
            channelListViewModel.fetchNewEpisodeList()
        }
    }

    private fun fetchChannelList() {
        lifecycleScope.launch {
            channelListViewModel.fetchChannelList()
        }
    }

    private fun fetchCategoryList() {
        lifecycleScope.launch {
            channelListViewModel.fetchCategoryList()
        }
    }

    private fun observeViewModel(){
        channelListViewModel.newEpisodeList.observe(this, Observer {
            when(it){
                is Success ->{
                    val carListResponse = it.data as List<*>
                    newEpisodeAdapter.updateList(carListResponse)
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

        channelListViewModel.channelList.observe(this, Observer {
            when(it){
                is Success ->{
                    val channelListResponse = it.data as List<*>
                    channelAdapter.updateList(channelListResponse)
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

        channelListViewModel.categoryList.observe(this, Observer {
            when(it){
                is Success ->{
                    val categoryListResponse = it.data as List<*>
                    categoryAdapter.updateList(categoryListResponse)
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