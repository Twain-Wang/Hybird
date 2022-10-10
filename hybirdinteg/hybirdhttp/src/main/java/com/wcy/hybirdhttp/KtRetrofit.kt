package com.wcy.hybirdhttp

import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object KtRetrofit{
    private val mOkClient:OkHttpClient = OkHttpClient.Builder()
        .callTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .followRedirects(false)
        .cache(Cache(File("sdcard/cache","okhttp"),1024))
        .cookieJar(LocalCookieJar())
        .addNetworkInterceptor(KtHttpLogInterceptor{
            logLevel(KtHttpLogInterceptor.LogLevel.BODY)
        })
        .build()
    private val retrofitBuilder:Retrofit.Builder= Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(LiveDataCallAdapterFactory())
        .client(mOkClient)

    private var retrofit:Retrofit? = null


    fun initConfig(baseUrl:String,okHttpClient: OkHttpClient = mOkClient):KtRetrofit{
        retrofit = retrofitBuilder.baseUrl(baseUrl).client(okHttpClient).build()
        return this
    }

    fun <T> getService(serviceClass: Class<T>):T{
        if (retrofit == null){
            throw UninitializedPropertyAccessException("Retrofit必须初始化，需要配置baseUrl")
        }else{
            return this.retrofit!!.create(serviceClass)
        }
    }
}