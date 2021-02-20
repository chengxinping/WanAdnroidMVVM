package cn.xpcheng.wanadnroidmvvm.ui.fragment.mine.article

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import cn.xpcheng.common.utils.DisplayUtil
import cn.xpcheng.mvvm_core.base.network.AppException
import cn.xpcheng.wanadnroidmvvm.NavigationDirections
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.base.BaseFragment
import cn.xpcheng.wanadnroidmvvm.databinding.FragmentRecyclerViewBinding
import cn.xpcheng.wanadnroidmvvm.ext.*
import cn.xpcheng.wanadnroidmvvm.ui.adapter.HomeAdapter
import cn.xpcheng.wanadnroidmvvm.viewmodel.MyArticleViewModel
import cn.xpcheng.wanadnroidmvvm.widget.SpaceItemDecoration
import com.fengchen.uistatus.UiStatusController
import com.fengchen.uistatus.annotation.UiStatus
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author ChengXinPing
 * @time   2021/2/4 16:57
 *
 */
class MyArticleFragment : BaseFragment<MyArticleViewModel, FragmentRecyclerViewBinding>() {

    private var page = 1

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

    private val mViewModel: MyArticleViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.fragment_recycler_view

    override fun getViewModel(): MyArticleViewModel = mViewModel

    override fun initView() {
        mDataBinding.layoutToolbar.toolbar.run {
            initClose("我分享的文章", onBack = {
                navigateBack()
            })

            inflateMenu(R.menu.menu_add)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_add -> {
                        nav(R.id.action_myArticleFragment_to_uploadFragment)
                    }
                }
                true
            }
        }

        mDataBinding.recyclerView.recycler.init(mLinearLayoutManager, mAdapter).run {
            addItemDecoration(mSpaceItemDecoration)
        }

        mUiStatusController = UiStatusController.get().bind(mDataBinding.recyclerView.swipeRefresh)

        onReload(mUiStatusController) {
            page = 1
            mViewModel.getMyArticle(page)
        }

        mAdapter.run {

            loadMoreModule.setOnLoadMoreListener {
                mViewModel.getMyArticle(page)
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
                page = 1
                mViewModel.getMyArticle(page)
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
        page = 1
        mViewModel.getMyArticle(page)
    }

    override fun createObserver() {
        mViewModel.shareInfo.observe(viewLifecycleOwner, {
            page++
            if (it.shareArticles.curPage == 1) {
                mDataBinding.recyclerView.swipeRefresh.isRefreshing = false
                if (it.shareArticles.datas.isNotEmpty()) {
                    mUiStatusController.changeUiStatus(UiStatus.CONTENT)
                    mAdapter.setList(it.shareArticles.datas)
                } else {
                    mUiStatusController.changeUiStatus(UiStatus.EMPTY)
                }
            } else {
                mAdapter.addData(it.shareArticles.datas)
            }

            mAdapter.loadMoreModule.run {
                if (it.shareArticles.over)
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