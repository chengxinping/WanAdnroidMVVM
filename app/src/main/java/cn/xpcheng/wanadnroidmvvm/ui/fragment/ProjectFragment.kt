package cn.xpcheng.wanadnroidmvvm.ui.fragment

import cn.xpcheng.mvvm_core.base.fragment.BaseVmDbFragment
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.databinding.FragmentProjectBinding
import cn.xpcheng.wanadnroidmvvm.viewmodel.ProjectViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *@author chengxinping
 *@time 2020/7/30
 *@desc
 */
class ProjectFragment : BaseVmDbFragment<ProjectViewModel, FragmentProjectBinding>() {
    private val mViewModel: ProjectViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.fragment_project

    override fun getViewModel(): ProjectViewModel = mViewModel

    override fun initView() {

    }

    override fun createObserver() {

    }

    override fun lazyLoadData() {

    }
}