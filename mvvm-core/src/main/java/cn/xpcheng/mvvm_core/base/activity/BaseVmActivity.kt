package cn.xpcheng.mvvm_core.base.activity

import androidx.lifecycle.Observer
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel
import cn.xpcheng.mvvm_core.base.viewmodel.ErrorState
import cn.xpcheng.mvvm_core.base.viewmodel.LoadingState
import cn.xpcheng.mvvm_core.base.viewmodel.SuccessState

/**
 *@author chengxinping
 *@time 2020/5/18
 *@desc 使用ViewModel的baseActivity
 */
abstract class BaseVmActivity<VM : BaseViewModel> : BaseActivity() {



    abstract fun getViewModel(): VM

    override fun setContentLayout() {
        setContentView(getLayoutId())

        initBaseViewModelAction()
        initView()
        initData()
        createObserver()
    }

    /**
     * 创建liveData监听（网络成功回调）
     */
    abstract fun createObserver()

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

    open fun showLoading() {}

    open fun hideLoading() {}

    open fun handlerError(errorMsg: String) {}

}