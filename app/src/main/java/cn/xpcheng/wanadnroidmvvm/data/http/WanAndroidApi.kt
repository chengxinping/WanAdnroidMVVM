package cn.xpcheng.wanadnroidmvvm.data.http

import cn.xpcheng.wanadnroidmvvm.data.bean.Article
import cn.xpcheng.wanadnroidmvvm.data.bean.ArticleBody
import cn.xpcheng.wanadnroidmvvm.data.bean.Banner
import cn.xpcheng.wanadnroidmvvm.data.bean.BaseApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *@author chengxinping
 *@time 2020/5/19
 *@desc
 */
interface WanAndroidApi {


    @GET("banner/json")
    suspend fun getBanner(): BaseApiResponse<ArrayList<Banner>>

    /**
     *置顶文章
     */
    @GET("article/top/json")
    suspend fun getTopArticles(): BaseApiResponse<List<Article>>

    /**
     * 首页文章列表
     * 页码，拼接在连接中，从0开始。
     *
     */
    @GET("article/list/{page}/json")
    suspend fun getArticlesList(@Path("page") page: Int): BaseApiResponse<ArticleBody>
}
