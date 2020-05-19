package cn.xpcheng.mvvm_core.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel

/**
 *@author chengxinping
 *@time 2020/5/18
 *@desc ViewModel、DataBinding的fragment基类
 */
abstract class BaseVmDbFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmFragment<VM>() {

    lateinit var mDataBinding: DB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mDataBinding.lifecycleOwner = this
        return mDataBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        mDataBinding.unbind()
    }
}