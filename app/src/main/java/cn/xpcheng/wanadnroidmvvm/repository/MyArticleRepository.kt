package cn.xpcheng.wanadnroidmvvm.repository

import cn.xpcheng.wanadnroidmvvm.data.http.WanAndroidApi

/**
 * @author ChengXinPing
 * @time   2021/2/4 16:53
 *
 */
class MyArticleRepository(private val api: WanAndroidApi) {
    suspend fun getMyArticle(page: Int) = api.getMyArticle(page)
}