package cn.xpcheng.wanadnroidmvvm.ui.fragment.mine.collect

import android.content.Context
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
import cn.xpcheng.wanadnroidmvvm.ui.adapter.CollectArticleAdapter
import cn.xpcheng.wanadnroidmvvm.viewmodel.CollectArticleViewModel
import cn.xpcheng.wanadnroidmvvm.widget.SpaceItemDecoration
import com.fengchen.uistatus.UiStatusController
import com.fengchen.uistatus.annotation.UiStatus
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author ChengXinPing
 * @time   2021/2/4 15:12
 *
 */
class CollectArticleFragment : BaseFragment<CollectArticleViewModel, LayoutRecyclerViewBinding>() {

    private val mViewModel: CollectArticleViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.layout_recycler_view

    override fun getViewModel(): CollectArticleViewModel = mViewModel

    private var page = 0

    private val mLinearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }

    //RecyclerView分割线
    private val mSpaceItemDecoration: SpaceItemDecoration by lazy {
        SpaceItemDecoration(DisplayUtil.dp2px(activity as Context, 10F))
    }


    private val mAdapter: CollectArticleAdapter by lazy {
        CollectArticleAdapter(arrayListOf())
    }

    private lateinit var mUiStatusController: UiStatusController

    override fun initView() {
        mDataBinding.recycler.init(mLinearLayoutManager, mAdapter).run {
            addItemDecoration(mSpaceItemDecoration)
        }

        mUiStatusController = UiStatusController.get().bind(mDataBinding.swipeRefresh)

        onReload(mUiStatusController) {
            page = 0
            mViewModel.getCollectArticle(page)
        }

        mAdapter.run {

            loadMoreModule.setOnLoadMoreListener {
                mViewModel.getCollectArticle(page)
            }
            setOnItemClickListener { _, _, position ->

                nav(
                    NavigationDirections.actionGlobalWebViewFragment(
                        mAdapter.data[position].id,
                        mAdapter.data[position].link,
                        mAdapter.data[position].title,
                        true
                    )
                )
            }
        }
        mDataBinding.swipeRefresh.run {
            setColorSchemeResources(R.color.Cyan, R.color.Cyan_600)
            setOnRefreshListener {
                page = 0
                mViewModel.getCollectArticle(page)
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
        page = 0
        mViewModel.getCollectArticle(page)
    }

    override fun createObserver() {
        mViewModel.collectArticle.observe(viewLifecycleOwner, {
            page++
            mUiStatusController.changeUiStatus(UiStatus.CONTENT)
            if (it.curPage == 1) {
                mDataBinding.swipeRefresh.isRefreshing = false
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

    override fun handlerError(appException: AppException) {
        super.handlerError(appException)
        mAdapter.loadMoreModule.loadMoreFail()
        mUiStatusController.changeUiStatus(UiStatus.LOAD_ERROR)
    }

}