package cn.xpcheng.wanadnroidmvvm.ui.fragment

import android.text.Html
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.base.BaseFragment
import cn.xpcheng.wanadnroidmvvm.data.bean.Tree
import cn.xpcheng.wanadnroidmvvm.databinding.FragmentTreeDetailBinding
import cn.xpcheng.wanadnroidmvvm.ext.initClose
import cn.xpcheng.wanadnroidmvvm.ext.navigateBack
import cn.xpcheng.wanadnroidmvvm.viewmodel.EmptyViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.layout_tablayout.*
import kotlinx.android.synthetic.main.layout_toolbar.*
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

    private lateinit var data: Tree

    override fun getLayoutId(): Int = R.layout.fragment_tree_detail

    override fun getViewModel(): EmptyViewModel = mViewModel

    override fun initView() {
        arguments?.let {
            data = it.getParcelable("data")!!
            index = it.getInt("index")
        }
        toolbar.initClose(data.name) {
            navigateBack()
        }
    }

    override fun lazyLoadData() {
        data.children.forEach {
            mFragments.add(TreeArticleFragment.newInstance(it.id))
        }

        view_pager.run {
            //是否可以滑动
            isUserInputEnabled = true
            adapter = object : FragmentStateAdapter(this@TreeDetailFragment) {
                override fun getItemCount(): Int = mFragments.size

                override fun createFragment(position: Int): Fragment = mFragments[position]

            }
        }

        TabLayoutMediator(
            tab_layout,
            view_pager
        ) { tab, position ->
            tab.text = data.children[position].name
        }.attach()

        view_pager.postDelayed({
            view_pager.currentItem = index
        }, 100)
    }

    override fun createObserver() {

    }
}