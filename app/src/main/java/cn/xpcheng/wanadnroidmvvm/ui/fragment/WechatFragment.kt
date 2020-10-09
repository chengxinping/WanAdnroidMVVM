package cn.xpcheng.wanadnroidmvvm.ui.fragment

import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.base.BaseFragment
import cn.xpcheng.wanadnroidmvvm.databinding.FragmentWechatBinding
import cn.xpcheng.wanadnroidmvvm.viewmodel.WechatViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *@author chengxinping
 *@time 2020/7/30
 *@desc
 */
class WechatFragment : BaseFragment<WechatViewModel, FragmentWechatBinding>() {

    private val mViewModel: WechatViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.fragment_wechat

    override fun getViewModel(): WechatViewModel = mViewModel

    override fun initView() {

    }

    override fun createObserver() {

    }

    override fun lazyLoadData() {

    }
}