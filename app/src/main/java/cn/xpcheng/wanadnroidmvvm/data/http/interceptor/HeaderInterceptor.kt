package cn.xpcheng.wanadnroidmvvm.data.http.interceptor

import android.os.Build
import cn.xpcheng.common.R
import cn.xpcheng.mvvm_core.Ktx
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author ChengXinPing
 * @time   2018/10/31 17:26
 *
 */
class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        builder.addHeader("Content-type", "application/json; charset=utf-8")
        builder.addHeader(
            "User-Agent",
            Ktx.app.getString(R.string.app_name) + "/" + " V" + Ktx.app.packageManager.getPackageInfo(
                Ktx.app.packageName,
                0
            ).versionName + "(" + Build.MODEL + ";" + Build.VERSION.RELEASE + ")"
        )

        //TODO: 部分接口需要将缓存的cookie添加到header  目前是全部加到header

        return chain.proceed(builder.build())
    }

}