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
    suspend fun getArticlesList(@Path("page") page: Int): BaseApiResponse<PageInfo<Article>>

    /**
     * 搜索
     * 页码，拼接在连接中，从0开始。
     */
    @POST("article/query/{page}/json")
    @FormUrlEncoded
    suspend fun search(
        @Path("page") page: Int,
        @Field("k") key: String
    ): BaseApiResponse<PageInfo<Article>>

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
    ): BaseApiResponse<PageInfo<Article>>

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
    ): BaseApiResponse<PageInfo<Article>>

    /**
     * 最新项目
     * @param page 分页数据 从0开始
     */
    @GET("article/listproject/{page}/json")
    suspend fun getRecentlyProjects(@Path("page") page: Int): BaseApiResponse<PageInfo<Article>>

    /**
     * 问答列表
     *  @param page 每日一问的页码从1开始
     */
    @GET("wenda/list/{page}/json")
    suspend fun getQuestionList(@Path("page") page: Int): BaseApiResponse<PageInfo<Article>>

    /**
     * 广场列表
     * @param page 每日一问的页码从0开始
     */
    @GET("user_article/list/{page}/json")
    suspend fun getSquareList(@Path("page") page: Int): BaseApiResponse<PageInfo<Article>>

    /**
     * 导航数据
     */
    @GET("navi/json")
    suspend fun getNavigation(): BaseApiResponse<List<NavigationBean>>


    @GET("tree/json")
    suspend fun getTree(): BaseApiResponse<List<Tree>>

    @GET("article/list/{page}/json")
    suspend fun getTreeArticle(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): BaseApiResponse<PageInfo<Article>>


    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") pwd: String
    ): BaseApiResponse<UserInfo>

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("user/register")
    suspend fun register(
        @Field("username") username: String,
        @Field("password") pwd: String,
        @Field("repassword") rpwd: String
    ): BaseApiResponse<UserInfo>

    @GET("user/logout/json")
    suspend fun logout(): BaseApiResponse<String>

    @GET("lg/coin/userinfo/json")
    suspend fun getMyPoint(): BaseApiResponse<Point>

    @GET("coin/rank/{page}/json")
    suspend fun getRankList(@Path("page") page: Int): BaseApiResponse<PageInfo<Point>>

    /**
     * 个人积分详细列表
     */
    @GET("lg/coin/list/{page}/json")
    suspend fun getMyPointDetail(@Path("page") page: Int): BaseApiResponse<PageInfo<PointDetail>>

    /**
     * 收藏文章列表
     * @param page 从0开始
     */
    @GET("lg/collect/list/{page}/json")
    suspend fun getCollectArticle(@Path("page") page: Int): BaseApiResponse<PageInfo<Collect>>

    @GET("lg/collect/usertools/json")
    suspend fun getCollectUrl(): BaseApiResponse<List<WebSite>>

    /**
     * 自己的分享的文章列表
     * @param page 从1开始
     */
    @GET("user/lg/private_articles/{page}/json")
    suspend fun getMyArticle(@Path("page") page: Int): BaseApiResponse<ShareInfo>

    @FormUrlEncoded
    @POST("lg/user_article/add/json")
    suspend fun uploadArticle(
        @Field("title") title: String,
        @Field("link") content: String
    ): BaseApiResponse<String>
}
