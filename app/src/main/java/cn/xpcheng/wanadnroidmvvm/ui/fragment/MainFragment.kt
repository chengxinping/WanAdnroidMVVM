package cn.xpcheng.wanadnroidmvvm.ui.fragment

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import cn.xpcheng.mvvm_core.base.fragment.BaseVmDbFragment
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.databinding.FragmentMainBinding
import cn.xpcheng.wanadnroidmvvm.viewmodel.Main2ViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *@author chengxinping
 *@time 2020/5/22
 *@desc
 */

class MainFragment : BaseVmDbFragment<Main2ViewModel, FragmentMainBinding>() {

    private val mViewModel: Main2ViewModel by activityViewModels<Main2ViewModel>()


    override fun getLayoutId(): Int = R.layout.fragment_main


    override fun getViewModel(): Main2ViewModel = mViewModel

    override fun createObserver() {

    }

    override fun lazyLoadData() {

    }

    override fun initView() {
        mDataBinding.vm = mViewModel
    }

}