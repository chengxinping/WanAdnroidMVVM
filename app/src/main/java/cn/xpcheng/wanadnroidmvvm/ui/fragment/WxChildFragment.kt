package cn.xpcheng.wanadnroidmvvm.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cn.xpcheng.common.utils.DisplayUtil
import cn.xpcheng.mvvm_core.base.network.AppException
import cn.xpcheng.wanadnroidmvvm.NavigationDirections
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.base.BaseFragment
import cn.xpcheng.wanadnroidmvvm.data.bean.Article
import cn.xpcheng.wanadnroidmvvm.databinding.LayoutRecyclerViewBinding
import cn.xpcheng.wanadnroidmvvm.ext.init
import cn.xpcheng.wanadnroidmvvm.ext.nav
import cn.xpcheng.wanadnroidmvvm.ext.onReload
import cn.xpcheng.wanadnroidmvvm.ui.adapter.HomeAdapter
import cn.xpcheng.wanadnroidmvvm.viewmodel.WxChildViewModel
import cn.xpcheng.wanadnroidmvvm.widget.SpaceItemDecoration
import com.fengchen.uistatus.UiStatusController
import com.fengchen.uistatus.annotation.UiStatus
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


    private lateinit var mUiStatusController: UiStatusController

    override fun initView() {

        mUiStatusController = UiStatusController.get().bind(mDataBinding.swipeRefresh)

        onReload(mUiStatusController) {
            page = 1
            mViewModel.getWxArticle(cid, page)
        }

        mDataBinding.recycler.init(mLinearLayoutManager, mAdapter).run {
            addItemDecoration(mSpaceItemDecoration)
        }
        mAdapter.run {

            loadMoreModule.setOnLoadMoreListener {
                mViewModel.getWxArticle(cid, page)
            }
            setOnItemClickListener { _, _, position ->

                nav(
                    NavigationDirections.actionGlobalWebViewFragment(
                        mData[position].id, mData[position].link, mData[position].title,
                        mData[position].collect
                    )
                )
            }
        }
        mDataBinding.swipeRefresh.run {
            setColorSchemeResources(R.color.Cyan, R.color.Cyan_600)
            setOnRefreshListener {
                page = 1
                mViewModel.getWxArticle(cid, page)
            }
        }

        mDataBinding.fab.setOnClickListener {
            mDataBinding.recycler.run {
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
                mDataBinding.swipeRefresh.isRefreshing = false
                mAdapter.setList(it.datas)
            } else {
                mAdapter.addData(it.datas)
            }

            mUiStatusController.changeUiStatusIgnore(UiStatus.CONTENT)

            mAdapter.loadMoreModule.run {
                if (it.over)
                    loadMoreEnd()
                else
                    loadMoreComplete()
            }
        })
    }

    override fun handlerError(appException: AppException) {
        super.handlerError(appException)
        mAdapter.loadMoreModule.loadMoreFail()
    }
}