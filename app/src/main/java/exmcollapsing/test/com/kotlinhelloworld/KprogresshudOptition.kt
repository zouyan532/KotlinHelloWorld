package exmcollapsing.test.com.kotlinhelloworld

import android.content.Context

import com.kaopiz.kprogresshud.KProgressHUD

/**
 * Created by Boosal on 2016/11/2.
 */
object KprogresshudOptition {
    fun getKProgressHUD(context: Context): KProgressHUD {
        val kProgressHUD = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setAnimationSpeed(2)
                //                .setDetailsLabel("loading")
                .setDimAmount(0.5f)
        return kProgressHUD
    }
}
