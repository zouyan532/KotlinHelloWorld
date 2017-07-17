package exmcollapsing.test.com.kotlinhelloworld

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by LeXunSW on 2017-02-21.
 */

object ServiceGenerator {

    private val builder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())



    fun <T> createServiceFrom(serviceClass: Class<T>): T {
        var client = initOKhttClient()
        builder.client(client)
        return createServiceFrom(serviceClass, UrlStore.BASEURL)
    }

    fun <T> createServiceFrom(serviceClass: Class<T>, baseUrl: String): T {
        builder.baseUrl(baseUrl)
        return builder.build().create(serviceClass)
    }

    private fun initOKhttClient(): OkHttpClient? {
        val sslParams = HttpsUtils.getSslSocketFactory(null, null, null)
        val httpClient = OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .addInterceptor { chain ->
                    val request = chain.request()
                            .newBuilder()
                            .addHeader("Content-Type", "application/json;charset=utf-8")
                            .addHeader("Accept", "application/json")
                            //                                    .addHeader("Authorization", EbirdApplication.getToken())
                            .build()
                    chain.proceed(request)
                }
//                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .hostnameVerifier { hostname, session -> true }
                .build()
        return httpClient

    }
}
