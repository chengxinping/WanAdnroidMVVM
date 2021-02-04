package cn.xpcheng.wanadnroidmvvm.ui.adapter

import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.data.bean.PointDetail
import cn.xpcheng.wanadnroidmvvm.databinding.ItemPointDetailBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder

/**
 * @author ChengXinPing
 * @time   2021/2/4 10:28
 *
 */
class PointDetailAdapter(data: MutableList<PointDetail>) :
    BaseQuickAdapter<PointDetail, BaseDataBindingHolder<ItemPointDetailBinding>>(
        R.layout.item_point_detail,
        data
    ), LoadMoreModule {

    override fun convert(holder: BaseDataBindingHolder<ItemPointDetailBinding>, item: PointDetail) {
        val descStr =
            if (item.desc.contains("积分")) item.desc.subSequence(
                item.desc.indexOf("积分"),
                item.desc.length
            ) else ""

        holder.dataBinding?.let {
            it.pointDetail = item
            it.reason = item.reason + " " + descStr
            it.executePendingBindings()
        }
    }
}