package cn.xpcheng.wanadnroidmvvm.ui.adapter

import android.text.Html
import cn.xpcheng.wanadnroidmvvm.data.bean.NavigationBean
import q.rorbin.verticaltablayout.adapter.TabAdapter
import q.rorbin.verticaltablayout.widget.ITabView

/**
 * @author ChengXinPing
 * @time   2020/11/20 9:36
 *
 */
class NavigationTagAdapter(private val list: List<NavigationBean>) : TabAdapter {
    override fun getCount(): Int = list.size

    override fun getBadge(position: Int): ITabView.TabBadge? = null

    override fun getIcon(position: Int): ITabView.TabIcon? = null

    override fun getTitle(position: Int): ITabView.TabTitle =
        ITabView.TabTitle.Builder()
            .setContent(Html.fromHtml(list[position].name).toString())
            .build()

    override fun getBackground(position: Int): Int = -1


}