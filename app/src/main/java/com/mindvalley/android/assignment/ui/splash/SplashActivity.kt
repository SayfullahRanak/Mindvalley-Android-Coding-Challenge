package com.mindvalley.android.assignment.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build

import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.mindvalley.android.assignment.BuildConfig
import com.mindvalley.android.assignment.R
import com.mindvalley.android.assignment.bases.BaseActivity
import com.mindvalley.android.assignment.ui.channelmain.view.ChannelListActivity

import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity(false) {



    private var index = 0

    private var activityIsActive = false

    @SuppressLint("SetTextI18n")
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
            window.setDecorFitsSystemWindows(false)
        }else{
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        supportActionBar?.hide()

        uatProduction.text = BuildConfig.FLAVOR.substring(0, 1).toUpperCase()
    }

    override fun onResume() {
        super.onResume()

        startActivity(
            Intent(
                mActivity,
                ChannelListActivity::class.java
            ).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        )
        onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        activityIsActive = false
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}
