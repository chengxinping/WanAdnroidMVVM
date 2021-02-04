package cn.xpcheng.wanadnroidmvvm.ui.fragment.square.tree

import android.content.Context
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cn.xpcheng.common.utils.DisplayUtil
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.base.BaseFragment
import cn.xpcheng.wanadnroidmvvm.data.bean.Tree
import cn.xpcheng.wanadnroidmvvm.databinding.LayoutRecyclerViewBinding
import cn.xpcheng.wanadnroidmvvm.ext.init
import cn.xpcheng.wanadnroidmvvm.ext.nav
import cn.xpcheng.wanadnroidmvvm.ext.onReload
import cn.xpcheng.wanadnroidmvvm.ui.adapter.TreeAdapter
import cn.xpcheng.wanadnroidmvvm.ui.fragment.MainFragmentDirections
import cn.xpcheng.wanadnroidmvvm.viewmodel.TreeViewModel
import cn.xpcheng.wanadnroidmvvm.widget.SpaceItemDecoration
import com.fengchen.uistatus.UiStatusController
import com.fengchen.uistatus.annotation.UiStatus
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author ChengXinPing
 * @time   2021/1/4 16:15
 *
 */
class TreeFragment : BaseFragment<TreeViewModel, LayoutRecyclerViewBinding>() {

    private val mViewModel: TreeViewModel by viewModel()

    private val mLinearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }

    //RecyclerView分割线
    private val mSpaceItemDecoration: SpaceItemDecoration by lazy {
        SpaceItemDecoration(DisplayUtil.dp2px(activity as Context, 10F))
    }

    private val mAdapter: TreeAdapter by lazy {
        TreeAdapter(arrayListOf(), ProxyClick())
    }

    override fun getLayoutId(): Int = R.layout.layout_recycler_view

    override fun getViewModel(): TreeViewModel = mViewModel

    private lateinit var mUiStatusController: UiStatusController

    override fun initView() {

        mUiStatusController = UiStatusController.get().bind(mDataBinding.swipeRefresh)

        onReload(mUiStatusController) {
            mViewModel.getTree()
        }

        mDataBinding.recycler.init(mLinearLayoutManager, mAdapter).run {
            addItemDecoration(mSpaceItemDecoration)
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

        mDataBinding.swipeRefresh.run {
            setColorSchemeResources(R.color.Cyan, R.color.Cyan_600)
            setOnRefreshListener {
                mViewModel.getTree()
            }
        }
    }

    override fun lazyLoadData() {
        mViewModel.getTree()
    }

    override fun createObserver() {
        mViewModel.tree.observe(viewLifecycleOwner, Observer {
            mDataBinding.swipeRefresh.isRefreshing = false
            mAdapter.setList(it)
            mUiStatusController.changeUiStatus(UiStatus.CONTENT)
        })
    }

    inner class ProxyClick {
        fun goToTreeDetail(data: Tree, index: Int) {
            nav(MainFragmentDirections.actionMainFragmentToTreeDetailFragment(data, index))
        }
    }
}