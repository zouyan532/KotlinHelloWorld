package exmcollapsing.test.com.kotlinhelloworld


import exmcollapsing.test.com.kotlinhelloworld.exception.base.BaseException

class UnauthException(throwable: Throwable, var httpCode: Int) : BaseException(throwable)
