package cn.xpcheng.mvvm_core.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.xpcheng.mvvm_core.base.BaseResponse
import kotlinx.coroutines.*

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
     * 通用处理loading error的网络请求
     * @param block 网络请求方法体
     * @param liveData 需要观察的数据体对象  不用包一层BaseApoResponse
     * @param isShowLoading 默认true 是否显示loading
     */
    fun <T> launch(
        block: suspend CoroutineScope.() -> BaseResponse<T>,
        liveData: MutableLiveData<T>,
        isShowLoading: Boolean = false
    ): Job {
        return viewModelScope.launch {
            //ktx扩展 代替try-catch
            runCatching {
                if (isShowLoading) mStateLiveData.postValue(LoadingState)
                withContext(Dispatchers.IO) {
                    block()
                }
            }.onSuccess {

                //可以再封装一下 将错误包装秤自定义异常

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
     * 特殊处理的网络请求  error处理不用界面的通用error处理  需要配合parseStatusData一起用
     * @param block 网络请求方法体
     * @param liveData 需要观察的数据体对象  不用包一层BaseApoResponse
     * @param isShowLoading 默认true 是否显示loading
     */
    fun <T> request(
        block: suspend CoroutineScope.() -> BaseResponse<T>,
        liveData: MutableLiveData<ResultState<T>>,
        isShowLoading: Boolean = false
    ): Job {
        return viewModelScope.launch {
            runCatching {
                if (isShowLoading) liveData.postValue(ResultState.onAppLoading())
                withContext(Dispatchers.IO) {
                    block()
                }
            }.onSuccess {
                if (it.isSuccess())
                    liveData.postValue(ResultState.onAppSuccess(it.getResponseData()))
                else
                    liveData.postValue(ResultState.onAppError(it.getResponseMsg()))
            }.onFailure {
                liveData.postValue(ResultState.onAppError(it.message))
            }
        }
    }


    /**
     *在viewmodel 里面处理的成功失败
     * 需要自己控制 loading
     * @param block 网络请求方法体
     * @param success 成功请求回调
     * @param error 失败请求回调 可以不传
     */
    fun <T> launch(
        block: suspend CoroutineScope.() -> BaseResponse<T>,
        success: (T) -> Unit,
        error: (String) -> Unit = {}
    ): Job {
        return viewModelScope.launch {
            //ktx扩展 代替try-catch
            runCatching {
                withContext(Dispatchers.IO) {
                    block()
                }
            }.onSuccess {

                //可以再封装一下 将错误包装秤自定义异常
                if (it.isSuccess()) {
                    success(it.getResponseData())
                } else {
                    error(it.getResponseMsg())
                }
            }.onFailure {
                error(it.message.toString())
            }
        }
    }

    /**
     *  耗时操作  数据库查询
     * @param block 操作耗时操作任务 需要suspend 方法
     * @param success 成功回调
     * @param error 失败回调 可不给
     */
    fun <T> emit(
        block: suspend CoroutineScope.() -> T,
        success: (T) -> Unit,
        error: (Throwable) -> Unit = {}
    ): Job {
        return viewModelScope.launch {
            runCatching {
                withContext(Dispatchers.IO) {
                    block()
                }
            }.onSuccess {
                success(it)
            }.onFailure {
                error(it)
            }
        }
    }
}