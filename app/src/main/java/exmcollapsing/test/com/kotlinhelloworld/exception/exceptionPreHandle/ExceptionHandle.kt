package exmcollapsing.test.com.kotlinhelloworld.exception.exceptionPreHandle

import com.google.gson.Gson
import exmcollapsing.test.com.kotlinhelloworld.exception.ApiException
import exmcollapsing.test.com.kotlinhelloworld.exception.OtherException
import exmcollapsing.test.com.kotlinhelloworld.exception.ResponseError
import exmcollapsing.test.com.kotlinhelloworld.exception.UnauthException
import exmcollapsing.test.com.kotlinhelloworld.exception.base.BaseException
import retrofit2.HttpException
import java.io.IOException

object ExceptionHandle {

    fun handleException(e: Throwable): BaseException? {

        var baseException: BaseException? = null
        if (e is HttpException) {             //HTTP错误
            val httpException = e

            if (httpException.code() == 400) {
                try {
                    val errorStr = httpException.response().errorBody().string()
                    val error = Gson().fromJson(errorStr, ResponseError::class.java)
                    baseException = ApiException(e, httpException.code())
                    baseException.responseError = error
                } catch (e1: IOException) {
                    e1.printStackTrace()
                }

            } else if (httpException.code() == 401) {
                baseException = UnauthException(e, httpException.code())
            } else {
                baseException = ApiException(e, httpException.code())
            }
        } else {
            baseException = OtherException(e)
        }

        return baseException

    }
}
