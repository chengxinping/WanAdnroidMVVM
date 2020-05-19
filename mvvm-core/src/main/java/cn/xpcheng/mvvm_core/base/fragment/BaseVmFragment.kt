package cn.xpcheng.mvvm_core.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel
import cn.xpcheng.mvvm_core.base.viewmodel.ErrorState
import cn.xpcheng.mvvm_core.base.viewmodel.LoadingState
import cn.xpcheng.mvvm_core.base.viewmodel.SuccessState

/**
 *@author chengxinping
 *@time 2020/5/18
 *@desc ViewModel的fragment基类
 */

abstract class BaseVmFragment<VM : BaseViewModel> : Fragment() {

    //是否是第一次加载
    private var isFirst: Boolean = true

    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): VM


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        onVisible()
        initBaseViewModelAction()
    }

    /**
     * 创建liveData监听（网络成功回调）
     */
    abstract fun createObserver()


    override fun onResume() {
        super.onResume()
        onVisible()
    }

    /**
     * 是否需要懒加载
     */
    private fun onVisible() {
        if (lifecycle.currentState == Lifecycle.State.STARTED && isFirst) {
            lazyLoadData()
            isFirst = false
            createObserver()
        }
    }

    /**
     * 懒加载数据
     */
    abstract fun lazyLoadData()

    /**
     *初始化公共的数据监听 是否显示loading等
     */
    protected fun initBaseViewModelAction() {
        getViewModel().let {
            it.mStateLiveData.observe(this, Observer { stateActionEvent ->
                when (stateActionEvent) {
                    is LoadingState -> showLoading()
                    is SuccessState -> hideLoading()
                    is ErrorState -> {
                        hideLoading()
                        stateActionEvent.message?.let {
                            handlerError(stateActionEvent.message)
                        }
                    }
                }
            })
        }
    }

    abstract fun initView()

    open fun showLoading() {}

    open fun hideLoading() {}

    open fun handlerError(errorMsg: String) {}
}