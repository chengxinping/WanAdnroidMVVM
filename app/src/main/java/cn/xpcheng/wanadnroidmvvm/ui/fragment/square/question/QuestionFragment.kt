package cn.xpcheng.wanadnroidmvvm.ui.fragment.square.question

import android.content.Context
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
import cn.xpcheng.wanadnroidmvvm.viewmodel.QuestionViewModel
import cn.xpcheng.wanadnroidmvvm.widget.SpaceItemDecoration
import com.fengchen.uistatus.UiStatusController
import com.fengchen.uistatus.annotation.UiStatus
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author ChengXinPing
 * @time   2020/11/19 9:57
 *
 */
class QuestionFragment : BaseFragment<QuestionViewModel, LayoutRecyclerViewBinding>() {

    private val mViewModel: QuestionViewModel by viewModel()

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

    override fun getLayoutId(): Int = R.layout.layout_recycler_view

    override fun getViewModel(): QuestionViewModel = mViewModel

    private lateinit var mUiStatusController: UiStatusController

    override fun initView() {
        mDataBinding.recycler.init(mLinearLayoutManager, mAdapter).run {
            addItemDecoration(mSpaceItemDecoration)
        }

        mUiStatusController = UiStatusController.get().bind(mDataBinding.swipeRefresh)

        onReload(mUiStatusController) {
            page = 1
            mViewModel.getQuestionList(page)
        }


        mAdapter.run {

            loadMoreModule.setOnLoadMoreListener {
                mViewModel.getQuestionList(page)
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
                page = 1
                mViewModel.getQuestionList(page)
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
        page = 1
        mViewModel.getQuestionList(page)
    }

    override fun createObserver() {
        mViewModel.questionList.observe(viewLifecycleOwner, Observer {
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