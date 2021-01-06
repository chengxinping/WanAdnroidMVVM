package cn.xpcheng.wanadnroidmvvm.ui.fragment

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.base.BaseFragment
import cn.xpcheng.wanadnroidmvvm.databinding.LayoutTablayoutBinding
import cn.xpcheng.wanadnroidmvvm.viewmodel.EmptyViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view_pager
import kotlinx.android.synthetic.main.layout_tablayout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *@author chengxinping
 *@time 2020/7/30
 *@desc
 */
class SquareFragment : BaseFragment<EmptyViewModel, LayoutTablayoutBinding>() {
    private val mViewModel: EmptyViewModel by viewModel()

    private val mTitle = arrayListOf("广场", "问答", "导航", "体系")

    private val mFragments: ArrayList<Fragment> = arrayListOf()

    init {
        mFragments.add(SquareListFragment())
        mFragments.add(QuestionFragment())
        mFragments.add(NavigationFragment())
        mFragments.add(TreeFragment())
    }

    override fun getLayoutId(): Int = R.layout.layout_tablayout

    override fun getViewModel(): EmptyViewModel = mViewModel

    override fun initView() {

        tab_layout.run {
            tabMode = TabLayout.MODE_FIXED
        }

        view_pager.run {
            offscreenPageLimit = mFragments.size

            adapter = object : FragmentStateAdapter(this@SquareFragment) {
                override fun getItemCount(): Int = mFragments.size

                override fun createFragment(position: Int): Fragment {
                    return mFragments[position]
                }

            }
        }
        TabLayoutMediator(
            tab_layout,
            view_pager
        ) { tab, position ->
            tab.text = mTitle[position]
        }.attach()

    }

    override fun createObserver() {

    }

    override fun lazyLoadData() {

    }
}