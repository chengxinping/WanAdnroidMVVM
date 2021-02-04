package cn.xpcheng.wanadnroidmvvm.ui.fragment.mine.point

import android.content.Context
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import cn.xpcheng.common.utils.DisplayUtil
import cn.xpcheng.mvvm_core.base.network.AppException
import cn.xpcheng.wanadnroidmvvm.NavigationDirections
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.base.BaseFragment
import cn.xpcheng.wanadnroidmvvm.databinding.FragmentPointBinding
import cn.xpcheng.wanadnroidmvvm.ext.*
import cn.xpcheng.wanadnroidmvvm.ui.adapter.RankAdapter
import cn.xpcheng.wanadnroidmvvm.viewmodel.PointViewModel
import cn.xpcheng.wanadnroidmvvm.widget.SpaceItemDecoration
import com.fengchen.uistatus.UiStatusController
import com.fengchen.uistatus.annotation.UiStatus
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author ChengXinPing
 * @time   2021/2/1 14:19
 *
 */
class PointFragment : BaseFragment<PointViewModel, FragmentPointBinding>() {

    private val mViewModel: PointViewModel by viewModel()

    private val args: PointFragmentArgs by navArgs()

    override fun getLayoutId(): Int = R.layout.fragment_point

    override fun getViewModel(): PointViewModel = mViewModel

    private val mLinearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }

    private var page = 1

    //RecyclerView分割线
    private val mSpaceItemDecoration: SpaceItemDecoration by lazy {
        SpaceItemDecoration(DisplayUtil.dp2px(activity as Context, 10F))
    }

    private val mAdapter: RankAdapter by lazy {
        RankAdapter(arrayListOf())
    }

    private lateinit var mUiStatusController: UiStatusController

    override fun initView() {
        mDataBinding.run {
            layoutToolbar.toolbar.run {
                initClose("积分排行") { navigateBack() }
                inflateMenu(R.menu.menu_rank)
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.menu_help -> {
                            nav(
                                NavigationDirections.actionGlobalWebViewFragment(
                                    -1,
                                    "https://www.wanandroid.com/blog/show/2653",
                                    "积分规则",
                                    false
                                )
                            )
                        }
                        R.id.menu_record -> nav(R.id.action_pointFragment_to_myPointDetailFragment)
                    }
                    true
                }
            }
            if (args.rank.isNotBlank()) {
                myInfo.visibility = View.VISIBLE
                tvMyRank.text = args.rank
                tvMyName.text = args.userName
                tvMyCoinCount.text = args.coinCount.toString()
            } else {
                myInfo.visibility = View.GONE
            }
        }

        mDataBinding.layoutRecycler.recycler.init(mLinearLayoutManager, mAdapter).run {
            addItemDecoration(mSpaceItemDecoration)
        }

        mUiStatusController =
            UiStatusController.get().bind(mDataBinding.layoutRecycler.swipeRefresh)

        onReload(mUiStatusController) {
            page = 1
            mViewModel.getRankList(page)
        }

        mAdapter.run {
            loadMoreModule.setOnLoadMoreListener {
                mViewModel.getRankList(page)
            }
        }

        mDataBinding.layoutRecycler.swipeRefresh.run {
            setColorSchemeResources(R.color.Cyan, R.color.Cyan_600)
            setOnRefreshListener {
                page = 1
                mViewModel.getRankList(page)
            }
        }

        mDataBinding.layoutRecycler.fab.setOnClickListener {
            mDataBinding.layoutRecycler.recycler.run {
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
        mViewModel.getRankList(page)
    }

    override fun createObserver() {
        mViewModel.rankList.observe(viewLifecycleOwner, {
            page++
            if (it.curPage == 1) {
                mDataBinding.layoutRecycler.swipeRefresh.isRefreshing = false
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