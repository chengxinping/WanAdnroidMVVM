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
import cn.xpcheng.wanadnroidmvvm.databinding.LayoutRecyclerViewBinding
import cn.xpcheng.wanadnroidmvvm.ext.init
import cn.xpcheng.wanadnroidmvvm.ext.nav
import cn.xpcheng.wanadnroidmvvm.ui.adapter.HomeAdapter
import cn.xpcheng.wanadnroidmvvm.viewmodel.WxChildViewModel
import cn.xpcheng.wanadnroidmvvm.widget.SpaceItemDecoration
import kotlinx.android.synthetic.main.layout_recycler_view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author ChengXinPing
 * @time   2020/10/14 16:08
 *
 */
class WxChildFragment : BaseFragment<WxChildViewModel, LayoutRecyclerViewBinding>() {


    companion object {
        fun newInstance(cid: Int): WxChildFragment {
            return WxChildFragment().apply {
                arguments = Bundle().apply {
                    putInt("cid", cid)
                }
            }
        }
    }

    private val mViewModel: WxChildViewModel by viewModel()

    private var cid = -1
    private var page = 1

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

    override fun getLayoutId(): Int = R.layout.layout_recycler_view

    override fun getViewModel(): WxChildViewModel = mViewModel

    override fun initView() {
        recycler.init(mLinearLayoutManager, mAdapter).run {
            addItemDecoration(mSpaceItemDecoration)
        }
        mAdapter.run {

            loadMoreModule.setOnLoadMoreListener {
                mViewModel.getWxArticle(cid, page)
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
                page = 1
                mViewModel.getWxArticle(cid, page)
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
        arguments?.let {
            cid = it.getInt("cid")
        }
        page = 1
        mViewModel.getWxArticle(cid, page)
    }

    override fun createObserver() {
        mViewModel.wxArticle.observe(viewLifecycleOwner, Observer {
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