package cn.xpcheng.wanadnroidmvvm.data.bean

import cn.xpcheng.mvvm_core.base.BaseResponse

/**
 *@author chengxinping
 *@time 2020/5/19
 *@desc gson数据实体类
 */

/**
 *轮播图
 */

data class BaseApiResponse<T>(var errorCode: Int, var errorMsg: String, var data: T) :
    BaseResponse<T>() {

    //错误码为0就是成功
    override fun isSuccess(): Boolean = errorCode == 0
    override fun getResponseData(): T = data

    override fun getResponseCode(): Int = errorCode

    override fun getResponseMsg(): String = errorMsg
}


data class Banner(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: String,
    val url: String
)

//文章列表
data class ArticleBody(
    val curPage: Int,
    val datas: MutableList<Article>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)

//文章详情
data class Article(
    val apkLink: String,
    val author: String,
    val audit: Int,
    val chapterId: Int,
    val chapterName: String,
    val collect: Boolean,
    val courseId: Int,
    val desc: String,
    val envelopePic: String,
    val fresh: Boolean,
    val id: Int,
    val link: String,
    val niceDate: String,
    val niceShareDate: String,
    val origin: String,
    val prefix: String,
    val projectLink: String,
    val publishTime: Long,
    val selfVisible: Int,
    val shareDate: Long,
    val shareUser: String,
    val superChapterId: Int,
    val superChapterName: String,
    val tags: List<Tag>,
    val title: String,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int,
    var isTop: Boolean = false
)

data class Tag(
    val name: String,
    val url: String
)

data class HotKey(
    val id: Int,
    val link: String,
    val name: String,
    val order: Int,
    val visible: Int
)

data class TreeBean(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Long,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)