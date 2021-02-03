package cn.xpcheng.wanadnroidmvvm.ui.adapter

import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.data.bean.HotKey
import cn.xpcheng.wanadnroidmvvm.data.bean.SearchHistoryKey
import cn.xpcheng.wanadnroidmvvm.databinding.ItemHistorySearchBinding
import cn.xpcheng.wanadnroidmvvm.ui.fragment.SearchFragment
import cn.xpcheng.wanadnroidmvvm.viewmodel.SearchViewModel
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder

/**
 * @author ChengXinPing
 * @time   2020/9/28 14:30
 *
 */
class HistorySearchAdapter(
    data: MutableList<SearchHistoryKey>,
    private val mClick: SearchFragment.Proxy
) :
    BaseQuickAdapter<SearchHistoryKey, BaseDataBindingHolder<ItemHistorySearchBinding>>(
        R.layout.item_history_search,
        data
    ) {

    override fun convert(
        holder: BaseDataBindingHolder<ItemHistorySearchBinding>,
        item: SearchHistoryKey
    ) {
        holder.dataBinding?.let {
            it.data = item
            it.click = mClick
            it.executePendingBindings()
        }
    }

}