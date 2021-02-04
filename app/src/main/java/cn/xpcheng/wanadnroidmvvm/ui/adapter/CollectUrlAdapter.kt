package cn.xpcheng.wanadnroidmvvm.ui.adapter

import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.data.bean.WebSite
import cn.xpcheng.wanadnroidmvvm.databinding.ItemWebsiteBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder

/**
 * @author ChengXinPing
 * @time   2021/2/4 16:40
 *
 */
class CollectUrlAdapter(data: MutableList<WebSite>) :
    BaseQuickAdapter<WebSite, BaseDataBindingHolder<ItemWebsiteBinding>>(
        R.layout.item_website,
        data
    ) {
    override fun convert(holder: BaseDataBindingHolder<ItemWebsiteBinding>, item: WebSite) {
        holder.dataBinding?.let {
            it.website = item
            it.executePendingBindings()
        }
    }
}