package cn.xpcheng.wanadnroidmvvm.repository

import cn.xpcheng.wanadnroidmvvm.data.http.WanAndroidApi

/**
 * @author ChengXinPing
 * @time   2020/10/14 15:28
 *
 */
class WxRepository(private val wanAndroidApi: WanAndroidApi) {
    suspend fun getWxtTree() = wanAndroidApi.getWxTree()
}