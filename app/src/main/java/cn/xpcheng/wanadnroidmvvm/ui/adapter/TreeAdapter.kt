package cn.xpcheng.wanadnroidmvvm.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.data.bean.Tree
import cn.xpcheng.wanadnroidmvvm.databinding.ItemTreeBinding
import cn.xpcheng.wanadnroidmvvm.ext.init
import cn.xpcheng.wanadnroidmvvm.ui.fragment.square.tree.TreeFragment
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

/**
 * @author ChengXinPing
 * @time   2021/1/6 14:49
 *
 */
class TreeAdapter(list: MutableList<Tree>, private val click: TreeFragment.ProxyClick) :
    BaseQuickAdapter<Tree, BaseDataBindingHolder<ItemTreeBinding>>(R.layout.item_tree, list) {
    override fun convert(holder: BaseDataBindingHolder<ItemTreeBinding>, item: Tree) {
        val flexLayoutManager = FlexboxLayoutManager(context)
        flexLayoutManager.run {
            //方向 主轴为水平方向，起点在左端
            flexDirection = FlexDirection.ROW
            //左对齐
            justifyContent = JustifyContent.FLEX_START
        }

        val mItemAdapter = TreeItemAdapter(item.children.toMutableList())
        holder.getView<RecyclerView>(R.id.item_tree_rv)
            .init(flexLayoutManager, mItemAdapter)

        mItemAdapter.setOnItemClickListener { _, _, position ->
            click.goToTreeDetail(item, position)
        }

        holder.dataBinding?.let {
            it.data = item
            it.click = click
            it.executePendingBindings()
        }
    }
}