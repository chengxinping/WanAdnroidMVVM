package cn.xpcheng.mvvm_core.base.viewmodel

import cn.xpcheng.mvvm_core.base.network.AppException

/**
 * @author chengxinping
 * @time 2020年08月20日09:49:06
 * @desc 密闭类 配合liveData 将结果抛给界面 界面来处理特殊的loading 成功 失败 等
 */
sealed class ResultState<out T> {

    companion object {
        fun <T> onAppSuccess(data: T): ResultState<T> = Success(data)
        fun <T> onAppLoading(): ResultState<T> = Loading
        fun <T> onAppError(error: AppException): ResultState<T> = Error(error)
    }
}

/**
 * loading状态
 */
object Loading : ResultState<Nothing>()

/**
 * 成功状态 dismiss 进度条
 */
data class Success<out T>(val data: T) : ResultState<T>()


/**
 * 错误状态  toast or SnackBar
 */
data class Error(val error: AppException) : ResultState<Nothing>()