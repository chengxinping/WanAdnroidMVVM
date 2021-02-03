package cn.xpcheng.wanadnroidmvvm.ui.adapter

import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.data.bean.Point
import cn.xpcheng.wanadnroidmvvm.databinding.ItemRankBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder

/**
 * @author ChengXinPing
 * @time   2021/2/3 16:17
 *
 */
class RankAdapter(data: MutableList<Point>) :
    BaseQuickAdapter<Point, BaseDataBindingHolder<ItemRankBinding>>(
        R.layout.item_rank, data
    ), LoadMoreModule {
    override fun convert(holder: BaseDataBindingHolder<ItemRankBinding>, item: Point) {
        holder.dataBinding?.let {
            it.point = item
            it.executePendingBindings()
        }
    }
}