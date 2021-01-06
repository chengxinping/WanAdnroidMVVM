package cn.xpcheng.wanadnroidmvvm.ui.adapter

import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.data.bean.Article
import cn.xpcheng.wanadnroidmvvm.data.bean.TreeBean
import cn.xpcheng.wanadnroidmvvm.databinding.ItemFlowBinding
import cn.xpcheng.wanadnroidmvvm.databinding.ItemNavigationChildBinding
import cn.xpcheng.wanadnroidmvvm.databinding.ItemTreeChildBinding
import cn.xpcheng.wanadnroidmvvm.ui.fragment.NavigationFragment
import cn.xpcheng.wanadnroidmvvm.ui.fragment.TreeFragment
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder

/**
 * @author ChengXinPing
 * @time   2020/9/23 15:19
 *
 */
class TreeItemAdapter(data: MutableList<TreeBean>) :
    BaseQuickAdapter<TreeBean, BaseDataBindingHolder<ItemTreeChildBinding>>(
        R.layout.item_tree_child,
        data
    ) {
    override fun convert(holder: BaseDataBindingHolder<ItemTreeChildBinding>, item: TreeBean) {
        holder.dataBinding?.let {
            it.data = item
            it.executePendingBindings()
        }
    }
}