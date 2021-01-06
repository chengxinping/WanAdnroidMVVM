package cn.xpcheng.wanadnroidmvvm.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.base.BaseFragment
import cn.xpcheng.wanadnroidmvvm.constant.Constant
import cn.xpcheng.wanadnroidmvvm.data.bean.Article
import cn.xpcheng.wanadnroidmvvm.data.bean.WebViewData
import cn.xpcheng.wanadnroidmvvm.databinding.FragmentNavigationBinding
import cn.xpcheng.wanadnroidmvvm.ext.nav
import cn.xpcheng.wanadnroidmvvm.ui.adapter.NavigationAdapter
import cn.xpcheng.wanadnroidmvvm.ui.adapter.NavigationTagAdapter
import cn.xpcheng.wanadnroidmvvm.viewmodel.NavigationViewModel
import kotlinx.android.synthetic.main.fragment_navigation.*
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import q.rorbin.verticaltablayout.VerticalTabLayout
import q.rorbin.verticaltablayout.widget.TabView

/**
 * @author ChengXinPing
 * @time   2020/11/19 15:24
 *
 */
class NavigationFragment : BaseFragment<NavigationViewModel, FragmentNavigationBinding>() {

    private val mViewModel: NavigationViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.fragment_navigation

    override fun getViewModel(): NavigationViewModel = mViewModel

    //目标项是否在最后一个可见项之后
    private var mShouldScroll = false

    //记录目标项位置
    private var mToPosition = 0

    private var isClick = false
    private var mTabCurrentPosition: Int = 0


    override fun initView() {
        vertical_tab_layout_navigation.addOnTabSelectedListener(object :
            VerticalTabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabView?, position: Int) {
                isClick = true
                smoothMoveToPosition(position)
                mTabCurrentPosition = position
            }

            override fun onTabReselected(tab: TabView?, position: Int) {

            }

        })

        recycler_navigation.run {
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (RecyclerView.SCROLL_STATE_IDLE == newState) {
                        if (mShouldScroll) {
                            mShouldScroll = false
                            smoothMoveToPosition(mToPosition)
                        }

                        if (isClick) {
                            isClick = false
                            return
                        } else {
                            val firstPosition: Int =
                                (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                            if (firstPosition != mTabCurrentPosition) {
                                vertical_tab_layout_navigation.setTabSelected(firstPosition)
                            }
                        }

                    }

                }

            })

        }
    }

    private fun smoothMoveToPosition(position: Int) {
        val linearLayoutManager = recycler_navigation.layoutManager as LinearLayoutManager
        val firstPosition = linearLayoutManager.findFirstVisibleItemPosition()
        val lastPosition = linearLayoutManager.findLastVisibleItemPosition()
        when {
            position < firstPosition -> {
                //第一种可能:跳转位置在第一个可见位置之前，使用smoothScrollToPosition
                recycler_navigation.smoothScrollToPosition(position)
            }
            position <= lastPosition -> {
                // 第二种可能:跳转位置在第一个可见位置之后，最后一个可见项之前
                // smoothScrollToPosition 不会有效果，此时调用smoothScrollBy来滑动到指定位置
                val top = recycler_navigation.getChildAt(position - firstPosition).top
                recycler_navigation.smoothScrollBy(0, top)
            }
            else -> {
                // 第三种可能:跳转位置在最后可见项之后，则先调用smoothScrollToPosition将要跳转的位置滚动到可见位置
                // 再通过onScrollStateChanged控制再次调用smoothMoveToPosition，执行上一个判断中的方法
                recycler_navigation.smoothScrollToPosition(position)
                mToPosition = position
                mShouldScroll = true
            }
        }
    }

    override fun lazyLoadData() {
        mViewModel.getNavigationList()
    }

    override fun createObserver() {
        mViewModel.navigationList.observe(viewLifecycleOwner, Observer {
            vertical_tab_layout_navigation.run {
                setTabAdapter(NavigationTagAdapter(it))
            }
            recycler_navigation.run {
                layoutManager = LinearLayoutManager(this@NavigationFragment.context)
                itemAnimator = DefaultItemAnimator()
                setHasFixedSize(true)
                adapter = NavigationAdapter(it.toMutableList(), ProxyClick())
            }
        })
    }

    inner class ProxyClick {
        fun navDetail(article: Article) {
            nav(R.id.action_global_webViewFragment, Bundle().apply {
                putParcelable(
                    Constant.KEY_WEB_VIEW_DATA, WebViewData(
                        article.id,
                        article.link,
                        article.title,
                        false
                    )
                )
            })
        }
    }
}