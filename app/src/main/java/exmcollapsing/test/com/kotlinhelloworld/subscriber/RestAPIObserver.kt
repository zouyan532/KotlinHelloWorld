package exmcollapsing.test.com.kotlinhelloworld.subscriber

import android.content.Context
import exmcollapsing.test.com.kotlinhelloworld.KprogresshudUtils
import exmcollapsing.test.com.kotlinhelloworld.NetWorkUtils
import exmcollapsing.test.com.kotlinhelloworld.UnauthException
import exmcollapsing.test.com.kotlinhelloworld.exception.ApiException
import exmcollapsing.test.com.kotlinhelloworld.exception.OtherException
import exmcollapsing.test.com.kotlinhelloworld.exception.exceptionPreHandle.ExceptionHandle
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


abstract class RestAPIObserver<T> @JvmOverloads constructor(private val context: Context, private val isShowProgress: Boolean = false) : Observer<T> {

    override fun onSubscribe(d: Disposable) {
        val d1 = d
        if (isShowProgress) {
            KprogresshudUtils.show(context)
        }
        if (!NetWorkUtils.isNetConnected(context)) {
            d.dispose()
            KprogresshudUtils.dismiss()
        }
    }


    override fun onNext(t: T) {
        KprogresshudUtils.dismiss()
        _onSuccess(t)
    }

    override fun onComplete() {

    }

    override fun onError(e: Throwable) {
        KprogresshudUtils.dismiss()
        val baseException = ExceptionHandle.handleException(e)
        if (baseException is ApiException) {
            when (baseException.httpCode) {
                400 -> _onApiError(baseException)
                401 -> {
                }
            }
        } else if (baseException is UnauthException) {
            _onUnAuth(baseException)
        } else if (e is OtherException) {
            _onOtherException(baseException as OtherException)
        }
    }


    fun _onSuccess(t: T) {
        onSuccess(t)
    }

    fun _onApiError(e: ApiException) {
        onApiError(e)
    }

    fun _onUnAuth(e: UnauthException) {
        onUnAuth(e)
    }

    fun _onOtherException(e: OtherException) {
        onOtherError(e)
    }

    fun onUnAuth(e: UnauthException) {}

    protected fun unlogin() {

    }

    open abstract fun onSuccess(t: T)

    open abstract fun onApiError(e: ApiException)

    protected fun onOtherError(e: OtherException) {}
}
