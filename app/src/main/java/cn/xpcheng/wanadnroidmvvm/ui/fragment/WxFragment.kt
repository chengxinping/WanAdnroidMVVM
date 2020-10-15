package cn.xpcheng.wanadnroidmvvm.ui.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager2.adapter.FragmentStateAdapter
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.base.BaseFragment
import cn.xpcheng.wanadnroidmvvm.databinding.LayoutTablayoutBinding
import cn.xpcheng.wanadnroidmvvm.viewmodel.WxViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.layout_tablayout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *@author chengxinping
 *@time 2020/7/30
 *@desc
 */
class WxFragment : BaseFragment<WxViewModel, LayoutTablayoutBinding>() {

    private val mViewModel: WxViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.layout_tablayout

    override fun getViewModel(): WxViewModel = mViewModel

    override fun initView() {

    }

    override fun createObserver() {
        mViewModel.wxTree.observe(viewLifecycleOwner, Observer {
            view_pager.run {
                offscreenPageLimit = it.size

                adapter = object : FragmentStateAdapter(this@WxFragment) {
                    override fun getItemCount(): Int = it.size

                    override fun createFragment(position: Int): Fragment {
                        return WxChildFragment.newInstance(it[position].id)
                    }

                }
            }

            TabLayoutMediator(
                tab_layout,
                view_pager
            ) { tab, position -> tab.text = it[position].name }.attach();
        })
    }

    override fun lazyLoadData() {
        mViewModel.getWxTree()
    }
}