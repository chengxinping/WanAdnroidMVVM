package cn.xpcheng.wanadnroidmvvm.ui.fragment

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.base.BaseFragment
import cn.xpcheng.wanadnroidmvvm.data.http.NULL_TO_EMPTY_STRING_ADAPTER
import cn.xpcheng.wanadnroidmvvm.databinding.FragmentMainBinding
import cn.xpcheng.wanadnroidmvvm.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.squareup.moshi.Moshi
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *@author chengxinping
 *@time 2020/7/29
 *@desc
 */
class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>() {

    private val mViewModel: MainViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.fragment_main

    override fun getViewModel(): MainViewModel = mViewModel

    override fun initView() {
        mDataBinding.bottomNavigation.run {
            labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.navigation_home -> mDataBinding.viewPager.setCurrentItem(0, false)
                    R.id.navigation_wechat -> mDataBinding.viewPager.setCurrentItem(1, false)
                    R.id.navigation_square -> mDataBinding.viewPager.setCurrentItem(2, false)
                    R.id.navigation_project -> mDataBinding.viewPager.setCurrentItem(3, false)
                    R.id.navigation_mine -> mDataBinding.viewPager.setCurrentItem(4, false)
                }
                true
            }
        }

        mDataBinding.viewPager.run {
            //是否可以滑动
            isUserInputEnabled = false
            offscreenPageLimit = 5

            adapter = object : FragmentStateAdapter(this@MainFragment) {
                override fun getItemCount(): Int = 5

                override fun createFragment(position: Int): Fragment {
                    return when (position) {
                        0 -> HomeFragment()
                        1 -> WxFragment()
                        2 -> SquareFragment()
                        3 -> ProjectFragment()
                        4 -> MineFragment()
                        else -> HomeFragment()
                    }
                }

            }
        }

    }

    override fun createObserver() {

    }

    override fun lazyLoadData() {

    }


}