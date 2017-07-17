package exmcollapsing.test.com.kotlinhelloworld

import exmcollapsing.test.com.kotlinhelloworld.subscriber.RestAPIObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Boosal on 2017/7/13.
 */

class TestApi {
    protected var i = 0

    operator fun get(responseRestAPIObserver: RestAPIObserver<TestResponse>) {
        ServiceGenerator.createServiceFrom(TestService::class.java)
                .userGuides()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseRestAPIObserver)
    }

}
