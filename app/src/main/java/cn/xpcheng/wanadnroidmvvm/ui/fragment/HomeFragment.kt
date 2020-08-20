package cn.xpcheng.wanadnroidmvvm.ui.fragment

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import cn.xpcheng.common.utils.DisplayUtil
import cn.xpcheng.mvvm_core.base.fragment.BaseVmDbFragment
import cn.xpcheng.mvvm_core.ext.parseStatusData
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.data.bean.Article
import cn.xpcheng.wanadnroidmvvm.databinding.FragmentHomeBinding
import cn.xpcheng.wanadnroidmvvm.databinding.LayoutBannerBindingImpl
import cn.xpcheng.wanadnroidmvvm.ui.adapter.HomeAdapter
import cn.xpcheng.wanadnroidmvvm.viewmodel.HomeViewModel
import cn.xpcheng.wanadnroidmvvm.widget.SpaceItemDecoration
import coil.load
import kotlinx.android.synthetic.main.layout_banner.view.*
import kotlinx.android.synthetic.main.layout_recycler_view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *@author chengxinping
 *@time 2020/7/30
 *@desc
 */
class HomeFragment : BaseVmDbFragment<HomeViewModel, FragmentHomeBinding>() {

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

        recycler.run {
            layoutManager = mLinearLayoutManager
            itemAnimator = DefaultItemAnimator()
            adapter = mAdapter
            addItemDecoration(mSpaceItemDecoration)
        }
        mAdapter.run {
            addHeaderView(bannerView)
            loadMoreModule.setOnLoadMoreListener {
                mViewModel.getHomeData(page)
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
        mViewModel.homeArticles.observe(viewLifecycleOwner, Observer {
            page++
            if (it.curPage == 1) {
                swipe_refresh.isRefreshing = false
                mAdapter.setList(it.datas)
            } else {
                mAdapter.addData(it.datas)
            }

            mAdapter.loadMoreModule.run {
                if (it.datas.size < it.size)
                    loadMoreEnd()
                else
                    loadMoreComplete()
            }

        })
        mViewModel.bannerData.observe(viewLifecycleOwner, Observer {
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
            }

        })
    }

    override fun handlerError(errorMsg: String) {
        super.handlerError(errorMsg)
        mAdapter.loadMoreModule.loadMoreFail()
    }


}