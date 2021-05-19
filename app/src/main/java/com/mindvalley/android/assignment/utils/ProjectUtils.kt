package com.mindvalley.android.assignment.utils

import android.app.Activity
import com.mindvalley.android.assignment.R
import com.tapadoo.alerter.Alerter

class ProjectUtils {

    companion object{

        fun showAlert(
                mActivity: Activity?,
                title: String? = "",
                msg: String?,
                isError: Boolean = true
        ) {
            if (mActivity != null)
                Alerter.create(mActivity)
                        .setTitle(title ?: (if (isError) "Error" else ""))
                        .setText(msg ?: "")
                        .setDuration(3000)
                        .setBackgroundColorRes((if (isError) R.color.mine_shaft else R.color.redError))
                        .show()
        }




    }
}