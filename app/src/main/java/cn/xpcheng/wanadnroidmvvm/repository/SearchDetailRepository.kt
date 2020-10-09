package cn.xpcheng.wanadnroidmvvm.repository

import cn.xpcheng.wanadnroidmvvm.data.bean.ArticleBody
import cn.xpcheng.wanadnroidmvvm.data.bean.BaseApiResponse
import cn.xpcheng.wanadnroidmvvm.data.http.WanAndroidApi

/**
 * @author ChengXinPing
 * @time   2020/10/9 9:48
 *
 */
class SearchDetailRepository(private val wanAndroidApi: WanAndroidApi) {
    suspend fun search(key: String, page: Int): BaseApiResponse<ArticleBody> =
        wanAndroidApi.search(page, key)
}