package cn.xpcheng.wanadnroidmvvm.ui.fragment

import android.text.Html
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager2.adapter.FragmentStateAdapter
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.base.BaseFragment
import cn.xpcheng.wanadnroidmvvm.databinding.LayoutTablayoutBinding
import cn.xpcheng.wanadnroidmvvm.viewmodel.ProjectViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *@author chengxinping
 *@time 2020/7/30
 *@desc
 */
class ProjectFragment : BaseFragment<ProjectViewModel, LayoutTablayoutBinding>() {
    private val mViewModel: ProjectViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.layout_tablayout

    override fun getViewModel(): ProjectViewModel = mViewModel

    override fun initView() {

    }

    override fun createObserver() {
        mViewModel.projectTree.observe(viewLifecycleOwner, Observer {
            mDataBinding.viewPager.run {
                offscreenPageLimit = it.size

                adapter = object : FragmentStateAdapter(this@ProjectFragment) {
                    override fun getItemCount(): Int = it.size

                    override fun createFragment(position: Int): Fragment {
                        return ProjectChildFragment.newInstance(it[position].id)
                    }

                }
            }

            TabLayoutMediator(
                mDataBinding.tabLayout,
                mDataBinding.viewPager
            ) { tab, position ->
                tab.text = Html.fromHtml(it[position].name)
            }.attach()
        })
    }

    override fun lazyLoadData() {
        mViewModel.getProjectTree()
    }
}