package exmcollapsing.test.com.kotlinhelloworld.exception

import java.io.Serializable

/**
 * Created by LeXunSW on 2017/2/6.
 */

class ResponseError : Serializable {
    var error_code: Int = 0
    var error_message: String? = null
}
