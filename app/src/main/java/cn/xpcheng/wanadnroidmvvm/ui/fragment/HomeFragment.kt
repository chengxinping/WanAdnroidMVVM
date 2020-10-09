package cn.xpcheng.wanadnroidmvvm.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cn.xpcheng.common.utils.DisplayUtil
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.base.BaseFragment
import cn.xpcheng.wanadnroidmvvm.constant.Constant
import cn.xpcheng.wanadnroidmvvm.data.bean.Article
import cn.xpcheng.wanadnroidmvvm.data.bean.WebViewData
import cn.xpcheng.wanadnroidmvvm.databinding.FragmentHomeBinding
import cn.xpcheng.wanadnroidmvvm.ext.init
import cn.xpcheng.wanadnroidmvvm.ext.nav
import cn.xpcheng.wanadnroidmvvm.ui.adapter.HomeAdapter
import cn.xpcheng.wanadnroidmvvm.viewmodel.HomeViewModel
import cn.xpcheng.wanadnroidmvvm.widget.SpaceItemDecoration
import coil.load
import kotlinx.android.synthetic.main.layout_banner.view.*
import kotlinx.android.synthetic.main.layout_recycler_view.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *@author chengxinping
 *@time 2020/7/30
 *@desc
 */
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    private val mViewModel: HomeViewModel by viewModel()

    private var page = 0

    private val bannerView: View by lazy {
        layoutInflater.inflate(R.layout.layout_banner, null, false)
    }

    private val mLinearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }

    //RecyclerView分割线
    private val mSpaceItemDecoration: SpaceItemDecoration by lazy {
        SpaceItemDecoration(DisplayUtil.dp2px(activity as Context, 10F))
    }

    private val mData = mutableListOf<Article>()

    private val mAdapter: HomeAdapter by lazy {
        HomeAdapter(mData)
    }

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun getViewModel(): HomeViewModel = mViewModel

    override fun initView() {
        toolbar.init("首页").run {
            inflateMenu(R.menu.home_search)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_search -> nav(R.id.action_mainFragment_to_searchFragment)
                }

                true
            }
        }
        recycler.init(mLinearLayoutManager, mAdapter).run {
            addItemDecoration(mSpaceItemDecoration)
        }
        mAdapter.run {
            addHeaderView(bannerView)
            loadMoreModule.setOnLoadMoreListener {
                mViewModel.getHomeData(page)
            }
            setOnItemClickListener { _, _, position ->

                nav(R.id.action_global_webViewFragment, Bundle().apply {
                    putParcelable(
                        Constant.KEY_WEB_VIEW_DATA, WebViewData(
                            mData[position].id,
                            mData[position].link,
                            mData[position].title,
                            mData[position].collect
                        )
                    )
                })
            }
        }
        swipe_refresh.run {
            setColorSchemeResources(R.color.colorAccent, R.color.colorPrimaryDark);
            setOnRefreshListener {
                page = 0
                mViewModel.getHomeData(page)
            }
        }

        fab.setOnClickListener {
            recycler.run {
                if (mLinearLayoutManager.findFirstVisibleItemPosition() > 20) {
                    scrollToPosition(0)
                } else {
                    smoothScrollToPosition(0)
                }
            }

        }
    }

    override fun lazyLoadData() {
        mViewModel.getBanner()
        mViewModel.getHomeData(page)
    }

    override fun createObserver() {

        mViewModel.run {
            homeArticles.observe(viewLifecycleOwner, Observer {
                page++
                if (it.curPage == 1) {
                    swipe_refresh.isRefreshing = false
                    mAdapter.setList(it.datas)
                } else {
                    mAdapter.addData(it.datas)
                }

                mAdapter.loadMoreModule.run {
                    if (it.over)
                        loadMoreEnd()
                    else
                        loadMoreComplete()
                }

            })

            bannerData.observe(viewLifecycleOwner, Observer {
                val imageUrls = ArrayList<String>()
                val bannerTitle = ArrayList<String>()

                it.forEach { banner ->
                    imageUrls.add(banner.imagePath)
                    bannerTitle.add(banner.title)

                }
                bannerView.banner.run {
                    setAutoPlayAble(imageUrls.size > 1)
                    setData(imageUrls, bannerTitle)
                    setAdapter { _, itemView, model, _ ->
                        (itemView as ImageView).load(model.toString()) {
                            crossfade(true)

                        }
                    }
                    setDelegate { _, _, _, position ->
                        nav(R.id.action_global_webViewFragment, Bundle().apply {
                            putParcelable(
                                Constant.KEY_WEB_VIEW_DATA, WebViewData(
                                    it[position].id,
                                    it[position].url,
                                    it[position].title,
                                    false
                                )
                            )
                        })
                    }
                }

            })
        }
    }

    override fun handlerError(errorMsg: String) {
        super.handlerError(errorMsg)
        mAdapter.loadMoreModule.loadMoreFail()
    }


}