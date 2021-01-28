package cn.xpcheng.wanadnroidmvvm.ui.fragment

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import cn.xpcheng.wanadnroidmvvm.NavigationDirections
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.base.BaseFragment
import cn.xpcheng.wanadnroidmvvm.constant.Constant
import cn.xpcheng.wanadnroidmvvm.databinding.FragmentMineBinding
import cn.xpcheng.wanadnroidmvvm.ext.init
import cn.xpcheng.wanadnroidmvvm.ext.nav
import cn.xpcheng.wanadnroidmvvm.utils.CacheUtil
import cn.xpcheng.wanadnroidmvvm.viewmodel.MainViewModel
import cn.xpcheng.wanadnroidmvvm.viewmodel.MineViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *@author chengxinping
 *@time 2020/7/30
 *@desc
 */
class MineFragment : BaseFragment<MineViewModel, FragmentMineBinding>() {
    private val mViewModel: MineViewModel by viewModel()

    private val mActivityViewModel: MainViewModel by activityViewModels()

    override fun getLayoutId(): Int = R.layout.fragment_mine

    override fun getViewModel(): MineViewModel = mViewModel

    override fun initView() {
        mDataBinding.layoutToolbar.toolbar.init("我的")
        mDataBinding.click = ProxyClick()
    }

    override fun createObserver() {

        mViewModel.isLogoutSuccess.observe(viewLifecycleOwner, {
            if (it) {
                CacheUtil.saveUserInfo(null)
                mActivityViewModel.userInfo.value = null
            }
        })

        mActivityViewModel.userInfo.observe(viewLifecycleOwner, {
            if (it != null) {
                mDataBinding.tvUserName.text = it.username
            } else {
                mDataBinding.tvUserName.text = "去登陆"
            }
        })
    }

    override fun lazyLoadData() {

    }

    inner class ProxyClick {
        fun goToSetting() {

        }

        fun goToWebView() {

            nav(
                NavigationDirections.actionGlobalWebViewFragment(
                    -1,
                    "https://www.wanandroid.com/",
                    "玩Android",
                    false
                )
            )
        }

        fun goToLogin() {
            if (mActivityViewModel.userInfo.value == null) {
                nav(R.id.action_global_loginFragment)
            } else {
                //退出登录
                mViewModel.logout()
            }
        }

        fun goToMyPoints() {

        }

        fun goToMyCollects() {

        }

        fun goToMyArticles() {

        }


        fun goToTodo() {

        }
    }
}