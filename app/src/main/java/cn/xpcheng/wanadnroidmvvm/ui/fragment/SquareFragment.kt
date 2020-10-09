package cn.xpcheng.wanadnroidmvvm.ui.fragment

import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.base.BaseFragment
import cn.xpcheng.wanadnroidmvvm.databinding.FragmentSquareBinding
import cn.xpcheng.wanadnroidmvvm.viewmodel.SquareViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *@author chengxinping
 *@time 2020/7/30
 *@desc
 */
class SquareFragment : BaseFragment<SquareViewModel, FragmentSquareBinding>() {
    private val mViewModel: SquareViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.fragment_square

    override fun getViewModel(): SquareViewModel = mViewModel

    override fun initView() {

    }

    override fun createObserver() {

    }

    override fun lazyLoadData() {

    }
}