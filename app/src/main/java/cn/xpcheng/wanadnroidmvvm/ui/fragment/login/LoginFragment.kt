package cn.xpcheng.wanadnroidmvvm.ui.fragment.login

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.base.BaseFragment
import cn.xpcheng.wanadnroidmvvm.databinding.FragmentLoginBinding
import cn.xpcheng.wanadnroidmvvm.ext.initClose
import cn.xpcheng.wanadnroidmvvm.ext.nav
import cn.xpcheng.wanadnroidmvvm.ext.navigateBack
import cn.xpcheng.wanadnroidmvvm.utils.CacheUtil
import cn.xpcheng.wanadnroidmvvm.viewmodel.LoginViewModel
import cn.xpcheng.wanadnroidmvvm.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author ChengXinPing
 * @time   2021/1/20 15:37
 *
 */
class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {

    private val mViewModel: LoginViewModel by viewModel()

    private val mActivityViewModel: MainViewModel by activityViewModels()


    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun getViewModel(): LoginViewModel = mViewModel

    override fun initView() {
        mDataBinding.click = Proxy()
        mDataBinding.toolbar.initClose(backImg = R.drawable.ic_close_white) { navigateBack() }
    }

    override fun lazyLoadData() {

    }

    override fun createObserver() {
        mViewModel.userInfo.observe(viewLifecycleOwner, Observer {
            mActivityViewModel.userInfo.postValue(it)
        })

        mActivityViewModel.userInfo.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                CacheUtil.saveUserInfo(it)
                navigateBack()
            }
        })
    }

    inner class Proxy {
        fun login() {
            mViewModel.login(
                mDataBinding.editUserName.text.toString().trim(),
                mDataBinding.editPassword.text.toString().trim()
            )
        }

        fun goToRegister() {
            nav(R.id.action_loginFragment_to_registerFragment)
        }
    }
}