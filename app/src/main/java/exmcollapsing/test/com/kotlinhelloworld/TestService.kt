package exmcollapsing.test.com.kotlinhelloworld

import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by Boosal on 2017/7/13.
 */

interface TestService {
    @GET(UrlStore.USERGUIDES)
    fun userGuides(): Observable<TestResponse>
}
