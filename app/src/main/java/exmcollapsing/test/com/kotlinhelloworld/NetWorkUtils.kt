package exmcollapsing.test.com.kotlinhelloworld

/**
 * Created by Boosal on 2016/6/30.
 */

import android.app.ActivityManager
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetWorkUtils(private val mContext: Context) {
    var wifiState: NetworkInfo.State? = null
    var mobileState: NetworkInfo.State? = null

    enum class NetWorkState {
        WIFI, MOBILE, NONE
    }

    /**
     * 获取当前的网络状态
     * @return
     */
    val connectState: NetWorkState
        get() {
            val manager = mContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            manager.activeNetworkInfo
            wifiState = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                    .state
            mobileState = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                    .state
            if (wifiState != null && mobileState != null
                    && NetworkInfo.State.CONNECTED != wifiState
                    && NetworkInfo.State.CONNECTED == mobileState) {
                return NetWorkState.MOBILE
            } else if (wifiState != null && mobileState != null
                    && NetworkInfo.State.CONNECTED != wifiState
                    && NetworkInfo.State.CONNECTED != mobileState) {
                return NetWorkState.NONE
            } else if (wifiState != null && NetworkInfo.State.CONNECTED == wifiState) {
                return NetWorkState.WIFI
            }
            return NetWorkState.NONE
        }

    companion object {
        private var isNetConnected: Boolean = false
        /**
         * 是否是wifi连接
         */
        fun getConnectWifi(context: Context): Boolean {
            val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetInfo = manager.activeNetworkInfo//获取网络的连接情况
            if (activeNetInfo.type == ConnectivityManager.TYPE_WIFI) {
                return true
            } else {
                return false
            }
        }

        /**
         * 检查网络设置
         * @param context
         * *
         * @return
         */
        fun isNetConnected(context: Context): Boolean {
            val connManager = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = connManager.activeNetworkInfo
            if (info != null && info.isAvailable) {
                isNetConnected = true
            } else {
                isNetConnected = false
            }
            return isNetConnected
        }


        fun isServiceWork(mContext: Context): Boolean {
            var isWork = false
            val serviceName = "com.lcit.lecai.service.CityService"
            val myAM = mContext
                    .getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val myList = myAM.getRunningServices(40)
            if (myList.size <= 0) {
                return false
            }
            for (i in myList.indices) {
                val mName = myList[i].service.className.toString()
                if (mName == serviceName) {
                    isWork = true
                    break
                }
            }
            return isWork
        }
    }
}
