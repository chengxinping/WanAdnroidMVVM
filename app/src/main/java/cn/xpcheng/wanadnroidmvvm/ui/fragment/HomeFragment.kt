package cn.xpcheng.wanadnroidmvvm.ui.fragment

import cn.xpcheng.mvvm_core.base.fragment.BaseVmDbFragment
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.databinding.FragmentHomeBinding
import cn.xpcheng.wanadnroidmvvm.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *@author chengxinping
 *@time 2020/7/30
 *@desc
 */
class HomeFragment : BaseVmDbFragment<HomeViewModel, FragmentHomeBinding>() {

    private val mViewModel: HomeViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun getViewModel(): HomeViewModel = mViewModel

    override fun initView() {

    }

    override fun createObserver() {

    }

    override fun lazyLoadData() {

    }

}