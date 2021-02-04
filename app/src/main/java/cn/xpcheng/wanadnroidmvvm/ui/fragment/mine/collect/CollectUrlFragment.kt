package cn.xpcheng.wanadnroidmvvm.ui.fragment.mine.collect

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import cn.xpcheng.common.utils.DisplayUtil
import cn.xpcheng.wanadnroidmvvm.NavigationDirections
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.base.BaseFragment
import cn.xpcheng.wanadnroidmvvm.databinding.LayoutRecyclerViewBinding
import cn.xpcheng.wanadnroidmvvm.ext.init
import cn.xpcheng.wanadnroidmvvm.ext.nav
import cn.xpcheng.wanadnroidmvvm.ext.onReload
import cn.xpcheng.wanadnroidmvvm.ui.adapter.CollectUrlAdapter
import cn.xpcheng.wanadnroidmvvm.viewmodel.CollectUrlViewModel
import cn.xpcheng.wanadnroidmvvm.widget.SpaceItemDecoration
import com.fengchen.uistatus.UiStatusController
import com.fengchen.uistatus.annotation.UiStatus
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author ChengXinPing
 * @time   2021/2/4 15:12
 *
 */
class CollectUrlFragment : BaseFragment<CollectUrlViewModel, LayoutRecyclerViewBinding>() {

    private val mViewModel: CollectUrlViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.layout_recycler_view

    override fun getViewModel(): CollectUrlViewModel = mViewModel

    private val mLinearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }

    //RecyclerView分割线
    private val mSpaceItemDecoration: SpaceItemDecoration by lazy {
        SpaceItemDecoration(DisplayUtil.dp2px(activity as Context, 10F))
    }

    private val mAdapter: CollectUrlAdapter by lazy {
        CollectUrlAdapter(arrayListOf())
    }

    private lateinit var mUiStatusController: UiStatusController

    override fun initView() {
        mUiStatusController = UiStatusController.get().bind(mDataBinding.swipeRefresh)

        onReload(mUiStatusController) {
            mViewModel.getCollectUrl()
        }

        mDataBinding.recycler.init(mLinearLayoutManager, mAdapter).run {
            addItemDecoration(mSpaceItemDecoration)
        }

        mAdapter.run {


            setOnItemClickListener { _, _, position ->

                nav(
                    NavigationDirections.actionGlobalWebViewFragment(
                        -1,
                        mAdapter.data[position].link,
                        mAdapter.data[position].name,
                        true
                    )
                )
            }
        }
        mDataBinding.swipeRefresh.run {
            setColorSchemeResources(R.color.Cyan, R.color.Cyan_600)
            setOnRefreshListener {
                mViewModel.getCollectUrl()
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
        mViewModel.getCollectUrl()
    }

    override fun createObserver() {
        mViewModel.webSite.observe(viewLifecycleOwner, {
            mUiStatusController.changeUiStatus(UiStatus.CONTENT)
            mDataBinding.swipeRefresh.isRefreshing = false
            mAdapter.setList(it)
        })
    }
}