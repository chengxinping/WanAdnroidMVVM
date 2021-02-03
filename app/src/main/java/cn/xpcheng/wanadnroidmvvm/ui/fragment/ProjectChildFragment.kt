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
import cn.xpcheng.wanadnroidmvvm.ui.adapter.ProjectAdapter
import cn.xpcheng.wanadnroidmvvm.viewmodel.ProjectChildViewModel
import cn.xpcheng.wanadnroidmvvm.widget.SpaceItemDecoration
import com.fengchen.uistatus.UiStatusController
import com.fengchen.uistatus.annotation.UiStatus
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author ChengXinPing
 * @time   2020/10/14 16:08
 *
 */
class ProjectChildFragment : BaseFragment<ProjectChildViewModel, LayoutRecyclerViewBinding>() {


    companion object {
        fun newInstance(cid: Int): ProjectChildFragment {
            return ProjectChildFragment().apply {
                arguments = Bundle().apply {
                    putInt("cid", cid)
                }
            }
        }
    }

    private val mViewModel: ProjectChildViewModel by viewModel()

    private var cid = -1
    private var isNew: Boolean = false

    private val mLinearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }

    //RecyclerView分割线
    private val mSpaceItemDecoration: SpaceItemDecoration by lazy {
        SpaceItemDecoration(DisplayUtil.dp2px(activity as Context, 10F))
    }

    private val mData = mutableListOf<Article>()

    private val mAdapter: ProjectAdapter by lazy {
        ProjectAdapter(mData)
    }

    override fun getLayoutId(): Int = R.layout.layout_recycler_view

    override fun getViewModel(): ProjectChildViewModel = mViewModel


    private lateinit var mUiStatusController: UiStatusController

    override fun initView() {

        mUiStatusController = UiStatusController.get().bind(mDataBinding.swipeRefresh)

        onReload(mUiStatusController) {
            mViewModel.getProjectList(true, cid, isNew)
        }

        mDataBinding.recycler.init(mLinearLayoutManager, mAdapter).run {
            addItemDecoration(mSpaceItemDecoration)
        }
        mAdapter.run {

            loadMoreModule.setOnLoadMoreListener {
                mViewModel.getProjectList(false, cid, isNew)
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
                mViewModel.getProjectList(true, cid, isNew)
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

        isNew = (cid == -1)
        mViewModel.getProjectList(true, cid, isNew)
    }

    override fun createObserver() {
        mViewModel.projectArticles.observe(viewLifecycleOwner, Observer {

            if (it.curPage == 1) {
                mDataBinding.swipeRefresh.isRefreshing = false
                mAdapter.setList(it.datas)
            } else {
                mAdapter.addData(it.datas)
            }
            mUiStatusController.changeUiStatus(UiStatus.CONTENT)
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