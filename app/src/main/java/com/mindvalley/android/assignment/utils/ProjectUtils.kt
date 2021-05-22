package com.mindvalley.android.assignment.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Insets
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.WindowInsets
import android.view.WindowManager
import com.mindvalley.android.assignment.R
import com.mindvalley.android.assignment.di.MyApplication
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


        fun getScreenWidth(context: Context): Int {
            return Resources.getSystem().getDisplayMetrics().widthPixels

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//
//                return Resources.getSystem().getDisplayMetrics().widthPixels;
//
//                val windowManger = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
//                val windowMetrics =
//                    windowManger.currentWindowMetrics
//
//                val insets: Insets = windowMetrics.windowInsets
//                    .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
////               return windowMetrics.bounds.width() - insets.left - insets.right
//                return Resources.getSystem().getDisplayMetrics().widthPixels;
//            }else{
//
//            }
        }

        fun dpTopx(dip : Float, context: Context) : Float{
            val r: Resources = context.resources
            val px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.displayMetrics
            )
            return px
        }

        fun getItemWidth(context: Context): Int {

            val division = getScreenWidth(context)/ 12
            return division * 6
        }

        fun getSpcing(context: Context) : Int{
            val division = getScreenWidth(context)/ 12
            return division
        }



    }
}