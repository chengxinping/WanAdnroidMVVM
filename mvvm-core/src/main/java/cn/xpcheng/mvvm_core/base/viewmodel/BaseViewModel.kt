package cn.xpcheng.mvvm_core.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.xpcheng.mvvm_core.base.BaseResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *
 * @author chengxinping
 * @time 2020年05月18日14:36:41
 * @desc ViewModel 基类
 */
open class BaseViewModel : ViewModel() {

    //通用界面UI驱动
    val mStateLiveData = MutableLiveData<StateActionEvent>()

    /**
     * @param block 网络请求方法体
     * @param liveData 需要观察的数据体对象  不用包一层BaseApoResponse
     * @param isShowLoading 默认true 是否显示loading
     */
    fun <T> launch(
        block: suspend CoroutineScope.() -> BaseResponse<T>,
        liveData: MutableLiveData<T>,
        isShowLoading: Boolean = true
    ) {
        viewModelScope.launch {
            //ktx扩展 代替try-catch
            runCatching {
                if (isShowLoading) mStateLiveData.postValue(LoadingState)
                withContext(Dispatchers.IO) {
                    block()
                }
            }.onSuccess {
                if (it.isSuccess()) {
                    mStateLiveData.postValue(SuccessState)
                    liveData.postValue(it.getResponseData())
                } else {
                    mStateLiveData.postValue(ErrorState(it.getResponseMsg()))
                }
            }.onFailure {
                mStateLiveData.postValue(ErrorState(it.message))
            }
        }
    }


    /**
     * @param block 网络请求方法体
     * @param success 成功请求回调
     * @param error 失败请求回调 可以不传
     * @param isShowLoading 默认true 是否显示loading
     */
    fun <T> launch(
        block: suspend CoroutineScope.() -> BaseResponse<T>,
        success: (T) -> Unit,
        error: (String) -> Unit = {},
        isShowLoading: Boolean = true
    ) {
        viewModelScope.launch {
            //ktx扩展 代替try-catch
            runCatching {
                if (isShowLoading) mStateLiveData.postValue(LoadingState)
                withContext(Dispatchers.IO) {
                    block()
                }
            }.onSuccess {
                if (it.isSuccess()) {
                    mStateLiveData.postValue(SuccessState)
                    success(it.getResponseData())
                } else {
                    mStateLiveData.postValue(ErrorState(it.getResponseMsg()))
                    error(it.getResponseMsg())
                }
            }.onFailure {
                mStateLiveData.postValue(ErrorState(it.message))
                error(it.message.toString())
            }
        }
    }
}