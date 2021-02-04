package cn.xpcheng.wanadnroidmvvm.repository

import cn.xpcheng.wanadnroidmvvm.data.http.WanAndroidApi

/**
 * @author ChengXinPing
 * @time   2021/2/4 15:14
 *
 */
class CollectArticleRepository(private val api: WanAndroidApi) {
    suspend fun getCollectArticle(page: Int) = api.getCollectArticle(page)
}