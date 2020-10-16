package cn.xpcheng.wanadnroidmvvm.repository

import cn.xpcheng.wanadnroidmvvm.data.bean.ArticleBody
import cn.xpcheng.wanadnroidmvvm.data.bean.BaseApiResponse
import cn.xpcheng.wanadnroidmvvm.data.http.WanAndroidApi

/**
 * @author ChengXinPing
 * @time   2020/10/15 9:34
 *
 */
class ProjectChildRepository(private val wanAndroidApi: WanAndroidApi) {
    suspend fun getProjectArticles(
        page: Int,
        cid: Int = 0,
        isNew: Boolean = false
    ): BaseApiResponse<ArticleBody> {
        return if (isNew) {
            wanAndroidApi.getRecentlyProjects(page)
        } else {
            wanAndroidApi.getProjectArticle(page, cid)
        }

    }
}