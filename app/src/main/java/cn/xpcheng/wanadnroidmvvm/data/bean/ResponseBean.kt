package cn.xpcheng.wanadnroidmvvm.data.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import cn.xpcheng.mvvm_core.base.network.BaseResponse
import kotlinx.android.parcel.Parcelize

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
data class PageInfo<T>(
    val curPage: Int,
    val datas: MutableList<T>,
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
    val shareDate: Long?,
    val shareUser: String,
    val superChapterId: Int,
    val superChapterName: String,
    val tags: List<Tag>,
    val title: String,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int
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

@SuppressLint("ParcelCreator")
@Parcelize
data class TreeBean(
    val children: List<String> = listOf(),
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Long,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
) : Parcelable

//导航数据
data class NavigationBean(
    val articles: List<Article>,
    val cid: Int,
    val name: String
)

@SuppressLint("ParcelCreator")
@Parcelize
data class Tree(
    val children: List<TreeBean>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Long,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class UserInfo(
    val admin: Boolean = false,
    val chapterTops: List<String> = listOf(),
    val collectIds: MutableList<String> = mutableListOf(),
    val email: String = "",
    val icon: String = "",
    val coinCount: Int = 0,
    val id: String = "",
    val nickname: String = "",
    val password: String = "",
    val token: String = "",
    val type: Int = 0,
    val username: String = ""
) : Parcelable

data class Point(
    val coinCount: Long,
    val level: Long,
    val nickname: String,
    val rank: String,
    val userId: Long,
    val username: String
)

data class PointDetail(
    val coinCount: Long,
    val date: Long,
    val desc: String,
    val id: Long,
    val reason: String,
    val type: Int,
    val userId: Long,
    val userName: String
)

data class WebSite(
    val desc: String,
    val icon: String,
    val id: Long,
    val link: String,
    val name: String,
    val order: Int,
    val userId: Long,
    val visible: Int
)

data class Collect(
    val chapterId: Int,
    val author: String,
    val chapterName: String,
    val courseId: Int,
    val desc: String,
    val envelopePic: String,
    val id: Int,
    val link: String,
    val niceDate: String,
    val origin: String,
    val originId: Int,
    val publishTime: Long,
    val title: String,
    val userId: Int,
    val visible: Int,
    val zan: Int
)