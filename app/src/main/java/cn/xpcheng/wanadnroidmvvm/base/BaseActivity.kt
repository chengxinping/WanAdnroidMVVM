package cn.xpcheng.wanadnroidmvvm.base

import androidx.databinding.ViewDataBinding
import cn.xpcheng.mvvm_core.base.activity.BaseVmDbActivity
import cn.xpcheng.mvvm_core.base.network.AppException
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel

/**
 * @author ChengXinPing
 * @time   2020/9/27 10:12
 *
 */
abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> :
    BaseVmDbActivity<VM, DB>() {


    override fun showLoading() {
        super.showLoading()
    }

    override fun hideLoading() {
        super.hideLoading()
    }

    override fun handlerError(appException: AppException) {
        super.handlerError(appException)

    }
}