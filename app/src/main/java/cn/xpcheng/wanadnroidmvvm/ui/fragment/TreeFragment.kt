package cn.xpcheng.wanadnroidmvvm.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import cn.xpcheng.common.utils.DisplayUtil
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.base.BaseFragment
import cn.xpcheng.wanadnroidmvvm.data.bean.Article
import cn.xpcheng.wanadnroidmvvm.data.bean.Tree
import cn.xpcheng.wanadnroidmvvm.data.bean.TreeBean
import cn.xpcheng.wanadnroidmvvm.databinding.LayoutRecyclerViewBinding
import cn.xpcheng.wanadnroidmvvm.ext.init
import cn.xpcheng.wanadnroidmvvm.ext.nav
import cn.xpcheng.wanadnroidmvvm.ui.adapter.HomeAdapter
import cn.xpcheng.wanadnroidmvvm.ui.adapter.TreeAdapter
import cn.xpcheng.wanadnroidmvvm.viewmodel.TreeViewModel
import cn.xpcheng.wanadnroidmvvm.widget.SpaceItemDecoration
import kotlinx.android.synthetic.main.layout_recycler_view.*
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

    private val mData = mutableListOf<Tree>()

    private val mAdapter: TreeAdapter by lazy {
        TreeAdapter(mData, ProxyClick())
    }

    override fun getLayoutId(): Int = R.layout.layout_recycler_view

    override fun getViewModel(): TreeViewModel = mViewModel

    override fun initView() {
        recycler.init(mLinearLayoutManager, mAdapter).run {
            addItemDecoration(mSpaceItemDecoration)
        }

        fab.setOnClickListener {
            recycler.run {
                if (mLinearLayoutManager.findFirstVisibleItemPosition() > 20) {
                    scrollToPosition(0)
                } else {
                    smoothScrollToPosition(0)
                }
            }

        }

        swipe_refresh.run {
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
            swipe_refresh.isRefreshing = false
            mAdapter.setList(it)
        })
    }

    inner class ProxyClick {
        fun goToTreeDetail(data: Tree, index: Int) {
            nav(R.id.action_mainFragment_to_treeDetailFragment, Bundle().apply {
                putParcelable("data", data)
                putInt("index", index)
            })
        }
    }
}