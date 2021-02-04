package cn.xpcheng.wanadnroidmvvm.ui.fragment.search

import android.content.Context
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import cn.xpcheng.common.utils.DisplayUtil
import cn.xpcheng.mvvm_core.base.network.AppException
import cn.xpcheng.wanadnroidmvvm.NavigationDirections
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.base.BaseFragment
import cn.xpcheng.wanadnroidmvvm.databinding.FragmentRecyclerViewBinding
import cn.xpcheng.wanadnroidmvvm.ext.*
import cn.xpcheng.wanadnroidmvvm.ui.adapter.HomeAdapter
import cn.xpcheng.wanadnroidmvvm.viewmodel.SearchDetailViewModel
import cn.xpcheng.wanadnroidmvvm.widget.SpaceItemDecoration
import com.fengchen.uistatus.UiStatusController
import com.fengchen.uistatus.annotation.UiStatus
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author ChengXinPing
 * @time   2020/10/9 9:47
 *
 */
class SearchDetailFragment : BaseFragment<SearchDetailViewModel, FragmentRecyclerViewBinding>() {

    private var page = 0

    private val args: SearchDetailFragmentArgs by navArgs()

    private val mViewModel: SearchDetailViewModel by viewModel()

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

    private lateinit var mUiStatusController: UiStatusController


    override fun getLayoutId(): Int = R.layout.fragment_recycler_view

    override fun getViewModel(): SearchDetailViewModel = mViewModel

    override fun initView() {

        mDataBinding.layoutToolbar.toolbar.initClose(args.searchKey, onBack = {
            navigateBack()
        })

        mDataBinding.recyclerView.recycler.init(mLinearLayoutManager, mAdapter).run {
            addItemDecoration(mSpaceItemDecoration)
        }

        mUiStatusController = UiStatusController.get().bind(mDataBinding.recyclerView.swipeRefresh)

        onReload(mUiStatusController) {
            page = 0
            mViewModel.search(args.searchKey, page)
        }

        mAdapter.run {

            loadMoreModule.setOnLoadMoreListener {
                mViewModel.search(args.searchKey, page)
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

        mDataBinding.recyclerView.swipeRefresh.run {
            setColorSchemeResources(R.color.Cyan, R.color.Cyan_600)
            setOnRefreshListener {
                page = 0
                mViewModel.search(args.searchKey, page)
            }
        }

        mDataBinding.recyclerView.fab.setOnClickListener {
            mDataBinding.recyclerView.recycler.run {
                if (mLinearLayoutManager.findFirstVisibleItemPosition() > 20) {
                    scrollToPosition(0)
                } else {
                    smoothScrollToPosition(0)
                }
            }
        }

    }

    override fun lazyLoadData() {
        mViewModel.search(args.searchKey, page)
    }

    override fun createObserver() {
        mViewModel.searchResult.observe(viewLifecycleOwner, Observer {
            page++
            if (it.curPage == 1) {
                mDataBinding.recyclerView.swipeRefresh.isRefreshing = false
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
    }
}