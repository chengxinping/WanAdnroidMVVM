package cn.xpcheng.wanadnroidmvvm.base

import androidx.databinding.ViewDataBinding
import cn.xpcheng.mvvm_core.base.fragment.BaseVmDbFragment
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel
import cn.xpcheng.wanadnroidmvvm.ext.hideSoftKeyboard

/**
 * @author ChengXinPing
 * @time   2020/9/27 10:00
 *
 */
abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmDbFragment<VM, DB>() {

    override fun showLoading() {
        super.showLoading()
    }

    override fun hideLoading() {
        super.hideLoading()
    }

    override fun onPause() {
        super.onPause()
        hideSoftKeyboard(mActivity)
    }

}