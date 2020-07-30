package cn.xpcheng.wanadnroidmvvm.ui.fragment

import cn.xpcheng.mvvm_core.base.fragment.BaseVmDbFragment
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.databinding.FragmentMineBinding
import cn.xpcheng.wanadnroidmvvm.viewmodel.MineViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *@author chengxinping
 *@time 2020/7/30
 *@desc
 */
class MineFragment : BaseVmDbFragment<MineViewModel, FragmentMineBinding>() {
    private val mViewModel: MineViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.fragment_mine

    override fun getViewModel(): MineViewModel = mViewModel

    override fun initView() {

    }

    override fun createObserver() {

    }

    override fun lazyLoadData() {

    }
}