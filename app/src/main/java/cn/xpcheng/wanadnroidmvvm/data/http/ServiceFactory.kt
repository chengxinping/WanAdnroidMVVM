package cn.xpcheng.wanadnroidmvvm.data.http

import android.util.Log
import cn.xpcheng.wanadnroidmvvm.App
import cn.xpcheng.wanadnroidmvvm.data.http.interceptor.CacheInterceptor
import cn.xpcheng.wanadnroidmvvm.data.http.interceptor.HeaderInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 *@author chengxinping
 *@time 2020/5/19
 *@desc
 */

val httpLoggingInterceptor = HttpLoggingInterceptor().also {
    it.level = HttpLoggingInterceptor.Level.BODY
}

val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(HeaderInterceptor())
    .addInterceptor(CacheInterceptor())
    .addInterceptor(httpLoggingInterceptor)
    .connectTimeout(15, TimeUnit.SECONDS)
    .readTimeout(15, TimeUnit.SECONDS)
    .writeTimeout(15, TimeUnit.SECONDS)
    .retryOnConnectionFailure(true)
    .cache(Cache(File(App.context.cacheDir, "cache"), 10 * 1024 * 1024))
    .build()


//多个baseUrl处理 再创建新的retrofit
val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .baseUrl("http://www.wanandroid.com/")
    .build()

//接口代理给koin注入
object WanAndroidApiService : WanAndroidApi by retrofit.create(WanAndroidApi::class.java)



