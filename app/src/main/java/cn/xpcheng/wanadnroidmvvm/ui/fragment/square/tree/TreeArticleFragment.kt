package cn.xpcheng.wanadnroidmvvm.ui.fragment.square.tree

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cn.xpcheng.common.utils.DisplayUtil
import cn.xpcheng.mvvm_core.base.network.AppException
import cn.xpcheng.wanadnroidmvvm.NavigationDirections
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.base.BaseFragment
import cn.xpcheng.wanadnroidmvvm.databinding.LayoutRecyclerViewBinding
import cn.xpcheng.wanadnroidmvvm.ext.init
import cn.xpcheng.wanadnroidmvvm.ext.nav
import cn.xpcheng.wanadnroidmvvm.ext.onReload
import cn.xpcheng.wanadnroidmvvm.ui.adapter.HomeAdapter
import cn.xpcheng.wanadnroidmvvm.viewmodel.TreeArticleViewModel
import cn.xpcheng.wanadnroidmvvm.widget.SpaceItemDecoration
import com.fengchen.uistatus.UiStatusController
import com.fengchen.uistatus.annotation.UiStatus
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author ChengXinPing
 * @time   2021/1/6 15:17
 *
 */
class TreeArticleFragment : BaseFragment<TreeArticleViewModel, LayoutRecyclerViewBinding>() {

    companion object {
        fun newInstance(cid: Int): TreeArticleFragment {
            return TreeArticleFragment().apply {
                arguments = Bundle().apply {
                    putInt("cid", cid)
                }
            }
        }
    }


    private var cid = -1
    private var page = 0

    private val mLinearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }

    //RecyclerView分割线
    private val mSpaceItemDecoration: SpaceItemDecoration by lazy {
        SpaceItemDecoration(DisplayUtil.dp2px(activity as Context, 10F))
    }


    private val mAdapter: HomeAdapter by lazy {
        HomeAdapter(arrayListOf())
    }

    private val mViewModel: TreeArticleViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.layout_recycler_view

    override fun getViewModel(): TreeArticleViewModel = mViewModel

    private lateinit var mUiStatusController: UiStatusController

    override fun initView() {


        mUiStatusController = UiStatusController.get().bind(mDataBinding.swipeRefresh)

        onReload(mUiStatusController) {
            page = 0
            mViewModel.getTreeArticle(page, cid)
        }

        mDataBinding.recycler.init(mLinearLayoutManager, mAdapter).run {
            addItemDecoration(mSpaceItemDecoration)
        }
        mAdapter.run {

            loadMoreModule.setOnLoadMoreListener {
                mViewModel.getTreeArticle(page, cid)
            }
            setOnItemClickListener { _, _, position ->

                nav(
                    NavigationDirections.actionGlobalWebViewFragment(
                        mAdapter.data[position].id,
                        mAdapter.data[position].link,
                        mAdapter.data[position].title,
                        mAdapter.data[position].collect
                    )
                )
            }
        }
        mDataBinding.swipeRefresh.run {
            setColorSchemeResources(R.color.Cyan, R.color.Cyan_600)
            setOnRefreshListener {
                page = 0
                mViewModel.getTreeArticle(page, cid)
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
        page = 0
        mViewModel.getTreeArticle(page, cid)
    }

    override fun createObserver() {
        mViewModel.article.observe(viewLifecycleOwner, Observer {
            page++

            if (it.curPage == 1) {
                mDataBinding.swipeRefresh.isRefreshing = false
                if (it.datas.isNotEmpty()) {
                    mUiStatusController.changeUiStatus(UiStatus.CONTENT)
                    mAdapter.setList(it.datas)
                } else {
                    mUiStatusController.changeUiStatus(UiStatus.EMPTY)
                }
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


    override fun handlerError(appException: AppException) {
        super.handlerError(appException)
        mAdapter.loadMoreModule.loadMoreFail()
        mUiStatusController.changeUiStatus(UiStatus.LOAD_ERROR)
    }
}