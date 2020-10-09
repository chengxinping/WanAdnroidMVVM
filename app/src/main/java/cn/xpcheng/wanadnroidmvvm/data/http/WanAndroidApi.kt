package cn.xpcheng.wanadnroidmvvm.data.http

import cn.xpcheng.wanadnroidmvvm.data.bean.*
import retrofit2.http.*

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

    /**
     * 搜索
     * 页码，拼接在连接中，从0开始。
     */
    @POST("article/query/{page}/json")
    @FormUrlEncoded
    suspend fun search(
        @Path("page") page: Int,
        @Field("k") key: String
    ): BaseApiResponse<ArticleBody>

    @GET("hotkey/json")
    suspend fun getHotKeys(): BaseApiResponse<ArrayList<HotKey>>
}
