package cn.xpcheng.wanadnroidmvvm.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.data.bean.NavigationBean
import cn.xpcheng.wanadnroidmvvm.databinding.ItemNavigationBinding
import cn.xpcheng.wanadnroidmvvm.ext.init
import cn.xpcheng.wanadnroidmvvm.ui.fragment.square.navigation.NavigationFragment
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

/**
 * @author ChengXinPing
 * @time   2020/11/20 10:17
 *
 */
class NavigationAdapter(
    list: MutableList<NavigationBean>,
    private val click: NavigationFragment.ProxyClick
) :
    BaseQuickAdapter<NavigationBean, BaseDataBindingHolder<ItemNavigationBinding>>(
        R.layout.item_navigation,
        list
    ) {
    override fun convert(
        holder: BaseDataBindingHolder<ItemNavigationBinding>,
        item: NavigationBean
    ) {
        val flexLayoutManager = FlexboxLayoutManager(context)
        flexLayoutManager.run {
            //方向 主轴为水平方向，起点在左端
            flexDirection = FlexDirection.ROW
            //左对齐
            justifyContent = JustifyContent.FLEX_START
        }

        val mNavigationItemAdapter = NavigationItemAdapter(item.articles.toMutableList(), click)
        holder.getView<RecyclerView>(R.id.ry_item_detail)
            .init(flexLayoutManager, mNavigationItemAdapter)


        holder.dataBinding?.let {
            it.data = item
            it.executePendingBindings()
        }
    }
}