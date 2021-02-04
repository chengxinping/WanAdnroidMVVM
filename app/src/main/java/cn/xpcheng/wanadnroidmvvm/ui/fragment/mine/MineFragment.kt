package cn.xpcheng.wanadnroidmvvm.ui.fragment.mine

import androidx.fragment.app.activityViewModels
import cn.xpcheng.wanadnroidmvvm.NavigationDirections
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.base.BaseFragment
import cn.xpcheng.wanadnroidmvvm.databinding.FragmentMineBinding
import cn.xpcheng.wanadnroidmvvm.ext.init
import cn.xpcheng.wanadnroidmvvm.ext.nav
import cn.xpcheng.wanadnroidmvvm.ext.navOrLogin
import cn.xpcheng.wanadnroidmvvm.ui.fragment.MainFragmentDirections
import cn.xpcheng.wanadnroidmvvm.utils.CacheUtil
import cn.xpcheng.wanadnroidmvvm.viewmodel.MainViewModel
import cn.xpcheng.wanadnroidmvvm.viewmodel.MineViewModel
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
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

    override fun lazyLoadData() {
        if (CacheUtil.isLogin()) {
            mViewModel.getMyPoint()
        }
    }

    override fun createObserver() {

        mViewModel.run {
            isLogoutSuccess.observe(viewLifecycleOwner, {
                if (it) {
                    CacheUtil.saveUserInfo(null)
                    mActivityViewModel.userInfo.value = null
                }
            })

            point.observe(viewLifecycleOwner, {
                if (it != null) {
                    mDataBinding.tvMyPoint.text = it.coinCount.toString()
                } else {
                    mDataBinding.tvMyPoint.text = ""
                }
            })
        }

        mActivityViewModel.userInfo.observe(viewLifecycleOwner, {
            if (it != null) {
                mViewModel.getMyPoint()
                mDataBinding.tvUserName.text = it.username
            } else {
                mDataBinding.tvUserName.text = "去登陆"
                mViewModel.point.value = null
            }
        })
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
            if (CacheUtil.isLogin()) {
                MaterialDialog(mActivity)
                    .cancelable(true)
                    .cancelOnTouchOutside(true)
                    .lifecycleOwner(mActivity)
                    .show {
                        title(text = "确认退出登录？")
                        positiveButton(text = "确定") {
                            mViewModel.logout()
                        }
                        negativeButton(text = "取消") {
                            dismiss()
                        }
                    }
            } else {
                nav(R.id.action_global_loginFragment)
            }
        }

        fun goToMyPoints() {
            mViewModel.point.value?.let {
                nav(
                    MainFragmentDirections.actionMainFragmentToPointFragment(
                        it.rank,
                        it.username,
                        it.coinCount
                    )
                )
            } ?: nav(
                MainFragmentDirections.actionMainFragmentToPointFragment()
            )

        }

        fun goToMyCollects() {
            navOrLogin(R.id.action_mainFragment_to_collectFragment)
        }

        fun goToMyArticles() {
            navOrLogin(R.id.action_mainFragment_to_myArticleFragment)
        }


        fun goToTodo() {

        }
    }
}