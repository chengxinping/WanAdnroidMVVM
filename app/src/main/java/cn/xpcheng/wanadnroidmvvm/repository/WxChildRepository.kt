package cn.xpcheng.wanadnroidmvvm.repository

import cn.xpcheng.wanadnroidmvvm.data.http.WanAndroidApi

/**
 * @author ChengXinPing
 * @time   2020/10/14 15:28
 *
 */
class WxChildRepository(private val wanAndroidApi: WanAndroidApi) {

    suspend fun getWxArticle(cid: Int, page: Int) = wanAndroidApi.getWxArticle(cid, page)

}