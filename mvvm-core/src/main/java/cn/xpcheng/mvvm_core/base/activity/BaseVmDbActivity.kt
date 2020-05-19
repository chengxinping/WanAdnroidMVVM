package cn.xpcheng.mvvm_core.base.activity

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel

/**
 *@author chengxinping
 *@time 2020/5/18
 *@desc 开启了databinding的baseActivity
 */
abstract class BaseVmDbActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmActivity<VM>() {
    lateinit var mDataBinding: DB

    override fun setContentLayout() {
        mDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mDataBinding.lifecycleOwner = this
        initBaseViewModelAction()
        initView()
        initData()
        createObserver()
    }

    override fun onDestroy() {
        super.onDestroy()
        mDataBinding.unbind()
    }
}