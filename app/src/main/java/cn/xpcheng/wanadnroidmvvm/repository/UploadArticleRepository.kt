package cn.xpcheng.wanadnroidmvvm.repository

import cn.xpcheng.wanadnroidmvvm.data.http.WanAndroidApi

/**
 * @author ChengXinPing
 * @time   2021/2/20 16:27
 *
 */
class UploadArticleRepository(private val api: WanAndroidApi) {
    suspend fun uploadArticle(title: String, content: String) = api.uploadArticle(title, content)
}