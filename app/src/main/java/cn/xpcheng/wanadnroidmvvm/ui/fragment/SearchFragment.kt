package cn.xpcheng.wanadnroidmvvm.ui.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.base.BaseFragment
import cn.xpcheng.wanadnroidmvvm.data.bean.SearchHistoryKey
import cn.xpcheng.wanadnroidmvvm.databinding.FragmentSearchBinding
import cn.xpcheng.wanadnroidmvvm.ext.init
import cn.xpcheng.wanadnroidmvvm.ext.initClose
import cn.xpcheng.wanadnroidmvvm.ext.nav
import cn.xpcheng.wanadnroidmvvm.ext.navigateBack
import cn.xpcheng.wanadnroidmvvm.ui.adapter.HistorySearchAdapter
import cn.xpcheng.wanadnroidmvvm.ui.adapter.HotKeyAdapter
import cn.xpcheng.wanadnroidmvvm.viewmodel.SearchViewModel
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *@author chengxinping
 *@time 2020/9/14
 *@desc
 */
class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>() {

    private val mViewModel: SearchViewModel by viewModel()

    private val mHotKeyAdapter: HotKeyAdapter by lazy {
        HotKeyAdapter(arrayListOf())
    }

    private val mHistoryAdapter: HistorySearchAdapter by lazy {
        HistorySearchAdapter(arrayListOf(), mViewModel)
    }

    private val mLinearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }


    override fun getLayoutId(): Int = R.layout.fragment_search

    override fun getViewModel(): SearchViewModel = mViewModel

    override fun initView() {
        mDataBinding.viewModel = mViewModel
        setHasOptionsMenu(true)
        mDataBinding.layoutToolbar.toolbar.run {
            mActivity.setSupportActionBar(this)
            initClose { navigateBack() }
        }

        val flexLayoutManager = FlexboxLayoutManager(context)
        flexLayoutManager.run {
            //方向 主轴为水平方向，起点在左端
            flexDirection = FlexDirection.ROW
            //左对齐
            justifyContent = JustifyContent.FLEX_START
        }
        mDataBinding.ryHotSearch.init(flexLayoutManager, mHotKeyAdapter)
        mHotKeyAdapter.run {
            setOnItemClickListener { _, _, position ->
                saveHistory(data[position].name)
                nav(SearchFragmentDirections.actionSearchFragmentToSearchDetailFragment(data[position].name))
            }
        }

        mHistoryAdapter.run {
            setOnItemClickListener { _, _, position ->
                saveHistory(data[position].history)
                nav(SearchFragmentDirections.actionSearchFragmentToSearchDetailFragment(data[position].history))

            }
        }

        mDataBinding.ryHistorySearch.init(mLinearLayoutManager, mHistoryAdapter)


    }

    override fun lazyLoadData() {
        mViewModel.getHotKey()
        mViewModel.getHistory()
    }

    override fun createObserver() {
        mViewModel.run {
            hotKeys.observe(viewLifecycleOwner, Observer {
                mHotKeyAdapter.setList(it)
            })

            historyKeys.observe(viewLifecycleOwner, Observer {
                mHistoryAdapter.setList(it)
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search, menu)
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.run {
            maxWidth = Integer.MAX_VALUE
            onActionViewExpanded()
            queryHint = "发现更多内容"
            isSubmitButtonEnabled = true
            try {
                val field = searchView.javaClass.getDeclaredField("mGoButton")
                field.isAccessible = true
                val mGoButton = field.get(searchView) as ImageView
                mGoButton.setImageResource(R.drawable.ic_search)
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        query?.let {
                            saveHistory(it)
                            nav(
                                SearchFragmentDirections.actionSearchFragmentToSearchDetailFragment(
                                    it
                                )
                            )
                        }
                        return false
                    }

                    override fun onQueryTextChange(p0: String?): Boolean {
                        return false
                    }
                })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    private fun saveHistory(key: String) {
        val history = SearchHistoryKey(key)
        mViewModel.run {
            insertHistory(history)
            historyKeys.value?.let {
                if (it.contains(history)) {
                    it.remove(history)
                }
                it.add(0, history)
                historyKeys.postValue(it)
            }
        }
    }
}
