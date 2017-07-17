package exmcollapsing.test.com.kotlinhelloworld

import android.content.Context

import com.kaopiz.kprogresshud.KProgressHUD

/**
 * Created by LeXunSW on 2017-02-22.
 */

object KprogresshudUtils {
    private var kProgressHUD: KProgressHUD? = null

    fun show(context: Context) {
        if (kProgressHUD != null) {
            kProgressHUD!!.dismiss()
            kProgressHUD = null
        }
        kProgressHUD = KprogresshudOptition.getKProgressHUD(context)
        kProgressHUD!!.show()
    }

    fun dismiss() {
        if (kProgressHUD != null) {
            kProgressHUD!!.dismiss()
            kProgressHUD = null
        }
    }
}
