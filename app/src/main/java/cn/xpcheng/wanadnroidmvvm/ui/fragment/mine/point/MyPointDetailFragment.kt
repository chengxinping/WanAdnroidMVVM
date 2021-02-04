package cn.xpcheng.wanadnroidmvvm.ui.fragment.mine.point

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import cn.xpcheng.common.utils.DisplayUtil
import cn.xpcheng.mvvm_core.base.network.AppException
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.base.BaseFragment
import cn.xpcheng.wanadnroidmvvm.data.bean.PointDetail
import cn.xpcheng.wanadnroidmvvm.databinding.FragmentRecyclerViewBinding
import cn.xpcheng.wanadnroidmvvm.ext.init
import cn.xpcheng.wanadnroidmvvm.ext.initClose
import cn.xpcheng.wanadnroidmvvm.ext.navigateBack
import cn.xpcheng.wanadnroidmvvm.ext.onReload
import cn.xpcheng.wanadnroidmvvm.ui.adapter.PointDetailAdapter
import cn.xpcheng.wanadnroidmvvm.viewmodel.MyPointDetailViewModel
import cn.xpcheng.wanadnroidmvvm.widget.SpaceItemDecoration
import com.fengchen.uistatus.UiStatusController
import com.fengchen.uistatus.annotation.UiStatus
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author ChengXinPing
 * @time   2021/2/4 9:42
 *
 */
class MyPointDetailFragment : BaseFragment<MyPointDetailViewModel, FragmentRecyclerViewBinding>() {

    private val mViewModel: MyPointDetailViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.fragment_recycler_view

    override fun getViewModel(): MyPointDetailViewModel = mViewModel

    private lateinit var mUiStatusController: UiStatusController

    private var page = 1

    private val mLinearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }

    //RecyclerView分割线
    private val mSpaceItemDecoration: SpaceItemDecoration by lazy {
        SpaceItemDecoration(DisplayUtil.dp2px(activity as Context, 10F))
    }


    private val mAdapter: PointDetailAdapter by lazy {
        PointDetailAdapter(arrayListOf())
    }

    override fun initView() {
        mDataBinding.layoutToolbar.toolbar.initClose("积分详情") {
            navigateBack()
        }

        mUiStatusController = UiStatusController.get().bind(mDataBinding.recyclerView.swipeRefresh)

        onReload(mUiStatusController) {
            page = 1
            mViewModel.getMyPointDetail(page)
        }

        mDataBinding.recyclerView.recycler.init(mLinearLayoutManager, mAdapter).run {
            addItemDecoration(mSpaceItemDecoration)
        }

        mAdapter.run {

            loadMoreModule.setOnLoadMoreListener {
                mViewModel.getMyPointDetail(page)
            }
        }

        mDataBinding.recyclerView.swipeRefresh.run {
            setColorSchemeResources(R.color.Cyan, R.color.Cyan_600)
            setOnRefreshListener {
                page = 1
                mViewModel.getMyPointDetail(page)
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
        mViewModel.getMyPointDetail(page)
    }

    override fun createObserver() {
        mViewModel.pointDetail.observe(viewLifecycleOwner, {
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
        mUiStatusController.changeUiStatus(UiStatus.LOAD_ERROR)
    }
}