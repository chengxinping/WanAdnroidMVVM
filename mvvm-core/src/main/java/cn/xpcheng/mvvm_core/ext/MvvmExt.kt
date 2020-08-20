package cn.xpcheng.mvvm_core.ext

import cn.xpcheng.mvvm_core.base.activity.BaseVmActivity
import cn.xpcheng.mvvm_core.base.fragment.BaseVmFragment
import cn.xpcheng.mvvm_core.base.viewmodel.Error
import cn.xpcheng.mvvm_core.base.viewmodel.Loading
import cn.xpcheng.mvvm_core.base.viewmodel.ResultState
import cn.xpcheng.mvvm_core.base.viewmodel.Success

/**
 *@author chengxinping
 *@time 2020/8/20
 *@desc
 */

/**
 * 处理网络请求带状态的返回数据，成功回调在第一个，其后两个带默认值的回调可省
 * @param resultStatusData 接口返回值
 * @param onLoading 加载中
 * @param onSuccess 成功回调
 * @param onError 失败回调
 */
fun <T> BaseVmActivity<*>.parseStatusData(
    resultStatusData: ResultState<T>,
    onSuccess: (T) -> Unit,
    onError: ((String?) -> Unit)? = null,
    onLoading: (() -> Unit)? = null
) {
    when (resultStatusData) {
        is Loading -> {
            showLoading()
            onLoading?.invoke()
        }
        is Success -> {
            hideLoading()
            onSuccess.invoke(resultStatusData.data)
        }
        is Error -> {
            hideLoading()
            onError?.invoke(resultStatusData.message)
        }
    }
}

/**
 * 处理网络请求带状态的返回数据，成功回调在第一个，其后两个带默认值的回调可省
 * @param resultStatusData 接口返回值
 * @param onLoading 加载中
 * @param onSuccess 成功回调
 * @param onError 失败回调
 */
fun <T> BaseVmFragment<*>.parseStatusData(
    resultStatusData: ResultState<T>,
    onSuccess: (T) -> Unit,
    onError: ((String?) -> Unit)? = null,
    onLoading: (() -> Unit)? = null
) {
    when (resultStatusData) {
        is Loading -> {
            showLoading()
            onLoading?.invoke()
        }
        is Success -> {
            hideLoading()
            onSuccess.invoke(resultStatusData.data)
        }
        is Error -> {
            hideLoading()
            onError?.invoke(resultStatusData.message)
        }
    }

}

