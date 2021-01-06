package cn.xpcheng.wanadnroidmvvm.repository

import cn.xpcheng.wanadnroidmvvm.data.http.WanAndroidApi

/**
 * @author ChengXinPing
 * @time   2020/11/19 15:33
 *
 */
class NavigationRepository(private val wanAndroidApi: WanAndroidApi) {

    suspend fun getNavigation() = wanAndroidApi.getNavigation()
}