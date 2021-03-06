package cn.xpcheng.wanadnroidmvvm.base

import android.util.Log
import androidx.databinding.ViewDataBinding
import cn.xpcheng.common.utils.toast
import cn.xpcheng.mvvm_core.base.fragment.BaseVmDbFragment
import cn.xpcheng.mvvm_core.base.network.AppException
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel
import cn.xpcheng.wanadnroidmvvm.ext.dismissLoadingExt
import cn.xpcheng.wanadnroidmvvm.ext.hideSoftKeyboard
import cn.xpcheng.wanadnroidmvvm.ext.showLoadingExt

/**
 * @author ChengXinPing
 * @time   2020/9/27 10:00
 *
 */
abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmDbFragment<VM, DB>() {

    override fun showLoading() {
        super.showLoading()
        showLoadingExt()
    }

    override fun hideLoading() {
        super.hideLoading()
        dismissLoadingExt()
    }

    override fun onPause() {
        super.onPause()
        hideSoftKeyboard(mActivity)
    }

    override fun handlerError(appException: AppException) {
        super.handlerError(appException)
        appException.errorMsg.toast(mActivity)
    }

}