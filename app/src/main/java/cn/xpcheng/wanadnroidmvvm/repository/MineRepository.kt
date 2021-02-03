package cn.xpcheng.wanadnroidmvvm.repository

import cn.xpcheng.wanadnroidmvvm.data.http.WanAndroidApi

/**
 * @author ChengXinPing
 * @time   2021/1/28 9:02
 *
 */
class MineRepository(private val api: WanAndroidApi) {
    suspend fun logout() = api.logout()

    suspend fun getMyPoint() = api.getMyPoint()
}