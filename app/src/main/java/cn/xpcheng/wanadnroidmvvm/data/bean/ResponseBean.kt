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
