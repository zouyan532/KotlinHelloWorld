package exmcollapsing.test.com.kotlinhelloworld.exception


import exmcollapsing.test.com.kotlinhelloworld.exception.base.BaseException

class ApiException(throwable: Throwable, var httpCode: Int) : BaseException(throwable) {

    var responseError: ResponseError? = null
}
