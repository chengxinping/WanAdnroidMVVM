package cn.xpcheng.wanadnroidmvvm.ui.adapter

import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.data.bean.Article
import cn.xpcheng.wanadnroidmvvm.databinding.ItemNavigationChildBinding
import cn.xpcheng.wanadnroidmvvm.ui.fragment.square.navigation.NavigationFragment
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder

/**
 * @author ChengXinPing
 * @time   2020/9/23 15:19
 *
 */
class NavigationItemAdapter(
    data: MutableList<Article>,
    private val click: NavigationFragment.ProxyClick
) :
    BaseQuickAdapter<Article, BaseDataBindingHolder<ItemNavigationChildBinding>>(
        R.layout.item_navigation_child,
        data
    ) {
    override fun convert(holder: BaseDataBindingHolder<ItemNavigationChildBinding>, item: Article) {
        holder.dataBinding?.let {

            it.article = item
            it.click = click
            it.executePendingBindings()
        }
    }
}