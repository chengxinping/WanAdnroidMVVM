package cn.xpcheng.wanadnroidmvvm.repository

import cn.xpcheng.wanadnroidmvvm.data.http.WanAndroidApi

/**
 * @author ChengXinPing
 * @time   2021/1/6 15:18
 *
 */
class TreeArticleRepository(private val wanAndroidApi: WanAndroidApi) {
    suspend fun getTreeArticle(page: Int, cid: Int) = wanAndroidApi.getTreeArticle(page, cid)
}