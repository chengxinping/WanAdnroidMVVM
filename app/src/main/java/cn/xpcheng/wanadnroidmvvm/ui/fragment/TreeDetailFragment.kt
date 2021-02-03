package cn.xpcheng.wanadnroidmvvm.ui.fragment

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.base.BaseFragment
import cn.xpcheng.wanadnroidmvvm.data.bean.Tree
import cn.xpcheng.wanadnroidmvvm.databinding.FragmentTreeDetailBinding
import cn.xpcheng.wanadnroidmvvm.ext.initClose
import cn.xpcheng.wanadnroidmvvm.ext.navigateBack
import cn.xpcheng.wanadnroidmvvm.viewmodel.EmptyViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author ChengXinPing
 * @time   2021/1/6 15:33
 *
 */
class TreeDetailFragment : BaseFragment<EmptyViewModel, FragmentTreeDetailBinding>() {

    private val mViewModel: EmptyViewModel by viewModel()

    private val mFragments: ArrayList<Fragment> = arrayListOf()

    private var index = 0

    private val args: TreeDetailFragmentArgs by navArgs()

    private lateinit var data: Tree

    override fun getLayoutId(): Int = R.layout.fragment_tree_detail

    override fun getViewModel(): EmptyViewModel = mViewModel

    override fun initView() {
        args.let {
            data = it.data
            index = it.index
        }
        mDataBinding.layoutToolbar.toolbar.initClose(data.name) {
            navigateBack()
        }
    }

    override fun lazyLoadData() {
        data.children.forEach {
            mFragments.add(TreeArticleFragment.newInstance(it.id))
        }

        mDataBinding.layoutTabLayout.viewPager.run {
            //是否可以滑动
            isUserInputEnabled = true
            adapter = object : FragmentStateAdapter(this@TreeDetailFragment) {
                override fun getItemCount(): Int = mFragments.size

                override fun createFragment(position: Int): Fragment = mFragments[position]

            }
            offscreenPageLimit = mFragments.size
        }

        TabLayoutMediator(
            mDataBinding.layoutTabLayout.tabLayout,
            mDataBinding.layoutTabLayout.viewPager
        ) { tab, position ->
            tab.text = data.children[position].name
        }.attach()

        mDataBinding.layoutTabLayout.viewPager.postDelayed({
            mDataBinding.layoutTabLayout.viewPager.currentItem = index
        }, 100)
    }

    override fun createObserver() {

    }
}