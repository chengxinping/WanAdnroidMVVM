package cn.xpcheng.wanadnroidmvvm.ui.fragment

import android.app.IntentService
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.base.BaseFragment
import cn.xpcheng.wanadnroidmvvm.databinding.FragmentRegisterBinding
import cn.xpcheng.wanadnroidmvvm.ext.initClose
import cn.xpcheng.wanadnroidmvvm.ext.navigateBack
import cn.xpcheng.wanadnroidmvvm.viewmodel.MainViewModel
import cn.xpcheng.wanadnroidmvvm.viewmodel.RegisterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author ChengXinPing
 * @time   2021/1/27 11:52
 *
 */
class RegisterFragment : BaseFragment<RegisterViewModel, FragmentRegisterBinding>() {

    private val mViewModel: RegisterViewModel by viewModel()

    private val mActivityViewModel: MainViewModel by activityViewModels()

    override fun getLayoutId(): Int = R.layout.fragment_register

    override fun getViewModel(): RegisterViewModel = mViewModel

    override fun initView() {
        mDataBinding.click = Proxy()
        mDataBinding.layoutToolbar.toolbar.initClose("注册") {
            navigateBack()
        }
    }

    override fun lazyLoadData() {

    }

    override fun createObserver() {
        mViewModel.userInfo.observe(viewLifecycleOwner, Observer {
            mActivityViewModel.userInfo.postValue(it)
            navigateBack()
        })
    }

    inner class Proxy {
        fun registerAndLogin() {
            mViewModel.registerAndLogin(
                mDataBinding.editUserName.text.toString().trim(),
                mDataBinding.editPassword.text.toString().trim(),
                mDataBinding.editRePassword.text.toString().trim()
            )
        }
    }
}