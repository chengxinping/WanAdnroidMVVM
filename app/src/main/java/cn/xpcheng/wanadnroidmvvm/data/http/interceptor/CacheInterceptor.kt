package cn.xpcheng.wanadnroidmvvm.data.http.interceptor

import cn.xpcheng.common.utils.NetworkUtils
import cn.xpcheng.mvvm_core.Ktx
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author ChengXinPing
 * @time   2018/10/31 18:00
 *
 */
class CacheInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (!NetworkUtils.isNetworkAvailable(Ktx.app)) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build()
        }
        val response = chain.proceed(request)
        if (NetworkUtils.isNetworkAvailable(Ktx.app)) {
            // 有网络时 设置缓存超时时间0个小时
            val maxAge = 0
            response.newBuilder()
                    .header("Cache-Control", "public, max-age=$maxAge")
                    .build()
        } else {
            // 无网络时，设置超时为1天  只对get有用,post没有缓冲
            val maxStale = 60 * 60 * 24
            response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .build()
        }
        return response
    }
}