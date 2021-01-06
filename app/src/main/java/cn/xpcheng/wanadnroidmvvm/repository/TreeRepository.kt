package cn.xpcheng.wanadnroidmvvm.repository

import cn.xpcheng.wanadnroidmvvm.data.http.WanAndroidApi

/**
 * @author ChengXinPing
 * @time   2021/1/4 16:15
 *
 */
class TreeRepository(private val wanAndroidApi: WanAndroidApi) {
    suspend fun getTree() = wanAndroidApi.getTree()
}