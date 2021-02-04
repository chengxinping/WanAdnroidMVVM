package cn.xpcheng.wanadnroidmvvm.ui.fragment.mine.collect

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.base.BaseFragment
import cn.xpcheng.wanadnroidmvvm.databinding.FragmentCollectBinding
import cn.xpcheng.wanadnroidmvvm.ext.initClose
import cn.xpcheng.wanadnroidmvvm.ext.navigateBack
import cn.xpcheng.wanadnroidmvvm.viewmodel.EmptyViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author ChengXinPing
 * @time   2021/2/4 14:37
 *
 */
class CollectFragment : BaseFragment<EmptyViewModel, FragmentCollectBinding>() {

    private val mViewModel: EmptyViewModel by viewModel()

    private val mTitle = arrayListOf("文章", "网址")

    private val mFragments: ArrayList<Fragment> = arrayListOf()

    init {
        mFragments.add(CollectArticleFragment())
        mFragments.add(CollectUrlFragment())
    }

    override fun getLayoutId(): Int = R.layout.fragment_collect

    override fun getViewModel(): EmptyViewModel = mViewModel

    override fun initView() {

        mDataBinding.run {

            layoutToolbar.toolbar.initClose {
                navigateBack()
            }

            tabLayout.run {
                tabMode = TabLayout.MODE_FIXED
            }
            viewPager.run {
                offscreenPageLimit = mFragments.size

                adapter = object : FragmentStateAdapter(this@CollectFragment) {
                    override fun getItemCount(): Int = mFragments.size

                    override fun createFragment(position: Int): Fragment {
                        return mFragments[position]
                    }

                }
            }
        }

        TabLayoutMediator(
            mDataBinding.tabLayout,
            mDataBinding.viewPager
        ) { tab, position ->
            tab.text = mTitle[position]
        }.attach()
    }

    override fun lazyLoadData() {

    }

    override fun createObserver() {

    }
}