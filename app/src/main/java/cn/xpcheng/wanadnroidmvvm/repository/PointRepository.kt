package cn.xpcheng.wanadnroidmvvm.repository

import cn.xpcheng.wanadnroidmvvm.data.http.WanAndroidApi

/**
 * @author ChengXinPing
 * @time   2021/2/1 14:09
 *
 */
class PointRepository(private val api: WanAndroidApi) {
    suspend fun getRankList(page: Int) = api.getRankList(page)
}