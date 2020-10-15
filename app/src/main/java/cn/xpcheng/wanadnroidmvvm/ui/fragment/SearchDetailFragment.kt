package cn.xpcheng.wanadnroidmvvm.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cn.xpcheng.common.utils.DisplayUtil
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.base.BaseFragment
import cn.xpcheng.wanadnroidmvvm.constant.Constant
import cn.xpcheng.wanadnroidmvvm.data.bean.Article
import cn.xpcheng.wanadnroidmvvm.data.bean.WebViewData
import cn.xpcheng.wanadnroidmvvm.databinding.FragmentSearchDetailBinding
import cn.xpcheng.wanadnroidmvvm.ext.init
import cn.xpcheng.wanadnroidmvvm.ext.initClose
import cn.xpcheng.wanadnroidmvvm.ext.nav
import cn.xpcheng.wanadnroidmvvm.ext.navigateBack
import cn.xpcheng.wanadnroidmvvm.ui.adapter.HomeAdapter
import cn.xpcheng.wanadnroidmvvm.viewmodel.SearchDetailViewModel
import cn.xpcheng.wanadnroidmvvm.widget.SpaceItemDecoration
import kotlinx.android.synthetic.main.layout_recycler_view.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author ChengXinPing
 * @time   2020/10/9 9:47
 *
 */
class SearchDetailFragment : BaseFragment<SearchDetailViewModel, FragmentSearchDetailBinding>() {

    private var searchKey: String = ""
    private var page = 0

    private val mViewModel: SearchDetailViewModel by viewModel()

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


    override fun getLayoutId(): Int = R.layout.fragment_search_detail

    override fun getViewModel(): SearchDetailViewModel = mViewModel

    override fun initView() {

        recycler.init(mLinearLayoutManager, mAdapter).run {
            addItemDecoration(mSpaceItemDecoration)
        }

        mAdapter.run {

            loadMoreModule.setOnLoadMoreListener {
                mViewModel.search(searchKey, page)
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
                mViewModel.search(searchKey, page)
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

    override fun initData() {
        super.initData()
        arguments?.run {
            searchKey = getString("searchKey", "")
        }
        toolbar.initClose(searchKey, onBack = {
            navigateBack()
        })
    }

    override fun lazyLoadData() {
        mViewModel.search(searchKey, page)
    }

    override fun createObserver() {
        mViewModel.searchResult.observe(viewLifecycleOwner, Observer {
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
    }

    override fun handlerError(errorMsg: String) {
        super.handlerError(errorMsg)
        mAdapter.loadMoreModule.loadMoreFail()
    }
}