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
    suspend fun getBanner(): BaseApiResponse<List<Banner>>

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
    suspend fun getHotKeys(): BaseApiResponse<List<HotKey>>

    /**
     * 获取微信公众号分类
     */
    @GET("wxarticle/chapters/json")
    suspend fun getWxTree(): BaseApiResponse<List<TreeBean>>

    /**
     * 查看某个公众号历史数据
     * 分页从1开始
     */
    @GET("wxarticle/list/{cid}/{page}/json")
    suspend fun getWxArticle(
        @Path("cid") cid: Int,
        @Path("page") page: Int
    ): BaseApiResponse<ArticleBody>

    /**
     *项目分类
     */
    @GET("project/tree/json")
    suspend fun getProjectTree(): BaseApiResponse<List<TreeBean>>

    /**
     * 获取某个项目分类下全部数据
     * @param page 分页数据  从1开始
     * @param cid 项目分类id
     */
    @GET("project/list/{page}/json")
    suspend fun getProjectArticle(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): BaseApiResponse<ArticleBody>

    /**
     * 最新项目
     * @param page 分页数据 从0开始
     */
    @GET("article/listproject/{page}/json")
    suspend fun getRecentlyProjects(@Path("page") page: Int): BaseApiResponse<ArticleBody>

}
