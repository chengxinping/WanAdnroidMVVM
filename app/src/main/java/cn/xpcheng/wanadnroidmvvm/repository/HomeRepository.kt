package cn.xpcheng.wanadnroidmvvm.repository

import cn.xpcheng.wanadnroidmvvm.data.bean.ArticleBody
import cn.xpcheng.wanadnroidmvvm.data.bean.Banner
import cn.xpcheng.wanadnroidmvvm.data.bean.BaseApiResponse
import cn.xpcheng.wanadnroidmvvm.data.http.WanAndroidApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

/**
 *@author chengxinping
 *@time 2020/8/18
 *@desc
 */
class HomeRepository(private val wanAndroidApi: WanAndroidApi) {
    suspend fun getHomeData(page: Int): BaseApiResponse<ArticleBody> = withContext(Dispatchers.IO) {
        val data = async { wanAndroidApi.getArticlesList(page) }
        //第一页时才拉置顶文章
        if (page == 0) {
            val topData = async { wanAndroidApi.getTopArticles() }
            data.await().data.datas.addAll(0, topData.await().data)
        }
        data.await()
    }

    suspend fun getBanner(): BaseApiResponse<List<Banner>> = wanAndroidApi.getBanner()

}