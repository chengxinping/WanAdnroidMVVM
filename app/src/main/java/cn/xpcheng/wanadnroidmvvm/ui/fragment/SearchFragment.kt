package cn.xpcheng.wanadnroidmvvm.ui.fragment

import cn.xpcheng.mvvm_core.base.fragment.BaseVmDbFragment
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.databinding.FragmentSearchBinding
import cn.xpcheng.wanadnroidmvvm.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *@author chengxinping
 *@time 2020/9/14
 *@desc
 */
class SearchFragment : BaseVmDbFragment<SearchViewModel, FragmentSearchBinding>() {

    private val mViewModel: SearchViewModel by viewModel()


    override fun getLayoutId(): Int = R.layout.fragment_search

    override fun getViewModel(): SearchViewModel =mViewModel

    override fun initView() {

    }

    override fun lazyLoadData() {

    }

    override fun createObserver() {

    }
}