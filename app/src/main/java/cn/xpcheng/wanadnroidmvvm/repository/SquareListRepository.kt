package cn.xpcheng.wanadnroidmvvm.repository

import cn.xpcheng.wanadnroidmvvm.data.http.WanAndroidApi

/**
 * @author ChengXinPing
 * @time   2020/11/19 10:10
 *
 */
class SquareListRepository(private val wanAndroidApi: WanAndroidApi) {
    suspend fun getSquareList(page: Int) = wanAndroidApi.getSquareList(page)
}