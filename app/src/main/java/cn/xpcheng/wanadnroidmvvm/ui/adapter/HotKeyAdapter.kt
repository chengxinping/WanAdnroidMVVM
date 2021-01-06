package cn.xpcheng.wanadnroidmvvm.ui.adapter

import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.data.bean.HotKey
import cn.xpcheng.wanadnroidmvvm.databinding.ItemFlowBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder

/**
 * @author ChengXinPing
 * @time   2020/9/23 15:19
 *
 */
class HotKeyAdapter(data: MutableList<HotKey>) :
    BaseQuickAdapter<HotKey, BaseDataBindingHolder<ItemFlowBinding>>(R.layout.item_flow, data) {
    override fun convert(holder: BaseDataBindingHolder<ItemFlowBinding>, item: HotKey) {
        holder.dataBinding?.let {
            it.name = item.name
            it.executePendingBindings()
        }
    }
}