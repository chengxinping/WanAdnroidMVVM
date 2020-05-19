package cn.xpcheng.wanadnroidmvvm.data.http

import cn.xpcheng.wanadnroidmvvm.data.bean.Banner
import cn.xpcheng.wanadnroidmvvm.data.bean.BaseApiResponse
import retrofit2.http.GET

/**
 *@author chengxinping
 *@time 2020/5/19
 *@desc
 */
interface WanAndroidApi {


    @GET("banner/json")
    suspend fun getBanner(): BaseApiResponse<ArrayList<Banner>>
}
