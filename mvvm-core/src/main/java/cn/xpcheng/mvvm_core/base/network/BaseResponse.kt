package cn.xpcheng.mvvm_core.base.network

/**
 *@author chengxinping
 *@time 2020/5/19
 *@desc 网络请求基类  外部调用的时候自己的请求基类必须继承
 */
abstract class BaseResponse<T> {

    abstract fun isSuccess(): Boolean

    abstract fun getResponseData(): T

    abstract fun getResponseCode(): Int

    abstract fun getResponseMsg(): String
}