package cn.xpcheng.wanadnroidmvvm.ui.fragment

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
import cn.xpcheng.wanadnroidmvvm.ext.*
import cn.xpcheng.wanadnroidmvvm.ui.adapter.HistorySearchAdapter
import cn.xpcheng.wanadnroidmvvm.ui.adapter.HotKeyAdapter
import cn.xpcheng.wanadnroidmvvm.viewmodel.SearchViewModel
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.fengchen.uistatus.UiStatusController
import com.fengchen.uistatus.annotation.UiStatus
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
        HistorySearchAdapter(arrayListOf(), Proxy())
    }

    private val mLinearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }


    override fun getLayoutId(): Int = R.layout.fragment_search

    override fun getViewModel(): SearchViewModel = mViewModel

    private lateinit var mUiStatusController: UiStatusController

    override fun initView() {
        mDataBinding.viewModel = mViewModel
        mDataBinding.click = Proxy()
        setHasOptionsMenu(true)
        mDataBinding.layoutToolbar.toolbar.run {
            mActivity.setSupportActionBar(this)
            initClose { navigateBack() }
        }

        mUiStatusController = UiStatusController.get().bind(mDataBinding.container)

        onReload(mUiStatusController) {
            mViewModel.getHotKey()
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
                mUiStatusController.changeUiStatus(UiStatus.CONTENT)
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

    inner class Proxy() {
        fun deleteAll() {
            MaterialDialog(mActivity)
                .cancelable(true)
                .cancelOnTouchOutside(true)
                .lifecycleOwner(mActivity)
                .show {
                    title(text = "确认删除全部历史记录？")
                    positiveButton(text = "确定") {
                        mViewModel.deleteAllHistory()
                    }
                    negativeButton(text = "取消") {
                        dismiss()
                    }
                }
        }

        fun deleteHistory(searchHistoryKey: SearchHistoryKey) {
            mViewModel.deleteHistory(searchHistoryKey)
        }
    }
}
