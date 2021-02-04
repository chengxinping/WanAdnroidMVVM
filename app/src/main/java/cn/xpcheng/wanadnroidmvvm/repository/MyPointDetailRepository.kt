package cn.xpcheng.wanadnroidmvvm.repository

import cn.xpcheng.wanadnroidmvvm.data.http.WanAndroidApi

/**
 * @author ChengXinPing
 * @time   2021/2/3 17:04
 *
 */
class MyPointDetailRepository(private val api: WanAndroidApi) {
    suspend fun getMyPointDetail(page: Int) = api.getMyPointDetail(page)
}