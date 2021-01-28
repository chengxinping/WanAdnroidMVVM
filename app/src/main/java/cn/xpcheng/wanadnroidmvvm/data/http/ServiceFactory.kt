package cn.xpcheng.wanadnroidmvvm.data.http

import cn.xpcheng.wanadnroidmvvm.App
import cn.xpcheng.wanadnroidmvvm.constant.Constant
import cn.xpcheng.wanadnroidmvvm.data.http.cookie.cache.SetCookieCache
import cn.xpcheng.wanadnroidmvvm.data.http.cookie.persistence.PersistentCookieJar
import cn.xpcheng.wanadnroidmvvm.data.http.cookie.persistence.SharedPrefsCookiePersistor
import cn.xpcheng.wanadnroidmvvm.data.http.interceptor.CacheInterceptor
import cn.xpcheng.wanadnroidmvvm.data.http.interceptor.HeaderInterceptor
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
val cookieJar: PersistentCookieJar =
    PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(App.context))

val okHttpClient = OkHttpClient.Builder()
    .cookieJar(cookieJar)
    .addInterceptor(HeaderInterceptor())
    .addInterceptor(CacheInterceptor())
    .addInterceptor(httpLoggingInterceptor)
    .connectTimeout(15, TimeUnit.SECONDS)
    .readTimeout(15, TimeUnit.SECONDS)
    .writeTimeout(15, TimeUnit.SECONDS)
    .retryOnConnectionFailure(true)
    .cache(Cache(File(App.context.cacheDir, "cache"), 10 * 1024 * 1024))
    .build()


val moshi = Moshi.Builder()
    .add(NULL_TO_EMPTY_STRING_ADAPTER)

    .build()

//多个baseUrl处理 再创建新的retrofit
val retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .baseUrl(Constant.BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

object NULL_TO_EMPTY_STRING_ADAPTER {
    @FromJson
    fun fromJson(reader: JsonReader): String {
        if (reader.peek() != JsonReader.Token.NULL) {
            return reader.nextString()
        }
        reader.nextNull<Unit>()
        return ""
    }
}

//接口代理给koin注入
object WanAndroidApiService : WanAndroidApi by retrofit.create(WanAndroidApi::class.java)



